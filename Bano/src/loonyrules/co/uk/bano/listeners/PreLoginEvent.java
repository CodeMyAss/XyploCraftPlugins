package loonyrules.co.uk.bano.listeners;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Lists;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent.Result;

@SuppressWarnings("deprecation")
public class PreLoginEvent implements Listener
{

	static Core main = Core.plugin;

	@EventHandler (priority=EventPriority.HIGHEST)
	public void onPlayerPreLoginEvent(PlayerPreLoginEvent e)
	{
		UUID uuid = e.getUniqueId();
		String name = e.getName();
	
		// Bans
		try
		{
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `bano_bans` WHERE bannedUUID= '" + uuid + "';");
			if(res.next())
			{
				String reason = res.getString("reason");
				String banner = res.getString("bannerIGN");
				e.disallow(Result.KICK_BANNED, ChatColor.GRAY + "You were banned by " + ChatColor.GOLD + banner + ChatColor.GRAY + "." + ChatColor.LIGHT_PURPLE + " Reason: " + ChatColor.GREEN + reason);
			} 
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		//
		
		// First Join
		String rank = null;
		try {
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM users WHERE uuid= '" + e.getUniqueId() + "';");
			if(!res.next())
			{
				main.c.createStatement().executeUpdate("INSERT INTO `users` (`uuid`, `ign`, `rank`, `points`) VALUES ('" + e.getUniqueId() +"', '" + e.getName() + "', '" + "User" + "', '0');");
				Lists.playersRanks.put(uuid, "default");
				rank = res.getString("default");
			} 
			if(res.next())
			{
				rank = res.getString("rank");
				Lists.playersRanks.put(uuid, rank.toLowerCase());
			}
		} catch(SQLException sql) {
			sql.printStackTrace();
		}
		//
		
		String cmd = "pex user " + name + " group set " + rank;
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
			
	}
	
}
