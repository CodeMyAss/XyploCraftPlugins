package xyplocraft.net.bano.permissions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import xyplocraft.net.bano.Bano;

public class PermissionPlayerQuitEventListener implements Listener
{
	
	Bano main = Bano.getPlugin();
	PermissionUtils pUtils = PermissionUtils.getPermissionUtils();
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerQuitEvent(PlayerQuitEvent e)
	{
		if(pUtils.containsPlayerAttachment(e.getPlayer().getUniqueId()))
		{
			pUtils.removeAttachment(e.getPlayer().getUniqueId());
		}
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerKickEvent(PlayerKickEvent e)
	{
		if(pUtils.containsPlayerAttachment(e.getPlayer().getUniqueId()))
		{
			pUtils.removeAttachment(e.getPlayer().getUniqueId());
		}
	}
	
}
