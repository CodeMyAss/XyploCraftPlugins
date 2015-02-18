package xyplocraft.net.hub.listeners;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import xyplocraft.net.hub.utils.Sockets;
import xyplocraft.net.hub.utils.Strings;

public class NavigationMenuListener
{
	
	public static void onInteract(Player p)
	{
		openNavigationMenu(p);
	}
	
	protected static void openNavigationMenu(Player p)
	{
		try {
			Inventory inventory = Bukkit.createInventory(null, 45, Strings.getNavigationTitle());
			//
			
			Date date = new Date();  
			DateFormat formatter = new SimpleDateFormat("HH:mm:ss z");  
			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));  
            
			String[] hubSocket = null;
			String hubStatus = ChatColor.GREEN + "Online";
			try {
				hubSocket = Sockets.getBungeeSocket(28455);
			} catch (Exception e) {
				hubStatus = ChatColor.RED + "Offline";
			}
			
			//
			
			String[] kitpvpSocket = null;
			String kitpvpStatus = ChatColor.GREEN + "Online";
			int kitpvpOnline = 0;
			int kitpvpMax = 0;
			try {
				kitpvpSocket = Sockets.getBungeeSocket(28456);
				kitpvpOnline = Integer.parseInt(kitpvpSocket[1]);
				kitpvpMax = Integer.parseInt(kitpvpSocket[2]);
			} catch (Exception e) {
				kitpvpStatus = ChatColor.RED + "Offline";
			}
			
			//
			
			String[] prisonSocket = null;
			String prisonStatus = ChatColor.GREEN + "Online";
			int prisonOnline = 0;
			int prisonMax = 0;
			try {
				prisonSocket = Sockets.getBungeeSocket(28458);
				prisonOnline = Integer.parseInt(prisonSocket[1]);
				prisonMax = Integer.parseInt(prisonSocket[2]);
			} catch (Exception e) {
				prisonStatus = ChatColor.RED + "Offline";
			}
			
			//
			
			String[] creativeSocket = null;
			String creativeStatus = ChatColor.GREEN + "Online";
			int creativeOnline = 0;
			int creativeMax = 0;
			try {
				creativeSocket = Sockets.getBungeeSocket(28459);
				creativeOnline = Integer.parseInt(creativeSocket[1]);
				creativeMax = Integer.parseInt(creativeSocket[2]);
			} catch (Exception e) {
				creativeStatus = ChatColor.RED + "Offline";
			}
			
			//
			
			String[] sgSocket = null;
			String sgStatus = ChatColor.GREEN + "Online";
			int sgOnline = 0;
			int sgMax = 0;
			try {
				sgSocket = Sockets.getBungeeSocket(28460);
				sgOnline = Integer.parseInt(sgSocket[1]);
				sgMax = Integer.parseInt(sgSocket[2]);
			} catch (Exception e) {
				sgStatus = ChatColor.RED + "Offline";
			}
			
			//
			
			String[] skywarsSocket = null;
			String skywarsStatus = ChatColor.GREEN + "Online";
			int skywarsOnline = 0;
			int skywarsMax = 0;
			try {
				skywarsSocket = Sockets.getBungeeSocket(28461);
				skywarsOnline = Integer.parseInt(skywarsSocket[1]);
				skywarsMax = Integer.parseInt(skywarsSocket[2]);
			} catch (Exception e) {
				skywarsStatus = ChatColor.RED + "Offline";
			}
			
			//
			
			String[] skyblockSocket = null;
			String skyblockStatus = ChatColor.GREEN + "Online";
			int skyblockOnline = 0;
			int skyblockMax = 0;
			try {
				skyblockSocket = Sockets.getBungeeSocket(28461);
				skyblockOnline = Integer.parseInt(skyblockSocket[1]);
				skyblockMax = Integer.parseInt(skyblockSocket[2]);
			} catch (Exception e) {
				skyblockStatus = ChatColor.RED + "Offline";
			}
			
			//
			
			ItemStack spawn = new ItemStack(Material.GRASS);
			ItemMeta spawnMeta = spawn.getItemMeta();
			spawnMeta.setDisplayName(Strings.getNavigationSpawn());
			
			List<String> hubLore = new ArrayList<String>();
			hubLore.add(ChatColor.YELLOW + "Status: " + hubStatus);
			hubLore.add(ChatColor.YELLOW + "Players: " + ChatColor.GRAY + hubSocket[1] + "/" + hubSocket[2]);
			hubLore.add("");
			hubLore.add(ChatColor.YELLOW + "Last Update:");
			hubLore.add("" + ChatColor.GRAY + formatter.format(date) );
			spawnMeta.setLore(hubLore);
			
			spawn.setItemMeta(spawnMeta);
			inventory.setItem(22, spawn);
			
			//
			
			ItemStack kitpvp = new ItemStack(Material.GOLD_SWORD);
			ItemMeta kitpvpMeta = kitpvp.getItemMeta();
			kitpvpMeta.setDisplayName(Strings.getNavigationKitPvP());
			
			List<String> kitpvpLore = new ArrayList<String>();
			kitpvpLore.add(ChatColor.YELLOW + "Status: " + kitpvpStatus);
			kitpvpLore.add(ChatColor.YELLOW + "Players: " + ChatColor.GRAY + kitpvpOnline + "/" + kitpvpMax);
			kitpvpLore.add("");
			kitpvpLore.add(ChatColor.YELLOW + "Last Update:");
			kitpvpLore.add("" + ChatColor.GRAY + formatter.format(date) );
			kitpvpMeta.setLore(kitpvpLore);
			
			kitpvp.setItemMeta(kitpvpMeta);
			inventory.setItem(4, kitpvp);
			
			//
			
			ItemStack prison = new ItemStack(Material.IRON_FENCE);
			ItemMeta prisonMeta = prison.getItemMeta();
			prisonMeta.setDisplayName(Strings.getNavigationPrison());
			
			List<String> prisonLores = new ArrayList<String>();
			prisonLores.add(ChatColor.YELLOW + "Status: " + prisonStatus);
			prisonLores.add(ChatColor.YELLOW + "Players: " + ChatColor.GRAY + prisonOnline + "/" + prisonMax);
			prisonLores.add("");
			prisonLores.add(ChatColor.YELLOW + "Last Update:");
			prisonLores.add("" + ChatColor.GRAY + formatter.format(date) );
			prisonMeta.setLore(prisonLores);
			
			prison.setItemMeta(prisonMeta);
			inventory.setItem(12, prison);
			
			//
			
			ItemStack creative = new ItemStack(Material.WOOL);
			ItemMeta creativeMeta = creative.getItemMeta();
			creativeMeta.setDisplayName(Strings.getNavigationCreative());
			
			List<String> creativeLores = new ArrayList<String>();
			creativeLores.add(ChatColor.YELLOW + "Status: " + creativeStatus);
			creativeLores.add(ChatColor.YELLOW + "Players: " + ChatColor.GRAY + creativeOnline + "/" + creativeMax);
			creativeLores.add("");
			creativeLores.add(ChatColor.YELLOW + "Last Update:");
			creativeLores.add("" + ChatColor.GRAY + formatter.format(date) );
			creativeMeta.setLore(creativeLores);
			
			creative.setItemMeta(creativeMeta);
			inventory.setItem(14, creative);
			
			//
			
			ItemStack sg = new ItemStack(Material.BOW);
			ItemMeta sgMeta = sg.getItemMeta();
			sgMeta.setDisplayName(Strings.getNavigationSurvivalGames());
			
			List<String> sgLores = new ArrayList<String>();
			sgLores.add(ChatColor.YELLOW + "Status: " + sgStatus);
			sgLores.add(ChatColor.YELLOW + "Players: " + ChatColor.GRAY + sgOnline + "/" + sgMax);
			sgLores.add("");
			sgLores.add(ChatColor.YELLOW + "Last Update:");
			sgLores.add("" + ChatColor.GRAY + formatter.format(date) );
			sgMeta.setLore(sgLores);
			
			sg.setItemMeta(sgMeta);
			inventory.setItem(20, sg);
			
			//
			
			ItemStack skywars = new ItemStack(Material.DIAMOND_SWORD);
			ItemMeta skywarsMete = skywars.getItemMeta();
			skywarsMete.setDisplayName(Strings.getNavigationSkyWars());
			
			List<String> skywarsLores = new ArrayList<String>();
			skywarsLores.add(ChatColor.YELLOW + "Status: " + skywarsStatus);
			skywarsLores.add(ChatColor.YELLOW + "Players: " + ChatColor.GRAY + skywarsOnline + "/" + skywarsMax);
			skywarsLores.add("");
			skywarsLores.add(ChatColor.YELLOW + "Last Update:");
			skywarsLores.add("" + ChatColor.GRAY + formatter.format(date) );
			skywarsMete.setLore(skywarsLores);
			
			skywars.setItemMeta(skywarsMete);
			inventory.setItem(24, skywars);
			
			//
			
			ItemStack skyblock = new ItemStack(Material.LAVA_BUCKET);
			ItemMeta skyblockMeta = skywars.getItemMeta();
			skyblockMeta.setDisplayName(Strings.getNavigationSkyBlock());
			
			List<String> skyblockLores = new ArrayList<String>();
			skyblockLores.add(ChatColor.YELLOW + "Status: " + skyblockStatus);
			skyblockLores.add(ChatColor.YELLOW + "Players: " + ChatColor.GRAY + skyblockOnline + "/" + skyblockMax);
			skyblockLores.add("");
			skyblockLores.add(ChatColor.YELLOW + "Last Update:");
			skyblockLores.add("" + ChatColor.GRAY + formatter.format(date) );
			skyblockMeta.setLore(skywarsLores);
			
			skyblock.setItemMeta(skyblockMeta);
			inventory.setItem(30, skyblock);
			
			//
			
			ItemStack comingSoon = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta comingSoonMeta = (SkullMeta) comingSoon.getItemMeta();
			comingSoonMeta.setOwner("MHF_Question");
			comingSoonMeta.setDisplayName("Coming Soon");
			comingSoon.setItemMeta(comingSoonMeta);
			inventory.setItem(32, comingSoon);
			inventory.setItem(40, comingSoon);
			
			//
			
			p.openInventory(inventory);
		} catch(Exception ex) {}
	}
	
}
