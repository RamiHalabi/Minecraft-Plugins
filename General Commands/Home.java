package io.GitHub.RamiHalabi.GeneralCommands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Home implements CommandExecutor {

	private static final String error_msg = ChatColor.RED + "Only players can use this command!";
	private static final String errornoHome = ChatColor.RED + "Home does not exist";
	private static final String teleportConfirm = ChatColor.GOLD +"Teleported home"; 
	private static final String home = "home";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) { sender.sendMessage(error_msg); return true; } // verify player
		
		Player user = (Player) sender; // get player		
		
		// /home 
		if(command.getName().equalsIgnoreCase(home)) { // trying to teleport to home
			
			DataManager data = Main.getInstance(); // fetch config file to get players' saved /home
			Location homeLoc = data.getConfig().getLocation("players."+user.getUniqueId().toString()+".home"); 
						
			if(homeLoc == null) { user.sendMessage(errornoHome); return true; } // home does not exist

			user.teleport(homeLoc) ; // teleport user to home
			user.sendMessage(teleportConfirm); // send confirmation msg 			
		}
		
		return true;
	}

}
