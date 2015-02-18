package xyplocraft.net.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.utils.Lists;

public class PlayerDropPickupItemEventListener implements Listener
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	Kitpvp main = Kitpvp.plugin;
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerDropItemEvent(PlayerDropItemEvent e)
	{
		if(!Lists.editMode.contains(e.getPlayer().getName()))
		{
			e.setCancelled(true);
		}
	}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent e)
	{
		if(!Lists.editMode.contains(e.getPlayer().getName()))
		{
			e.setCancelled(true);
		}
	}
	
}
