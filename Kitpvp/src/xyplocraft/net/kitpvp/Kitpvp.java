package xyplocraft.net.kitpvp;

import java.sql.Connection;

import loonyrules.co.uk.bano.sql.MySQL;
import loonyrules.co.uk.bano.sql.SQLD;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import xyplocraft.net.kitpvp.commands.SlashForceSDCommand;
import xyplocraft.net.kitpvp.commands.SlashModeCommand;
import xyplocraft.net.kitpvp.commands.SlashPointsCommand;
import xyplocraft.net.kitpvp.commands.SlashSpawnCommand;
import xyplocraft.net.kitpvp.commands.SlashStatsCommand;
import xyplocraft.net.kitpvp.commands.SlashTPACommand;
import xyplocraft.net.kitpvp.listeners.CancelledEventsListeners;
import xyplocraft.net.kitpvp.listeners.PlayerDeathEventListener;
import xyplocraft.net.kitpvp.listeners.PlayerDropPickupItemEventListener;
import xyplocraft.net.kitpvp.listeners.PlayerInteractSignEventListener;
import xyplocraft.net.kitpvp.listeners.PlayerJoinEventListener;
import xyplocraft.net.kitpvp.listeners.PlayerPreLoginEventListener;
import xyplocraft.net.kitpvp.listeners.PlayerQuitEventListener;
import xyplocraft.net.kitpvp.listeners.PlayerRespawnEventListener;
import xyplocraft.net.kitpvp.listeners.SupplyDropInteractListener;
import xyplocraft.net.kitpvp.managers.PlayerInteractRightClickEventManager;
import xyplocraft.net.kitpvp.timers.AssassinInvisibilityTimer;
import xyplocraft.net.kitpvp.timers.HeavyRepairTimer;
import xyplocraft.net.kitpvp.timers.MercenarySugarTimer;
import xyplocraft.net.kitpvp.timers.SupplyDropParticleTimer;
import xyplocraft.net.kitpvp.timers.SupplyDropTimer;


public class Kitpvp extends JavaPlugin
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	public static Kitpvp plugin;
	public PluginManager pm;
	public BukkitScheduler bs;
	public Connection c;
	
	@Override
	public void onEnable()
	{
		plugin = this;
		pm = Bukkit.getPluginManager();
		bs = Bukkit.getScheduler();
		
		regListeners();
		regCommands();
		regTimers();
		
		try {
			MySQL SQL = new loonyrules.co.uk.bano.sql.MySQL(SQLD.host, SQLD.port, SQLD.db, SQLD.user, SQLD.pw);
			c = SQL.open();
		} catch(Exception e) {}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onDisable()
	{
		c = MySQL.closeConnection(c);
		for(Player p : getServer().getOnlinePlayers())
		{
			p.setWalkSpeed(0.2F);
		}
	}
	
	void regListeners()
	{
		pm.registerEvents(new PlayerQuitEventListener(), this);
		pm.registerEvents(new PlayerJoinEventListener(), this);
		pm.registerEvents(new CancelledEventsListeners(), this);
		pm.registerEvents(new PlayerDeathEventListener(), this);
		pm.registerEvents(new PlayerRespawnEventListener(), this);
		pm.registerEvents(new SupplyDropInteractListener(), this);
		pm.registerEvents(new PlayerPreLoginEventListener(), this);
		pm.registerEvents(new PlayerInteractSignEventListener(), this);
		pm.registerEvents(new PlayerDropPickupItemEventListener(), this);
		pm.registerEvents(new PlayerInteractRightClickEventManager(), this);
	}
	
	void regCommands()
	{
		getCommand("stats").setExecutor(new SlashStatsCommand());
	    getCommand("tpa").setExecutor(new SlashTPACommand());
	    getCommand("mode").setExecutor(new SlashModeCommand());
	    getCommand("tpdeny").setExecutor(new SlashTPACommand());
	    getCommand("spawn").setExecutor(new SlashSpawnCommand());
	    getCommand("tpaccept").setExecutor(new SlashTPACommand());
	    getCommand("points").setExecutor(new SlashPointsCommand());
	    getCommand("forcesd").setExecutor(new SlashForceSDCommand());
	}
	
	void regTimers()
	{
		bs.runTaskTimerAsynchronously(this, new HeavyRepairTimer(), 0L, 20L);
		bs.runTaskTimerAsynchronously(this, new MercenarySugarTimer(), 0L, 20L);
		bs.runTaskTimerAsynchronously(this, new AssassinInvisibilityTimer(), 0L, 20L);
		bs.runTaskTimer(this, new SupplyDropTimer(), 0L, 20L);
		bs.runTaskTimer(this, new SupplyDropParticleTimer(), 0L, 20L);
	}
	
}
