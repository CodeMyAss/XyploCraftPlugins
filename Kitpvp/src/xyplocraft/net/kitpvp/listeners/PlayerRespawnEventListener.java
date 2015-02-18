package xyplocraft.net.kitpvp.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.utils.Locations;
import xyplocraft.net.kitpvp.utils.Scoreboards;

public class PlayerRespawnEventListener implements Listener
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	Kitpvp main = Kitpvp.plugin;
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerRespawnEvent(PlayerRespawnEvent e)
	{
		e.setRespawnLocation(Locations.getSpawn());
	    e.getPlayer().setGameMode(GameMode.ADVENTURE);
	    e.getPlayer().setMaxHealth(20.0);
	    e.getPlayer().setHealth(20.0);
	    e.getPlayer().setFoodLevel(20);
	    e.getPlayer().setExp(0.0F);
	    e.getPlayer().setLevel(0);
	    e.getPlayer().setWalkSpeed(0.2F);
	    for (PotionEffect pe : e.getPlayer().getActivePotionEffects())
	    {
	      e.getPlayer().removePotionEffect(pe.getType());
	    }
	    Scoreboards.setupMain(e.getPlayer());
	}
	
}
