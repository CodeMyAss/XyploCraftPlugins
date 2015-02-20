package loonyrules.co.uk.bano.anticheat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Lists;
import loonyrules.co.uk.bano.yml.FlyYML;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FlyDetection implements Listener
{
	
	static Core main;
	public FlyDetection(Core instance)
	{
		main = instance;
	}
	
	public static HashMap<String, Integer> ft =  new HashMap<String, Integer>();
	public static List<String> kicked = new ArrayList<String>();
	
	@SuppressWarnings("static-access")
	public static void scheduleRemoval()
	{
		int i;
		i = main.plugin.bs.scheduleSyncRepeatingTask(main.plugin, new Runnable(){
			public void run()
			{
				ft.clear();
			}
		}, 0, 20*main.plugin.getConfig().getInt("fly.removeLists"));
		Lists.timers.add(i);
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		if(kicked.contains(e.getPlayer().getName()))
		{
			e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("fly.notifyKMessage").replace("{name}", e.getPlayer().getName())));
		}
	}
	@SuppressWarnings("static-access")
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onPlayerMoveEvent(PlayerMoveEvent e)
	{
		if(!e.getPlayer().isFlying() && e.getPlayer().getGameMode() != GameMode.CREATIVE)
		{
			if(e.getTo().getX() - e.getFrom().getX() > 1 
					|| e.getFrom().getX() - e.getTo().getX() > 1
					|| e.getTo().getZ() - e.getFrom().getZ() > 1
					|| e.getFrom().getZ() - e.getTo().getZ() > 1
					&& Lists.ignorePlayers.contains(e.getPlayer().getName()))
			{
				Location loc = e.getPlayer().getLocation();
				if(loc.getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR)
				{
					if(!(e.getPlayer().getVelocity() == e.getPlayer().getLocation().getDirection().multiply(1).setY(1)))
					{
						e.getPlayer().teleport(loc);
						int x = 0;
						int y = 0;
						if(ft.containsKey(e.getPlayer().getName()))
						{
							x = ft.get(e.getPlayer().getName());
							y = x++;
							ft.remove(e.getPlayer().getName());
							ft.put(e.getPlayer().getName(), x++);
						} else ft.put(e.getPlayer().getName(), 0);
						
						giveConviction(e.getPlayer().getName(), 1);
						
						for(Player pO : Bukkit.getOnlinePlayers())
						{
							if(pO.hasPermission("fly.detection.notify"))
							{
								if(y >= main.plugin.getConfig().getInt("fly.notifyInt"))
								{
									pO.sendMessage(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("fly.notifyDetectionMessage").replace("{name}", e.getPlayer().getName())));
								}
								if(y >= main.plugin.getConfig().getInt("fly.kickInt"))
								{
									kicked.add(e.getPlayer().getName());
									e.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', main.plugin.getConfig().getString("fly.kickedMessage").replace("{name}", e.getPlayer().getName())));
									ft.remove(e.getPlayer().getName());
								}
							}
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void giveConviction(String name, int i)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(FlyYML.getFly().getString("convictions." + p.getUniqueId()) != null)
			{
				int convictions = FlyYML.getFly().getInt("convictions." + p.getUniqueId() + ".convictions");
				FlyYML.getFly().set("convictions." + p.getUniqueId() + ".convictions", convictions+i);
				FlyYML.saveFly();
			} else {
				FlyYML.getFly().set("convictions." + p.getUniqueId() + ".name", name);
				FlyYML.getFly().set("convictions." + p.getUniqueId() + ".convictions", i);
				FlyYML.saveFly();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void removeConviction(String name, int i)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(FlyYML.getFly().getString("convictions." + p.getUniqueId()) != null)
			{
				int convictions = FlyYML.getFly().getInt("convictions." + p.getUniqueId() + ".convictions");
				FlyYML.getFly().set("convictions." + p.getUniqueId() + ".convictions", convictions-i);
				FlyYML.saveFly();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void deleteConvictions(String name)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(FlyYML.getFly().getString("convictions." + p.getUniqueId()) != null)
			{
				FlyYML.getFly().set("convictions." + p.getUniqueId(), null);
				FlyYML.saveFly();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static int getConvictions(String name)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(FlyYML.getFly().getString("convictions." + p.getUniqueId()) != null)
			{
				return FlyYML.getFly().getInt("convictions." + p.getUniqueId() + ".convictions");
			}
		}
		return 0;
	}
	
}
