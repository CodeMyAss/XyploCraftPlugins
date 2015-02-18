package xyplocraft.net.hub.listeners;

import loonyrules.co.uk.bano.Core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import xyplocraft.net.hub.utils.Scoreboards;

public class PlayerQuitEventListener implements Listener
{
	
	/**
	 * @author LoonyRules and CodeBeasty
	 */
	
	Core main = Core.plugin;
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerQuitEvent(PlayerQuitEvent e)
	{
		e.setQuitMessage(null);
		
		main.bs.runTaskLater(main, new Runnable()
		{
			public void run()
			{
				for(Player p : Bukkit.getOnlinePlayers())
				{
					Scoreboards.setupMain(p);
				}
			}
		}, 60L);
	}
	
	
}
