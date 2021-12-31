package io.GitHub.RamiHalabi.GeneralCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;

public class gm implements CommandExecutor {
	
	private static final String error_msg = ChatColor.RED + "Only players can use this command!";
	private static final String error_args = ChatColor.RED + "Specify which GameMode you want! Creative=1 and Survival=0";
	private static final String success_msg = ChatColor.GOLD + "GameMode changed";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player))  // verify player			
			sender.sendMessage(error_msg); // not a player, send message
		
		Player user = (Player) sender; // get player
		
		if(args.length != 1) { user.sendMessage(error_args); return true;} // args are not correct	
		
		switch(args[0]) { // set gamemode
		case "1":
			user.setGameMode(GameMode.CREATIVE);
			break;
		case "c":
			user.setGameMode(GameMode.CREATIVE);
			break;
		case "0":
			user.setGameMode(GameMode.SURVIVAL);
			break;
		case "s":
			user.setGameMode(GameMode.SPECTATOR);
			break;
		case "a":
			user.setGameMode(GameMode.ADVENTURE);
			break;
		}

		user.sendMessage(success_msg); // confirm gamemode change				
		return true;
	}

}
