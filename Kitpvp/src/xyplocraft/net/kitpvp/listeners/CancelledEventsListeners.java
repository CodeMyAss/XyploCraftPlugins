package xyplocraft.net.kitpvp.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.utils.Lists;

@SuppressWarnings("deprecation")
public class CancelledEventsListeners implements Listener
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	Kitpvp main = Kitpvp.plugin;
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onProjectileHitEvent(ProjectileHitEvent e)
	{
		if (((e.getEntity() instanceof Arrow)) && ((e.getEntity().getShooter() instanceof Player)))
		{
			e.getEntity().remove();
	    }
	}
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onUpRoot(PlayerInteractEvent e)
	{
		if ((e.getAction() == Action.PHYSICAL) && (e.getClickedBlock().getType() == Material.SOIL))
		{
			e.setCancelled(true);
	    }
	}
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerBedEnterEvent(PlayerBedEnterEvent e)
	{
		e.setCancelled(true);
	}
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerEditBookEvent(PlayerEditBookEvent e)
	{
		e.setCancelled(true);
	}
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onBlockBreakEvent(BlockBreakEvent e)
	{
		if (!Lists.editMode.contains(e.getPlayer().getName()))
		{
			e.setCancelled(true);
	    } else {
	    	e.setCancelled(false);
	    }
	}
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onBlockPlaceEvent(BlockPlaceEvent e)
	{
		if(!Lists.editMode.contains(e.getPlayer().getName()))
		{
			e.setCancelled(true);
	    } else {
	    	e.setCancelled(false);
	    }
	}
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onEntityExplodeEvent(EntityExplodeEvent e)
	{
		e.setCancelled(true);
	}
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onFoodLevelChangeEvent(FoodLevelChangeEvent e)
	{
		if (e.getFoodLevel() <= 18)
		{
			e.setCancelled(true);
	    }
	}
	
}
