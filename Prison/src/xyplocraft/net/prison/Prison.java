package xyplocraft.net.prison;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import xyplocraft.net.prison.warps.Warps;

public class Prison extends JavaPlugin
{
	
	Prison plugin;
	PluginManager pm;
	BukkitScheduler bs;
	Warps warps;
	
	@Override
	public void onEnable()
	{
		setPlugin(this);
		setPluginManager(Bukkit.getPluginManager());
		setBukkitScheduler(Bukkit.getScheduler());
		setWarps(new Warps());
	}
	
	@Override
	public void onDisable()
	{
		
	}

	/*
	 * Getters and setters
	 */
	
	public static Prison getPlugin()
	{
		return new Prison();
	}
	
	public void setPlugin(Prison prison)
	{
		plugin = prison;
	}
	
	public PluginManager getPluginManager()
	{
		return pm;
	}
	
	public void setPluginManager(PluginManager pluginmanager)
	{
		pm = pluginmanager;
	}
	
	public BukkitScheduler getBukkitScheduler()
	{
		return bs;
	}
	
	public void setBukkitScheduler(BukkitScheduler bukkitscheduler)
	{
		bs = bukkitscheduler;
	}
	
	public Warps getWarps()
	{
		return warps;
	}
	
	public void setWarps(Warps warp)
	{
		warps = warp;
	}
	
	/*
	public Bano getBano()
	{
		return bano;
	}
	
	public void setBano(Bano banoplugin)
	{
		bano = banoplugin;
	}
	*/
}
