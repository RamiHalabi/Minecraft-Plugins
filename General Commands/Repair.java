package io.GitHub.RamiHalabi.GeneralCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class Repair implements CommandExecutor {
	
	private static final String error_msg = ChatColor.RED + "Only players can use this command!";
	private static final String noItem_msg = ChatColor.RED + "You must be holding something repairable!";
	private static final String success_msg = ChatColor.GREEN + "Repaired successfully";
	private static final String no_meta = ChatColor.RED + "Item cannot be repaired";
	private static final int zeroDamage = 0;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) { // verify player
			
			sender.sendMessage(error_msg); // not a player, send message			 
			
		} else { 
			
			Player user = (Player) sender; // get player
			ItemStack item = user.getInventory().getItemInMainHand(); // get item to repair
			
			if(item.getItemMeta() == null) { user.sendMessage(noItem_msg); return true; }
			
			ItemMeta meta = item.getItemMeta(); // get items meta data						
			int dmg = ((Damageable) meta).getDamage();
			
			if(dmg > 0) { // item has taken damage
				
				((Damageable) meta).setDamage(zeroDamage); // set new damage in items metadata
				item.setItemMeta(meta);	//update metadata
				user.sendMessage(success_msg); // confirm repair
				
			} else { // item is not damaged
				
				user.sendMessage(no_meta);				
			}					
		}
			
		return true;
	}

}
