package io.GitHub.RamiHalabi.GeneralCommands;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Heal implements CommandExecutor {

	private static final String error_msg = ChatColor.RED + "Only players can use this command!";
	private static final String success_msg = ChatColor.GREEN + "Healed successfully";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) { // verify player
						
			sender.sendMessage(error_msg); // not a player, send message			 
			
		} else { 
			
			Player user = (Player) sender; // get player 
			user.setHealth(user.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue()); // set players health to max
			user.sendMessage(success_msg); // confirm player was healed
		
		}
		
		return true;
	}

}
