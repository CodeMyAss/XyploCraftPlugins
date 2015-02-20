package loonyrules.co.uk.bano.listeners;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class KnockBackListener implements Listener
{
	
	Core main = Core.plugin;
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerAssistTag(EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player)
		{
			final Player p = (Player) e.getEntity();
			final Player a = (Player) e.getDamager();
			if(a.getItemInHand().containsEnchantment(Enchantment.KNOCKBACK))
			{
				if(!Functions.getIgnoredPlayers().contains(p.getName()))
				{
					Functions.insertIgnoredPlayers(p);
					main.bs.runTaskLater(main, new Runnable()
					{
						public void run()
						{
							Functions.removeIgnoredPlayers(p);
						}
					}, 10L);
				}
			}
		}
	}

}
