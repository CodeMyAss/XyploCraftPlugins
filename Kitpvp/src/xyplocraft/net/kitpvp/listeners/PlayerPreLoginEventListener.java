package xyplocraft.net.kitpvp.listeners;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;

import xyplocraft.net.kitpvp.Kitpvp;

@SuppressWarnings("deprecation")
public class PlayerPreLoginEventListener implements Listener
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	Kitpvp main = Kitpvp.plugin;
	
	@EventHandler
	public void onPlayerPreLoginEvent(PlayerPreLoginEvent e)
	{
		try {
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE uuid= '" + e.getUniqueId() + "';");
			if (!res.next())
			{
		        main.c.createStatement().executeUpdate("INSERT INTO `kitpvp_data` (`uuid`, `ign`, `kills`, `deaths`, `assists`, `highestks`, `points`, `supplycrates`) VALUES ('" + e.getUniqueId() + "', '" + e.getName() + "', '0', '0', '0', '0', '0', '0');");
		    }
		} catch(SQLException sql) {}
	}
	
}
