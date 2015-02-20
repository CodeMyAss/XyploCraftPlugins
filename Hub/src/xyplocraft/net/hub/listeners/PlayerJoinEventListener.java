package xyplocraft.net.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import xyplocraft.net.hub.utils.Locations;
import xyplocraft.net.hub.utils.Inventorys;
import xyplocraft.net.hub.utils.Scoreboards;

public class PlayerJoinEventListener implements Listener
{
	
	/**
	 * @author LoonyRules and CodeBeasty
	 */
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoinEvent(PlayerJoinEvent e)
	{
		e.setJoinMessage(null);
		Locations.teleportToSpawn(e.getPlayer());
		Inventorys.setupMain(e.getPlayer());
		for(Player p : Bukkit.getOnlinePlayers())
		{
			Scoreboards.setupMain(p);
		}
	}
	
}
