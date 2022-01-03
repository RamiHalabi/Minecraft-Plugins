package io.GitHub.RamiHalabi.PlayerScoreBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

public class LobbyBoard {
	
	private static Map<UUID, Integer> tasks = new HashMap<UUID,Integer>(); // store playerboard information (link UUID to taskID)
	private final UUID uuid; 
	
	public LobbyBoard(UUID uuid) { // constructor
		this.uuid = uuid; 
	}
	
	public void setID(int id) { // set board ID
		tasks.put(uuid,id);
	}
	
	public int getID() { // get board ID
		return tasks.get(uuid);
	}
	
	public boolean hasID() { // check for board ID
		if(tasks.containsKey(uuid)) {
			return true;
		}
		return false;
	}
	
	public void stop() { // stop running board
		Bukkit.getScheduler().cancelTask(tasks.get(uuid));
		tasks.remove(uuid);
	}
}
