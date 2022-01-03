package io.GitHub.RamiHalabi.PlayerScoreBoard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Main extends JavaPlugin implements Listener {

	// toggle messages
	private static final String enable_msg = ChatColor.GREEN + "PlayerScoreBoard enabled";
	private static final String disable_msg = ChatColor.RED + "PlayerScoreBoard disabled";

	private int taskID; // ID for each playerboard 

    public void onEnable() {
		
    	getServer().getPluginManager().registerEvents(this, this); // load events to update scoreboard
		
    	if(!Bukkit.getOnlinePlayers().isEmpty()) { // check if players are on the server
    		for(Player p: Bukkit.getOnlinePlayers()) { // loop throught each player
    			createBoard(p); // create player specific board
    			start(p); // run animation 
    		}
    	}   	
		getServer().getConsoleSender().sendMessage(enable_msg); // confirm plugin is enabled			
    }
    
    @Override
    public void onDisable() {
  	getServer().getConsoleSender().sendMessage(disable_msg); // confirm plugin is disabled
    }

    @EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) { // create board when player joins		
		createBoard(e.getPlayer());
		start(e.getPlayer());
	}
	
	@EventHandler  
	public void onPlayerQuit(PlayerQuitEvent e) { // stop board when the player quits
		LobbyBoard board = new LobbyBoard(e.getPlayer().getUniqueId());
		if(board.hasID()) { board.stop(); }
	}
   
    public void start(final Player p) { // animation function
    	
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			int count = 0; // variable used to count position in "animation" 
			LobbyBoard board = new LobbyBoard(p.getUniqueId()); // create new board for player 
			
			@Override
			public void run() {
				
				if(!board.hasID()) { board.setID(taskID); } // no id assigned, set id
				if(count == 11) { count = 0; } // reset counter (reset animation) 
				switch(count) { // begin animation (each case sets a new title to make the title appear animated)
				case 0:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lS&9&lerver &9&l1.18.1")); // s
					break;
				case 1:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lS&b&le&9&lrver &9&l1.18.1")); // e
					break;
				case 2:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lSe&b&lr&9&lver &9&l1.18.1")); // r
					break;
				case 3:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lSer&b&lv&9&ler &9&l1.18.1")); // v
					break;
				case 4:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lServ&b&le&9&lr &9&l1.18.1")); // e
					break;
				case 5:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lServe&b&lr &9&l1.18.1")); // r
					break;
				case 6:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lServer &b&l1&9&l.18.1")); // 1
					break;
				case 7:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lServer &9&l1.&b&l1&9&l8.1")); // 1
					break;
				case 8:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lServer &9&l1.1&b&l8&9&l.1")); // 8
					break;
				case 9:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lServer &9&l1.18.&b&l1")); // 1
					break;
				case 10:
					p.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9&lServer &9&l1.18.1")); // origin 
					createBoard(p); // update stats on playerboard
					break;
				}
				count++;
			}
			
		}, 0, 10); // update every 10ticks (0.5sec)
	}

	public void createBoard(Player user) { // create playerboard
		
		int userKills = user.getStatistic(Statistic.PLAYER_KILLS) + user.getStatistic(Statistic.MOB_KILLS); // get players kills (mobs and users)
		int userDeaths = user.getStatistic(Statistic.DEATHS); // get player deaths 
		int min = user.getStatistic(Statistic.PLAY_ONE_MINUTE)/(20*60); // calculate minutes played
		String timePlayedFORMATTED = min/24/60 + "D " + min/60%24 + "H " + min%60 + "M"; // used minutes played to format into D:H:M 
		int distanceTraveled = (user.getStatistic(Statistic.SPRINT_ONE_CM) + user.getStatistic(Statistic.SWIM_ONE_CM)+user.getStatistic(Statistic.WALK_ON_WATER_ONE_CM)+
		user.getStatistic(Statistic.WALK_ONE_CM)+user.getStatistic(Statistic.WALK_UNDER_WATER_ONE_CM)+user.getStatistic(Statistic.FLY_ONE_CM))/100; // distance player has traveled
		
		ScoreboardManager manager = Bukkit.getScoreboardManager(); // create new manager
		Scoreboard board = manager.getNewScoreboard(); // create new board 
		Objective obj = board.registerNewObjective("PlayerScoreBoard-1", "dummy", ChatColor.translateAlternateColorCodes('&', "&9&lServer &9&l1.18.1")); // create objective
		obj.setDisplaySlot(DisplaySlot.SIDEBAR); // set position on users screen
		
		// set statistics on board (will show in order in game)
		Score score = obj.getScore(ChatColor.WHITE + "=-=-=-=-=-=-=-=-=-=-="); 
		score.setScore(4);
		Score score2 = obj.getScore(ChatColor.GOLD + "Total Kills: " + ChatColor.WHITE + userKills);
		score2.setScore(3);
		Score score3 = obj.getScore(ChatColor.GOLD + "Total Deaths: " + ChatColor.WHITE + userDeaths);
		score3.setScore(2);
		Score score4 = obj.getScore(ChatColor.GOLD + "Time Played: " + ChatColor.WHITE + timePlayedFORMATTED);
		score4.setScore(1);
		Score score5 = obj.getScore(ChatColor.GOLD + "Blocks Traveled: " + ChatColor.WHITE + distanceTraveled);
		score5.setScore(0);
		
		user.setScoreboard(board); // set created board to player	
	}
}
