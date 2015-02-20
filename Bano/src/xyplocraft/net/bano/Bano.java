package xyplocraft.net.bano;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Bano extends JavaPlugin
{
	
	private static Bano plugin;
	PluginManager pm;
	BukkitScheduler bs;
	
	@Override
	public void onEnable()
	{
		setPlugin(this);
		pm = Bukkit.getPluginManager();
		bs = Bukkit.getScheduler();
	}

	public static Bano getPlugin()
	{
		return plugin;
	}

	public static void setPlugin(Bano plugin)
	{
		Bano.plugin = plugin;
	}
	
}
