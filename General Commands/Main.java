package io.GitHub.RamiHalabi.GeneralCommands;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	// toggle messages
	private static final String enable = ChatColor.GREEN + "GeneralCommands enabled";
	private static final String disable = ChatColor.RED + "GeneralCommands disabled";
	
	// events 
	Events event = new Events();
	
	// commands 
	private static final String heal = "heal";
	private static final String feed = "feed";
	private static final String repair = "repair";
	private static final String home = "home";
	private static final String setHome = "setHome"; 
	private static final String GoodDay = "GoodDay";
	private static final String gm = "gm";
	
	
	Heal heal_class = new Heal();
	Feed feed_class = new Feed();
	Repair repair_class = new Repair();
	Home home_class = new Home();
	setHome setHome_class = new setHome();
	GoodDay GoodDay_class = new GoodDay();
	gm gm_class = new gm();
	
	public static DataManager data;
	
	@Override
    public void onEnable() {
		
		Main.data = new DataManager(this); // load DataManager for config files 
		
		getServer().getPluginManager().registerEvents(event, this); // load events		
		getCommand(heal).setExecutor(heal_class); // load heal command 
		getCommand(feed).setExecutor(feed_class); // load feed command 
		getCommand(repair).setExecutor(repair_class); // load feed command 
		getCommand(home).setExecutor(home_class); // load home command
		getCommand(setHome).setExecutor(setHome_class); // load sethome command
		getCommand(GoodDay).setExecutor(GoodDay_class); // load GoodDay command
		getCommand(gm).setExecutor(gm_class); // load GameMode command (shorter implementation)
		
		getServer().getConsoleSender().sendMessage(enable); // confirm plugin is enabled
				
    }
    
    @Override
    public void onDisable() {
        
    	getServer().getConsoleSender().sendMessage(disable); // confirm plugin is disabled
    } 
    
    public static DataManager getInstance() {
        return data;
    } 
}
