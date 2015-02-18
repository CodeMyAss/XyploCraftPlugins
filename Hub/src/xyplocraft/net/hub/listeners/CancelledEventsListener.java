package xyplocraft.net.hub.listeners;

import loonyrules.co.uk.bano.Core;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import xyplocraft.net.hub.utils.Lists;
import xyplocraft.net.hub.utils.Locations;
import xyplocraft.net.hub.utils.Strings;

public class CancelledEventsListener implements Listener
{

	Core main = Core.plugin;
	
	@EventHandler (priority=EventPriority.HIGH)
	public void onCreatureSpawnEvent(CreatureSpawnEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onFoodLevelChangeEvent(FoodLevelChangeEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onPlayerRespawnEvent(PlayerRespawnEvent e)
	{
		Locations.teleportToSpawn(e.getPlayer());
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onBlockPlaceEvent(BlockPlaceEvent e)
	{
		if(!Lists.editMap.contains(e.getPlayer()))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onBlockBreakEvent(BlockBreakEvent e)
	{
		if(!Lists.editMap.contains(e.getPlayer()))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onPlayerDropItemEvent(PlayerDropItemEvent e)
	{
		if(!Lists.editMap.contains(e.getPlayer()))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent e)
	{
		if(!Lists.editMap.contains(e.getPlayer()))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onPlayerRespawnEvent(PlayerInteractEvent e)
	{
		if(e.getAction() == null 
				|| e.getItem() == null 
				|| e.getItem().getType() == null 
				|| e.getItem().getType() == Material.AIR
				|| e.getAction() == Action.PHYSICAL && Lists.stopBlocksInteract.contains(e.getClickedBlock().getType()))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onPlayerBedEnterEvent(PlayerBedEnterEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onPlayerEditBookEvent(PlayerEditBookEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onEntityExplodeEvent(EntityExplodeEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onInventoryClickEvent(InventoryClickEvent e)
	{
		e.setCancelled(true);
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onInventoryOpenEvent(InventoryOpenEvent e)
	{
		if(e.getInventory().getTitle().equalsIgnoreCase(Strings.getNavigationTitle()) 
				|| e.getInventory().getTitle().equalsIgnoreCase(Strings.getExtrasTitle()) 
				|| e.getInventory().getTitle().equalsIgnoreCase(Strings.getPlayerstatsTitle()))
		{
			return;
		} else e.setCancelled(true);
	}
	
}
