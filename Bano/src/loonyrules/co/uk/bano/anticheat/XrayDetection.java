package loonyrules.co.uk.bano.anticheat;

import java.util.HashMap;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Lists;
import loonyrules.co.uk.bano.yml.XrayYML;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class XrayDetection implements Listener, CommandExecutor
{

	static Core main;
	public XrayDetection(Core instance)
	{
		main = instance;
	}
	
	public static HashMap<String, Integer> emeralds = new HashMap<String, Integer>();
	public static HashMap<String, Integer> diamonds = new HashMap<String, Integer>();
	public static HashMap<String, Integer> gold = new HashMap<String, Integer>();
	public static HashMap<String, Integer> broke = new HashMap<String, Integer>();
	
	@SuppressWarnings("static-access")
	public static void scheduleRemoval()
	{
		int i;
		i = main.plugin.bs.scheduleSyncRepeatingTask(main.plugin, new Runnable()
		{
			public void run()
			{
				for(Player p : Bukkit.getOnlinePlayers())
				{
					if(broke.get(p.getName()) != null && broke.get(p.getName()) >= main.plugin.getConfig().getInt("xray.detectionInt"))
					{
						giveConviction(p.getName(), 1);
						sendAlert(ChatColor.GRAY + "In the past " + main.plugin.getConfig().getInt("xray.removeLists") + " seconds "+ ChatColor.WHITE + p.getName() + ChatColor.GRAY + " has mined:");
						if(emeralds.get(p.getName()) == null)
						{
							sendAlert(ChatColor.WHITE + "0" + ChatColor.GREEN + " emeralds.");
						} else sendAlert("" + ChatColor.WHITE + emeralds.get(p.getName()) + ChatColor.GREEN + " emeralds.");
						if(diamonds.get(p.getName()) == null)
						{
							sendAlert(ChatColor.WHITE + "0" + ChatColor.AQUA + " diamonds.");
						} else sendAlert("" + ChatColor.WHITE + diamonds.get(p.getName()) + ChatColor.AQUA + " diamonds.");
						if(gold.get(p.getName()) == null)
						{
							sendAlert(ChatColor.WHITE + "0" + ChatColor.GOLD + " gold.");
						} else sendAlert("" + ChatColor.WHITE + gold.get(p.getName()) + ChatColor.GOLD + " gold.");
						sendAlert(ChatColor.GRAY + "Convictions: " + ChatColor.YELLOW + getConvictions(p.getName()));
					}
				}
				emeralds.clear();
				diamonds.clear();
				gold.clear();
			}
		}, 0L, 20*main.plugin.getConfig().getInt("xray.removeLists"));
		Lists.timers.add(i);
	}
	
	public static void addEmerald(Player p, int i)
	{
		emeralds.put(p.getName(), Integer.valueOf(emeralds.get(p.getName()) == null ? 1 : ((Integer) emeralds.get(p.getName())).intValue() + 1));
	}
	
	public static void addDiamonds(Player p, int i)
	{
		diamonds.put(p.getName(), Integer.valueOf(diamonds.get(p.getName()) == null ? 1 : ((Integer) diamonds.get(p.getName())).intValue() + 1));
	}
	
	public static void addGold(Player p, int i)
	{
		gold.put(p.getName(), Integer.valueOf(gold.get(p.getName()) == null ? 1 : ((Integer) gold.get(p.getName())).intValue() + 1));
	}
	
	public static void addBroke(Player p, int i)
	{
		broke.put(p.getName(), Integer.valueOf(broke.get(p.getName()) == null ? 1 : ((Integer) broke.get(p.getName())).intValue() + 1));
	}
	
	public static void sendAlert(String message)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(p.hasPermission("xray.detection.notify"))
			{
				p.sendMessage(message);
			}
		}
	}
	
	@EventHandler
	public void onPlayerBreak(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if(b.getType() == Material.EMERALD_ORE)
		{
			addEmerald(p, 1);
			addBroke(p, 1);
		}
		if(b.getType() == Material.DIAMOND_ORE)
		{
			addDiamonds(p, 1);
			addBroke(p, 1);
		}
		if(b.getType() == Material.GOLD_ORE)
		{
			addGold(p, 1);
			addBroke(p, 1);
		}
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			return true;
		}
		Player p = (Player) sender;
		if(label.equalsIgnoreCase("xraycheck"))
		{
			if(p.hasPermission("xray.detection.check"))
			{
				if(main.plugin.getConfig().getBoolean("xray.enabled"))
				{
					if(args.length != 1)
					{
						p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.ITALIC + "/xraycheck <player>");
					} else {
						Player target = Bukkit.getPlayerExact(args[0]);
						if(target != null)
						{
							p.sendMessage(ChatColor.GRAY + "In the past " + main.plugin.getConfig().getInt("xray.removeLists") + " seconds " + ChatColor.WHITE + args[0] + ChatColor.GRAY + " has mined:");
							if(emeralds.get(p.getName()) == null)
							{
								p.sendMessage(ChatColor.WHITE + "0" + ChatColor.GREEN + " emeralds.");
							} else p.sendMessage("" + ChatColor.WHITE + emeralds.get(p.getName()) + ChatColor.GREEN + " emeralds.");
							if(diamonds.get(p.getName()) == null)
							{
								p.sendMessage(ChatColor.WHITE + "0" + ChatColor.AQUA + " diamonds.");
							} else p.sendMessage("" + ChatColor.WHITE + diamonds.get(p.getName()) + ChatColor.AQUA + " diamonds.");
							if(gold.get(p.getName()) == null)
							{
								p.sendMessage(ChatColor.WHITE + "0" + ChatColor.GOLD + " gold.");
							} else p.sendMessage("" + ChatColor.WHITE + gold.get(p.getName()) + ChatColor.GOLD + " gold.");
							p.sendMessage(ChatColor.GRAY + "Convictions: " + ChatColor.YELLOW + getConvictions(args[0]));
						} else p.sendMessage(ChatColor.RED + "Player not found.");
					}
				} else p.sendMessage(ChatColor.RED + "This command is unavailable when x-ray detection is disabled.");
			} else p.sendMessage(ChatColor.RED + "You don't have permission to do that!");
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static void giveConviction(String name, int i)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(XrayYML.getXray().getString("convictions." + p.getUniqueId()) != null)
			{
				int convictions = XrayYML.getXray().getInt("convictions." + p.getUniqueId() + ".convictions");
				XrayYML.getXray().set("convictions." + p.getUniqueId() + ".convictions", convictions+i);
				XrayYML.saveXray();
			} else {
				XrayYML.getXray().set("convictions." + p.getUniqueId() + ".name", name);
				XrayYML.getXray().set("convictions." + p.getUniqueId() + ".convictions", i);
				XrayYML.saveXray();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void removeConviction(String name, int i)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(XrayYML.getXray().getString("convictions." + p.getUniqueId()) != null)
			{
				int convictions = XrayYML.getXray().getInt("convictions." + p.getUniqueId() + ".convictions");
				XrayYML.getXray().set("convictions." + p.getUniqueId() + ".convictions", convictions-i);
				XrayYML.saveXray();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void deleteConvictions(String name)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(XrayYML.getXray().getString("convictions." + p.getUniqueId()) != null)
			{
				XrayYML.getXray().set("convictions." + p.getUniqueId(), null);
				XrayYML.saveXray();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static int getConvictions(String name)
	{
		if(name != null)
		{
			OfflinePlayer p = Bukkit.getOfflinePlayer(name);
			if(XrayYML.getXray().getString("convictions." + p.getUniqueId()) != null)
			{
				return XrayYML.getXray().getInt("convictions." + p.getUniqueId() + ".convictions");
			}
		}
		return 0;
	}
	
}
