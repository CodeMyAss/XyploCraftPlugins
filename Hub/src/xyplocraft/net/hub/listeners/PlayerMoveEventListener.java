package xyplocraft.net.hub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import xyplocraft.net.hub.utils.Locations;
import loonyrules.co.uk.bano.Core;

public class PlayerMoveEventListener implements Listener
{
	
	Core main = Core.plugin;
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerMoveEvent(PlayerMoveEvent e)
	{
		if(e.getPlayer().getLocation().getWorld().getName().equals("hub"))
		{
			if(e.getPlayer().getLocation().getY() <= -3)
			{
				Locations.teleportToSpawn(e.getPlayer());
			}
		}
	}
	
	
}
