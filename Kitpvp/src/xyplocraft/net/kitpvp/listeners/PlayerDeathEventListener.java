package xyplocraft.net.kitpvp.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.utils.Grant;
import xyplocraft.net.kitpvp.utils.Lists;

public class PlayerDeathEventListener implements Listener
{

	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	Kitpvp main = Kitpvp.plugin;

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerDeathEvent(PlayerDeathEvent e)
	{
		e.setDeathMessage(null);
	    Player p = e.getEntity();
	    
	    Lists.selectedKit.remove(p.getName());
	    Lists.starterKit.remove(p.getName());
	    Lists.mercenaryKit.remove(p.getName());
	    Lists.MercenarySugar.remove(p.getName());
	    e.getDrops().clear();
	    
	    Location loc = p.getLocation();
	    loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 0, 50);
	    loc.getWorld().playSound(loc, Sound.ENDERDRAGON_GROWL, 1.0F, 1.0F);
	    
	    Player killer = null;
	    if ((p.getKiller() instanceof Player)) 
	    {
	    	killer = p.getKiller();
	    } else {
	    	killer = p;
	    }
	    Grant.death(p);
	    if(killer != p)
	    {
	    	p.sendMessage(ChatColor.YELLOW + "You were killed by " + ChatColor.GRAY + killer.getName());
	    	Grant.kill(killer);
	    	killer.sendMessage(ChatColor.YELLOW + "You killed " + ChatColor.GRAY + p.getName() + ChatColor.YELLOW + " and gained " + ChatColor.GRAY + "15" + ChatColor.YELLOW + " points!");
	    } else {
	    	p.sendMessage(ChatColor.YELLOW + "We don't know how you did it, but you died...");
	    }
	}
	
}
