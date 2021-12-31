package io.GitHub.RamiHalabi.Warps;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Back implements CommandExecutor {
	
	private static final String error_msg = ChatColor.RED + "Only players can use this command!";
	private static final String error_null = ChatColor.RED +"No previous location found";
	private static final String back = "back";
	private static final String backMsg = ChatColor.AQUA +"Sent back!";


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) { sender.sendMessage(error_msg); return true; } // verify player
		
		Player user = (Player) sender; // get player
		
		if(command.getName().equalsIgnoreCase(back)) {
			
			DataManager data = Main.getInstance(); // fetch config file 
			Location prevLoc = data.getConfig().getLocation("back."+user.getUniqueId()); // get previous location in config.
			
			if(prevLoc == null) { user.sendMessage(error_null); return true; } // previousLoc is null
			
			user.teleport(prevLoc); // teleport player
			user.sendMessage(backMsg); // confirm teleport
		}		
		return true;
	}

}
