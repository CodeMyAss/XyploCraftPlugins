package xyplocraft.net.hub.listeners;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import xyplocraft.net.hub.utils.Strings;

public class PlayerStatsMenuListener
{
	
	public static void onInteract(Player p)
	{
		openPlayerStatsMenu(p);
	}

	protected static void openPlayerStatsMenu(Player p)
	{
		try {
			Inventory inventory = Bukkit.createInventory(null, 9, Strings.getPlayerstatsTitle());
			
			//
			
//			Date date = new Date();  
//			DateFormat formatter = new SimpleDateFormat("HH:mm:ss z");  
//			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));  
			
			//
			
			ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta playerheadMeta = (SkullMeta) playerhead.getItemMeta();
			playerheadMeta.setOwner(p.getName());
			playerheadMeta.setDisplayName("");
			playerhead.setItemMeta(playerheadMeta);
			inventory.setItem(0, playerhead);
			
			//
			
			ItemStack comingSoon = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta comingSoonMeta = (SkullMeta) comingSoon.getItemMeta();
			comingSoonMeta.setOwner("MHF_Question");
			comingSoonMeta.setDisplayName("Coming Soon");
			comingSoon.setItemMeta(comingSoonMeta);
			inventory.setItem(2, comingSoon);
			inventory.setItem(3, comingSoon);
			inventory.setItem(4, comingSoon);
			inventory.setItem(5, comingSoon);
			inventory.setItem(6, comingSoon);
			
			//
			p.openInventory(inventory);
		} catch(Exception ex) {}
	}
	
}
