package io.GitHub.RamiHalabi.Warps;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delWarp implements CommandExecutor {
	
	private static final String error_msg = ChatColor.RED + "Only players can use this command!";
	private static final String success_msg = ChatColor.GREEN + "Warp deleted successfully";
	private static final String args_msg = ChatColor.RED + "Specify the warp name! /delWarp <warpName>";
	private static final String noWarp = ChatColor.RED + "Warp does not exist";
	private static final String delWarp = "delWarp";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) { sender.sendMessage(error_msg); return true; } // verify player
		
		Player user = (Player) sender; // get player		
		
		if(command.getName().equalsIgnoreCase(delWarp)) { // player is trying to warp
			
			DataManager data = Main.getInstance(); // fetch config file to get servers saved /warp <warpname>
			
			if(args.length == 0) { user.sendMessage(args_msg); return true; } // no warp specified
			
			if(data.getConfig().contains("warps."+args[0])) { // warp that is being deleted exists
				
				data.getConfig().set("warps."+args[0], null); // delete warp
				data.saveConfig(); // save new data
				user.sendMessage(success_msg);
			} 
			else { // warp does not exist
				
				user.sendMessage(noWarp);  // cannot delete warp
				return true;
			}
		}
		
		return true;
	}

}
