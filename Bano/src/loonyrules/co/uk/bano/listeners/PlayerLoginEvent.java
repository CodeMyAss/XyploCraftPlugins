package loonyrules.co.uk.bano.listeners;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerLoginEvent implements Listener
{

	static Core main = Core.plugin;
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerJoinEvent(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		Functions.MANUAL_RESET_DISPAY_NAMES(p);	
	}

}
