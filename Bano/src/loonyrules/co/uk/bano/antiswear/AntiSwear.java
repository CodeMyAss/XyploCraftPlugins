package loonyrules.co.uk.bano.antiswear;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.yml.AntiSwearYML;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AntiSwear implements Listener
{

	static Core main = Core.plugin;
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void onAntiSwear(AsyncPlayerChatEvent e)
	{
		if(!e.getPlayer().hasPermission("antiswear.bypass"))
		{
			String a = e.getMessage().replaceAll("[^A-Za-z0-9 ]", "").toLowerCase();
			for(String s : main.getConfig().getStringList("anti-swear.banned"))
			{
				if(s != null && a.contains(s))
				{
					if(main.getConfig().getBoolean("anti-swear.cancel"))
					{
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("anti-swear.cancelMessage")));
						giveConviction(e.getPlayer().getName(), 1);
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
			if(AntiSwearYML.getAS().getString("convictions." + p.getUniqueId()) != null)
			{
				int convictions = AntiSwearYML.getAS().getInt("convictions." + p.getUniqueId() + ".convictions");
				AntiSwearYML.getAS().set("convictions." + p.getUniqueId() + ".convictions", convictions+i);
				AntiSwearYML.saveAS();
			} else {
				AntiSwearYML.getAS().set("convictions." + p.getUniqueId() + ".name", name);
				AntiSwearYML.getAS().set("convictions." + p.getUniqueId() + ".convictions", i);
				AntiSwearYML.saveAS();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void removeConviction(String name, int i)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(AntiSwearYML.getAS().getString("convictions." + p.getUniqueId()) != null)
			{
				int convictions = AntiSwearYML.getAS().getInt("convictions." + p.getUniqueId() + ".convictions");
				AntiSwearYML.getAS().set("convictions." + p.getUniqueId() + ".convictions", convictions-i);
				AntiSwearYML.saveAS();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void deleteConvictions(String name)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(AntiSwearYML.getAS().getString("convictions." + p.getUniqueId()) != null)
			{
				AntiSwearYML.getAS().set("convictions." + p.getUniqueId(), null);
				AntiSwearYML.saveAS();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static int getConvictions(String name)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(AntiSwearYML.getAS().getString("convictions." + p.getUniqueId()) != null)
			{
				return AntiSwearYML.getAS().getInt("convictions." + p.getUniqueId() + ".convictions");
			}
		}
		return 0;
	}
	
}
