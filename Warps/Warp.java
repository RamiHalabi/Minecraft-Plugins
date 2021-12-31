package io.GitHub.RamiHalabi.Warps;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {
	
	private static final String error_msg = ChatColor.RED + "Only players can use this command!";
	private static final String noWarp = ChatColor.RED + "Warp does not exist";
	private static final String Warp = "Warp";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(!(sender instanceof Player)) { sender.sendMessage(error_msg); return true; } // verify player
		
		Player user = (Player) sender; // get player
		
		if(command.getName().equalsIgnoreCase(Warp)) { // player is trying to warp
			
			DataManager data = Main.getInstance(); // fetch config file to get servers saved /warp <warpname>
			ConfigurationSection warplist = data.getConfig().getConfigurationSection("warps."); // get path to all warps					
			Set<String> keys = warplist.getKeys(false); // contains all warp locations
					
			if(args.length == 0) { // no warp specified

				user.sendMessage("Warp locations: " + ChatColor.GOLD + keys.toString());  					
				return true; 			
			} 
		
			Location warpLoc = data.getConfig().getLocation("warps."+args[0]); // fetch location player wants to warp to 
			
			if(warpLoc == null) { user.sendMessage(noWarp); return true; } // warp does not exist
			
			String success_msg = ChatColor.AQUA + "Warped to " + args[0];
			user.teleport(warpLoc); // teleport player
			user.sendMessage(success_msg); // confirmation msg			
		}
		
		return true;
	}

}
