package xyplocraft.net.bano;

import net.nanon.networkutilities.plugin.PluginInstance;

import org.bukkit.plugin.PluginManager;

import xyplocraft.net.bano.permissions.PermissionsHandler;

public class Bano extends PluginInstance {
	
	private PermissionsHandler permHandle;
	
	public Bano(String plugin_name, String plugin_version, String[] plugin_contributors) {
		super(plugin_name, plugin_version, plugin_contributors);
	}

	@Override
	public void onPluginInit() {
		
	}

	@Override
	public void onPluginEnable() {
		
	}

	@Override
	public void onPluginDisable() {
		
	}

	@Override
	public void registerListeners(PluginManager manager) {
		
	}

	@Override
	public void registerCommands(PluginManager manager) {
		
	}

	@Override
	public void registerTimers(PluginManager manager) {
		
	}
}

class References {
	private final static String plugin_name = "Bano";
	private final static String plugin_version = "0.1-Development";
	
	private final static String[] developers = new String[] {"LoonyRules", "Thecheatgamer1"};
	
	public static String getPluginName() {
		return plugin_name;
	}
	
	public static String getPluginVersion() {
		return plugin_version;
	}
	
	public static String[] getPluginDevelopers() {
		return developers;
	}
}