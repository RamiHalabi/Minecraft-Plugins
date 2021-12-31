package io.GitHub.RamiHalabi.GeneralCommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
		
	@EventHandler
	public static void greetPlayer(PlayerJoinEvent e) {
		
		String msg = ChatColor.AQUA + "Welcome to the server " + e.getPlayer().getDisplayName() +"! :D";

		Player user = e.getPlayer();
		user.sendMessage(msg); // greet player
	}
}
