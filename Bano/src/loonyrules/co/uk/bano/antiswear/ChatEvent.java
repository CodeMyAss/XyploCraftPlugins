package loonyrules.co.uk.bano.antiswear;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Booleans;
import loonyrules.co.uk.bano.utils.Functions;
import loonyrules.co.uk.bano.utils.Lists;
import loonyrules.co.uk.bano.utils.Messages;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener
{

	static Core main = Core.plugin;

	@SuppressWarnings("static-access")
	@EventHandler (priority=EventPriority.HIGH)
	public void onPlayerPreLoginEvent(AsyncPlayerChatEvent e)
	{
		final Player p = e.getPlayer();
		Functions.SETUP_DISPLAY_NAME(p);	
		
		if(e.getMessage().contains("%"))
		{
			e.getMessage().replace("%", " %");
		}
		
		if(!Lists.CAN_SPEAK.contains(p))
		{
			Lists.CAN_SPEAK.add(p);
			main.bs.runTaskLater(main, new Runnable()
			{
				public void run()
				{
					Lists.CAN_SPEAK.remove(p);
				}
			}, 60L);
			if(Booleans.SERVER_MUTED)
			{
				if(!p.hasPermission("bano.mute.bypass"))
				{
					e.setCancelled(true);
					p.sendMessage(ChatColor.RED + "The chat has been muted for a while.");
				}
			} else {
				if(Lists.mutedPlayers.contains(p.getName()))
				{
					e.setCancelled(true);
					Messages.PLAYER_IS_MUTED(p);
				} else {
					e.setFormat(p.getDisplayName() + ChatColor.RESET + " » " + e.getMessage());
				}
			}
		} else {
			e.setCancelled(true);
			Messages.WAIT_TO_CHAT(p);
		}
		
	}	

}
