package xyplocraft.net.kitpvp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.utils.Lists;

public class PlayerQuitEventListener implements Listener
{
	
	Kitpvp main = Kitpvp.plugin;
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerQuitEvent(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
	    Lists.selectedKit.remove(p.getName());
	    Lists.starterKit.remove(p.getName());
	    Lists.mercenaryKit.remove(p.getName());
	    Lists.MercenarySugar.remove(p.getName());
		e.setQuitMessage(null);
	}
	
}
