package io.GitHub.RamiHalabi.GeneralCommands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setHome implements CommandExecutor {
	
	private static final String setHome = "setHome";
	private static final String success_msg = ChatColor.GREEN + "Home set";
	private static final String error_msg = ChatColor.RED + "Only players can use this command!";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(!(sender instanceof Player)) { sender.sendMessage(error_msg); return true; } // verify player
		
		Player user = (Player) sender; // get player
		
		// /sethome 
		if(command.getName().equalsIgnoreCase(setHome)) { // trying to set a new home 
			
				DataManager data = Main.getInstance(); // fetch config file
				Location newHome = user.getLocation(); // get current location 
				data.getConfig().set("players."+user.getUniqueId().toString()+".home", newHome); // write new location to config file
				data.saveConfig(); // save new data
				user.sendMessage(success_msg); // confirm home has been set				
		}
		
		return true;
	}
		
}

