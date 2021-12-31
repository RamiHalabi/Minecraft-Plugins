package io.GitHub.RamiHalabi.Warps;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import net.md_5.bungee.api.ChatColor;

/*
 *  We need listeners to three events in the warp class to ensure /back works as intended
 *  
 *  	1. When a player dies: new location
 *  	2. When a player teleports: new location
 *  	3. When a player Leaves: clear user data  (players cannot do /back as soon as they log in without dying or teleporting first)
 *  
 */
public class Events implements Listener {


	@EventHandler //player dies, save last location to access when calling /back
	public static void onPlayerDie(PlayerRespawnEvent e) {
		
		DataManager data = Main.getInstance(); // fetch config file
		Location previous = e.getPlayer().getLocation(); // get players previous location 
		data.getConfig().set("back."+e.getPlayer().getUniqueId(), previous); // write players previous location to config file
		data.saveConfig(); // save new data
		e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "New /back: user died event");
	}
	
	@EventHandler //player teleports, save last location to access when calling /back
	public static void onPlayerTeleport(PlayerTeleportEvent e) throws InterruptedException {	

			Thread.sleep(100); // make sure that the player teleports before handling saving a back location (otherwise /back might save twice and overwrite the 1st location)
			DataManager data = Main.getInstance(); // fetch config file
			Location previous = e.getFrom(); // get players previous location 
			data.getConfig().set("back."+e.getPlayer().getUniqueId(), previous); // write players previous location to config file
			data.saveConfig(); // save new data 
			e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "New /back: user teleported");
	}
	
	@EventHandler //player leaves server, delete user data
	public static void onPlayerLeave(PlayerQuitEvent e) {
		
		DataManager data = Main.getInstance(); // fetch config file 
		data.getConfig().set("back."+e.getPlayer().getUniqueId(), null); // write players previous location to config file
		data.saveConfig(); // save new data
		
	}
	
	@EventHandler //player gets kicked server, delete user data
	public static void onPlayerKicked(PlayerKickEvent e) {
		
		DataManager data = Main.getInstance(); // fetch config file 
		data.getConfig().set("back."+e.getPlayer().getUniqueId(), null); // write players previous location to config file
		data.saveConfig(); // save new data 	
	}
	
}
