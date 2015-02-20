package loonyrules.co.uk.bano;

import java.sql.Connection;
import java.util.List;

import loonyrules.co.uk.bano.anticheat.FlyDetection;
import loonyrules.co.uk.bano.anticheat.ForcefieldDetection;
import loonyrules.co.uk.bano.anticheat.XrayDetection;
import loonyrules.co.uk.bano.antiswear.AntiSwear;
import loonyrules.co.uk.bano.antiswear.ChatEvent;
import loonyrules.co.uk.bano.cmds.BanCommand;
import loonyrules.co.uk.bano.cmds.BanoCommand;
import loonyrules.co.uk.bano.cmds.FlyCommand;
import loonyrules.co.uk.bano.cmds.MuteCommand;
import loonyrules.co.uk.bano.cmds.RefreshDBCommand;
import loonyrules.co.uk.bano.cmds.SetPointsCommand;
import loonyrules.co.uk.bano.cmds.SetRankCommand;
import loonyrules.co.uk.bano.cmds.UnbanCommand;
import loonyrules.co.uk.bano.listeners.KnockBackListener;
import loonyrules.co.uk.bano.listeners.PlayerLoginEvent;
import loonyrules.co.uk.bano.listeners.PreLoginEvent;
import loonyrules.co.uk.bano.listeners.WeatherListener;
import loonyrules.co.uk.bano.sql.MySQL;
import loonyrules.co.uk.bano.sql.SQLD;
import loonyrules.co.uk.bano.timer.DisplayNameRefresher;
import loonyrules.co.uk.bano.utils.Functions;
import loonyrules.co.uk.bano.vc.VersionChecker;
import loonyrules.co.uk.bano.yml.AntiSwearYML;
import loonyrules.co.uk.bano.yml.FlyYML;
import loonyrules.co.uk.bano.yml.ForcefieldYML;
import loonyrules.co.uk.bano.yml.MutedYML;
import loonyrules.co.uk.bano.yml.PermsYML;
import loonyrules.co.uk.bano.yml.XrayYML;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitWorker;
import org.yaml.snakeyaml.error.YAMLException;

public class Core extends JavaPlugin
{
	
	public static Core plugin;
	public static PluginManager pm;
	public BukkitScheduler bs;
	public Connection c;
	public double version = 1.1;
	
	@Override
	public void onEnable()
	{
		plugin = this;
		pm = Bukkit.getPluginManager();
		bs = Bukkit.getScheduler();
		
		try {
			MySQL SQL = new MySQL(SQLD.host, SQLD.port, SQLD.db, SQLD.user, SQLD.pw);
			c = SQL.open();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			getConfig().options().copyDefaults(true);
			saveConfig();
			
			PermsYML.getPerms().options().copyDefaults(true);
			PermsYML.savePerms();
			
			FlyYML.getFly().options().copyDefaults(true);
			FlyYML.saveFly();
			
			ForcefieldYML.getForcefield().options().copyDefaults(true);
			ForcefieldYML.saveForcefield();
			
			XrayYML.getXray().options().copyDefaults(true);
			XrayYML.saveXray();
			
			AntiSwearYML.getAS().options().copyDefaults(true);
			AntiSwearYML.saveAS();
			
			MutedYML.getMuted().options().copyDefaults(true);
			MutedYML.saveMuted();
			
			for (Player p : Bukkit.getOnlinePlayers()) 
			{
				Functions.MANUAL_RESET_DISPAY_NAMES(p);
			}
		} catch(YAMLException yex) {
			yex.printStackTrace();
		}
		
		if(getConfig().getBoolean("fly.enabled"))
		{
			 pm.registerEvents(new FlyDetection(this), this);
			 FlyDetection.scheduleRemoval();
		}
		
		if(getConfig().getBoolean("ff.enabled"))
		{
			pm.registerEvents(new ForcefieldDetection(this), this);
			ForcefieldDetection.scheduleRemoval();
		}
		
		if(getConfig().getBoolean("xray.enabled"))
		{
			pm.registerEvents(new XrayDetection(this), this);
			XrayDetection.scheduleRemoval();
		}
		
		if(getConfig().getBoolean("anti-swear.enabled"))
		{
			pm.registerEvents(new AntiSwear(), this);
		}
		
		if(getConfig().getBoolean("general.stopWeather"))
		{
			pm.registerEvents(new WeatherListener(), this);
		}
		
		regListeners();
		regCommands();
		regTimers();
	}
	
	@Override
	public void onDisable()
	{
		c = MySQL.closeConnection(c);
		List<BukkitWorker> bw = bs.getActiveWorkers();
		for(BukkitWorker lbw : bw)
		{
			bs.cancelTask(lbw.getTaskId());
		}
	}
	
	private void regTimers()
	{
		bs.runTaskTimerAsynchronously(this, new DisplayNameRefresher(), 0L, 20*300);
	}
	
	private void regListeners()
	{
		pm.registerEvents(new AntiSwear(), this);
		pm.registerEvents(new ChatEvent(), this);
		pm.registerEvents(new PreLoginEvent(), this);
		pm.registerEvents(new PlayerLoginEvent(), this);
		pm.registerEvents(new KnockBackListener(), this);
	}
	
	private void regCommands()
	{
		getCommand("setrank").setExecutor(new SetRankCommand());
		getCommand("setpoints").setExecutor(new SetPointsCommand());
		getCommand("ban").setExecutor(new BanCommand());
		getCommand("unban").setExecutor(new UnbanCommand());
		getCommand("pardon").setExecutor(new UnbanCommand());
		getCommand("bano").setExecutor(new BanoCommand());
		getCommand("mute").setExecutor(new MuteCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("refreshdb").setExecutor(new RefreshDBCommand());
	}
	
}
