package io.GitHub.RamiHalabi.Warps;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import io.GitHub.RamiHalabi.Warps.Main;

public class Main extends JavaPlugin {
	
	// toggle messages
	private static final String enable_msg = ChatColor.GREEN + "Warps enabled";
	private static final String disable_msg = ChatColor.RED + "Warps disabled";
	
	// custom config manager
	public static DataManager data;
	
	// commands
	private static final String warp = "warp";
	private static final String setWarp = "setWarp";
	private static final String delWarp = "delWarp";
	private static final String back = "back";
	
	Warp warp_class = new Warp();
	setWarp setWarp_class = new setWarp();
	delWarp delWarp_class = new delWarp();
	Back back_class = new Back();
	Events warpEvents = new Events();
	
	@Override
    public void onEnable() {
		
		Main.data = new DataManager(this); // load DataManager for config files
		getServer().getPluginManager().registerEvents(warpEvents, this); // load events
		getCommand(warp).setExecutor(warp_class); // load warp command 
		getCommand(setWarp).setExecutor(setWarp_class); // load setWarp command 
		getCommand(delWarp).setExecutor(delWarp_class); // load delWarp command
		getCommand(back).setExecutor(back_class); // load warp command 
		getServer().getConsoleSender().sendMessage(enable_msg); // confirm plugin is enabled			
    }
    
    @Override
    public void onDisable() {
        
    	getServer().getConsoleSender().sendMessage(disable_msg); // confirm plugin is disabled
    }
    
    public static DataManager getInstance() {
        return data;
    }

}
