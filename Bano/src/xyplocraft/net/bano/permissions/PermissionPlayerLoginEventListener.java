package xyplocraft.net.bano.permissions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.permissions.PermissionAttachment;

import xyplocraft.net.bano.Bano;

public class PermissionPlayerLoginEventListener implements Listener
{
	
	Bano main = Bano.getPlugin();
	
	@EventHandler
	public void onPlayerPreLoginEventListener(PlayerLoginEvent e)
	{
		PermissionAttachment attachment = e.getPlayer().addAttachment(Bano.getPlugin());
		PermissionUtils.storeNewAttachment(e.getPlayer().getUniqueId(), attachment);
	}
	
}
