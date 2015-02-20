package loonyrules.co.uk.bano.anticheat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Lists;
import loonyrules.co.uk.bano.yml.ForcefieldYML;

public class ForcefieldDetection implements Listener
{

	static Core main;
	public ForcefieldDetection(Core instance)
	{
		main = instance;
	}
	
	public static List<String> activeWorlds = null;
	public static List<String> l = new ArrayList<String>();
	public static HashMap<String, Integer> ff =  new HashMap<String, Integer>();
	
	@SuppressWarnings("static-access")
	public static void scheduleRemoval()
	{
		int i;
		int ii;
		activeWorlds = main.plugin.getConfig().getStringList("ff.worlds");
		i = main.plugin.bs.scheduleSyncRepeatingTask(main.plugin, new Runnable(){
			public void run()
			{
				check();
			}
		}, 0, 20*main.plugin.getConfig().getInt("checkInt"));
		ii = main.plugin.bs.scheduleSyncRepeatingTask(main.plugin, new Runnable(){
			public void run()
			{
				ff.clear();
				l.clear();
			}
		}, 0, 20*main.plugin.getConfig().getInt("removeLists"));
		Lists.timers.add(i);
		Lists.timers.add(ii);
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onCreatureSpawnEvent(CreatureSpawnEvent e)
	{
		if(e.getEntity() instanceof Silverfish && activeWorlds.contains(e.getLocation().getWorld().getName()))
		{
			e.setCancelled(false);
		}
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onSDamager(EntityDamageEvent e)
	{
		if(!(e.getCause() == DamageCause.ENTITY_ATTACK))
		{
			if(e.getEntity() instanceof Silverfish && activeWorlds.contains(e.getEntity().getLocation().getWorld().getName()))
			{
				e.setCancelled(true);
			}
		}
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onVDamage(EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Silverfish && activeWorlds.contains(e.getEntity().getLocation().getWorld().getName()))
		{
			Silverfish v = (Silverfish) e.getEntity();
			Player p = (Player) e.getDamager();
			if(v.getCustomName() != null && v.getCustomName().equals(p.getName()))
			{
				e.setCancelled(true);
				int x = 0;
				int y = 0;
				if(ff.containsKey(p.getName()))
				{
					x = ff.get(p.getName());
					y = x++;
					ff.remove(p.getName());
					ff.put(p.getName(), x++);
				} else ff.put(p.getName(), 0);
				giveConviction(p.getName(), 1);
				for(Player pO : Bukkit.getOnlinePlayers())
				{
					if(pO.hasPermission("ff.detection.notify"))
					{
						if(y >= main.plugin.getConfig().getInt("ff.notifyInt"))
						{
							pO.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("ff.notifyDetectionMessage")));
						} 
						if(y >= main.plugin.getConfig().getInt("ff.kickInt"))
						{
							pO.kickPlayer(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("ff.kickedMessage")));
						}
					}
				}
			}
		}
		if(e.getDamager() instanceof Silverfish && activeWorlds.contains(e.getDamager().getLocation().getWorld().getName()))
		{
			Silverfish s = (Silverfish) e.getDamager();
			if(s.getCustomName() != null && l.contains(s.getCustomName()))
			{
				e.setCancelled(true);
			}
		}
	}
	
	@SuppressWarnings("static-access")
	private static void check()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(activeWorlds.contains(p.getLocation().getWorld().getName()))
			{
				if(!ff.containsKey(p.getName()))
				{
					ff.put(p.getName(), 0);
				}
				if(!l.contains(p))
				{
					l.add(p.getName());
				}

				final Silverfish v = (Silverfish) p.getLocation().getWorld().spawnEntity(p.getLocation().subtract(3, 0, 3), EntityType.SILVERFISH);
				v.setCustomName(p.getName());
				v.setCustomNameVisible(true);
				v.setTarget(null);
				v.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
			
				Bukkit.getScheduler().runTaskLater(main.plugin, new Runnable()
				{
					public void run()
					{
						v.remove();
					}
				}, 10L);
			}
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public static void giveConviction(String name, int i)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(ForcefieldYML.getForcefield().getString("convictions." + p.getUniqueId()) != null)
			{
				int convictions = ForcefieldYML.getForcefield().getInt("convictions." + p.getUniqueId() + ".convictions");
				ForcefieldYML.getForcefield().set("convictions." + p.getUniqueId() + ".convictions", convictions+i);
				ForcefieldYML.saveForcefield();
			} else {
				ForcefieldYML.getForcefield().set("convictions." + p.getUniqueId() + ".name", name);
				ForcefieldYML.getForcefield().set("convictions." + p.getUniqueId() + ".convictions", i);
				ForcefieldYML.saveForcefield();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void removeConviction(String name, int i)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(ForcefieldYML.getForcefield().getString("convictions." + p.getUniqueId()) != null)
			{
				int convictions = ForcefieldYML.getForcefield().getInt("convictions." + p.getUniqueId() + ".convictions");
				ForcefieldYML.getForcefield().set("convictions." + p.getUniqueId() + ".convictions", convictions-i);
				ForcefieldYML.saveForcefield();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void deleteConvictions(String name)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(ForcefieldYML.getForcefield().getString("convictions." + p.getUniqueId()) != null)
			{
				ForcefieldYML.getForcefield().set("convictions." + p.getUniqueId(), null);
				ForcefieldYML.saveForcefield();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static int getConvictions(String name)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(ForcefieldYML.getForcefield().getString("convictions." + p.getUniqueId()) != null)
			{
				return ForcefieldYML.getForcefield().getInt("convictions." + p.getUniqueId() + ".convictions");
			}
		}
		return 0;
	}
	
}
