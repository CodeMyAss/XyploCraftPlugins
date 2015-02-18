package xyplocraft.net.hub;

import java.sql.Connection;

import loonyrules.co.uk.bano.sql.MySQL;
import loonyrules.co.uk.bano.sql.SQLD;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitScheduler;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import xyplocraft.net.hub.commands.SpawnCommand;
import xyplocraft.net.hub.listeners.CancelledEventsListener;
import xyplocraft.net.hub.listeners.DoubleJumpListener;
import xyplocraft.net.hub.listeners.ParkourSignsListener;
import xyplocraft.net.hub.listeners.PlayerMoveEventListener;
import xyplocraft.net.hub.listeners.PlayerJoinEventListener;
import xyplocraft.net.hub.listeners.PlayerQuitEventListener;
import xyplocraft.net.hub.managers.InventoryClickEventManager;
import xyplocraft.net.hub.managers.PlayerInteractEventManager;

public class Hub extends JavaPlugin implements PluginMessageListener
{
	
	/**
	 * @author LoonyRules and CodeBeasty
	 */
		
	public Hub plugin;
	public Connection c;
	public PluginManager pm;
	public BukkitScheduler bs;
	public Messenger messenger;
	public static String serverName;
	
	@Override
	public void onEnable()
	{
		plugin = this;
		bs = Bukkit.getScheduler();
		pm = Bukkit.getPluginManager();
		messenger = Bukkit.getMessenger();
		
		regListeners();
		regCommands();
		try {
			MySQL SQL = new MySQL(SQLD.host, SQLD.port, SQLD.db, SQLD.user, SQLD.pw);
			c = SQL.open();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	
	}

	@Override
	public void onDisable()
	{
		c = MySQL.closeConnection(c);
	}
	
	protected void regListeners() 
	{
		pm.registerEvents(new DoubleJumpListener(), this);
		pm.registerEvents(new ParkourSignsListener(), this);
		pm.registerEvents(new PlayerJoinEventListener(), this);
		pm.registerEvents(new PlayerQuitEventListener(), this);
		pm.registerEvents(new PlayerMoveEventListener(), this);
		pm.registerEvents(new CancelledEventsListener(), this);
		pm.registerEvents(new PlayerInteractEventManager(), this);
		pm.registerEvents(new InventoryClickEventManager(), this);
		messenger.registerOutgoingPluginChannel(plugin, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
	}
	
	protected void regCommands()
	{
		getCommand("spawn").setExecutor(new SpawnCommand());
	}

	public void onPluginMessageReceived(String channel, Player player, byte[] message)
	{
		if (!channel.equals("BungeeCord"))
		{
			return;
	    }
	    ByteArrayDataInput in = ByteStreams.newDataInput(message);
	    String subchannel = in.readUTF();
	    if (subchannel.equals("Connect"))
	    {
	    	serverName = in.readUTF();
	    }
	}
	
}
