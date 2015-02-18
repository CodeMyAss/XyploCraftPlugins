package xyplocraft.net.hub.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import xyplocraft.net.hub.utils.Lists;
import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;

public class DoubleJumpListener implements Listener
{

	Core main = Core.plugin;
	
	@EventHandler
	public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent e)
	{
		if(Lists.isFlying.contains(e.getPlayer()) && !Lists.Parkour.containsKey(e.getPlayer()) || e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().isFlying())
		{
			return;
		}
		e.setCancelled(true);
		e.getPlayer().setAllowFlight(false);
		e.getPlayer().setFlying(false);
		e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(1).setY(1));
		Functions.removeIgnoredPlayers(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e)
	{
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE && e.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR)
		{
			if(!Lists.Parkour.containsKey(e.getPlayer()) && !e.getPlayer().isFlying() && !Lists.Parkour.containsKey(e.getPlayer()))
			{
				e.getPlayer().setAllowFlight(true);
				Functions.insertIgnoredPlayers(e.getPlayer());
			}
		}
	}

}
