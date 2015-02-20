package xyplocraft.net.bano;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import xyplocraft.net.bano.permissions.PermissionUtils;

public class Bano extends JavaPlugin
{
	
	private static Bano plugin;
	private PluginManager pm;
	BukkitScheduler bs;
	PermissionUtils pUtils;
	
	@Override
	public void onEnable()
	{
		setPlugin(this);
		setPm(Bukkit.getPluginManager());
		bs = Bukkit.getScheduler();
		pUtils = PermissionUtils.getPermissionUtils();
		pUtils.registerClass();
	}

	@Override
	public void onDisable()
	{
		pUtils.clearPlayerAttachments();
	}
	
	public static Bano getPlugin()
	{
		return plugin;
	}

	public static void setPlugin(Bano plugin)
	{
		Bano.plugin = plugin;
	}

	public PluginManager getPm() {
		return pm;
	}

	public void setPm(PluginManager pm) {
		this.pm = pm;
	}
	
}
