package xyplocraft.net.hub.managers;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import xyplocraft.net.hub.listeners.ExtrasMenuListener;
import xyplocraft.net.hub.listeners.NavigationMenuListener;
import xyplocraft.net.hub.listeners.PlayerStatsMenuListener;
import xyplocraft.net.hub.utils.Strings;

public class PlayerInteractEventManager implements Listener
{
	
	/**
	 * @author LoonyRules and CodeBeasty
	 */
	
	@SuppressWarnings("incomplete-switch")
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerInteractEvent(PlayerInteractEvent e)
	{
		if(e.getItem() != null && e.getItem().getType() != null && e.getItem().getType() != Material.AIR)
		{
			switch(e.getItem().getType())
			{
				case NETHER_STAR:
					if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Strings.getNavigationDisplayName()))
					{
						if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
						{
							NavigationMenuListener.onInteract(e.getPlayer());
						}
					}
				break;
				case SKULL_ITEM:
					if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Strings.getPlayerStatsDisplayName()))
					{
						if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
						{
							PlayerStatsMenuListener.onInteract(e.getPlayer());
						}
					}
				break;
				case DRAGON_EGG:
					if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Strings.getExtrasDisplayName()))
					{
						if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
						{
							ExtrasMenuListener.onInteract(e.getPlayer());
						}
					}
				break;
			}	
		}
		e.setCancelled(true);
	}
	
}
