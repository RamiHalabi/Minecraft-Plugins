package io.GitHub.RamiHalabi.GeneralCommands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class GoodDay implements CommandExecutor {

	private static final String error_msg = ChatColor.RED + "Only players can use this command!";
	private static final String success_msg = ChatColor.AQUA + "Its a good day!";
	private static final String GoodDay = "GoodDay";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player))  // verify player			
			sender.sendMessage(error_msg); // not a player, send message
		
		if(command.getName().equalsIgnoreCase(GoodDay )) { // /GoodDay
			
			List<World> worlds = Bukkit.getWorlds();
			
			for (World w : worlds) { // change time and weather in each world 	
				w.setTime(0);
				w.setClearWeatherDuration(2000);
			}
			
			Player user = (Player) sender; // get player 
			user.sendMessage(success_msg); // confirm changes 
			return true;
		}
		
		return true;		
	}
}
