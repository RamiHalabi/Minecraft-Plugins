package io.GitHub.RamiHalabi.Warps;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class setWarp implements CommandExecutor {
	
	private static final String error_msg = ChatColor.RED + "Only players can use this command!";
	private static final String args_msg = ChatColor.RED + "Specify the new warp name! /setWarp <warpName>";
	private static final String success_msg = ChatColor.AQUA + "Warp created successfully";
	private static final String setWarp = "setWarp";
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) { sender.sendMessage(error_msg); return true; } // verify player
		
		Player user = (Player) sender; // get player
		
		if(command.getName().equalsIgnoreCase(setWarp )) { // trying to set a new warp
			
			if(args.length != 1) { user.sendMessage(args_msg); return true; } // no warp specified
			
			DataManager data = Main.getInstance(); // fetch config file
			Location newWarp = user.getLocation(); // get current location
			data.getConfig().set("warps."+args[0], newWarp); // write new location to config file
			data.saveConfig(); // save new data
			user.sendMessage(success_msg); // confirm home has been set		
		}
		
		return true;
	}

}
