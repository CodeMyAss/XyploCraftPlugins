package xyplocraft.net.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffectType;

import xyplocraft.net.hub.utils.Lists;
import xyplocraft.net.hub.utils.Strings;

public class ExtrasMenuListener
{
	
	public static void onInteract(Player p)
	{
		onExtrasMenu(p);
	}

	protected static void onExtrasMenu(Player p)
	{
		try {
			Inventory inventory = Bukkit.createInventory(null, 18, Strings.getExtrasTitle());
			
			//
			
			ItemStack highjump = new ItemStack(Material.IRON_BOOTS);
			ItemMeta highjumpMeta = highjump.getItemMeta();
			highjumpMeta.setDisplayName(Strings.getHighJump());
			highjump.setItemMeta(highjumpMeta);
			inventory.setItem(1, highjump);
			
			if(p.hasPotionEffect(PotionEffectType.JUMP))
			{
				ItemStack highjumpDye = new ItemStack(Material.INK_SACK, 1, (byte) 10);
				ItemMeta highjumpDyeMeta = highjumpDye.getItemMeta();
				highjumpDyeMeta.setDisplayName(ChatColor.GREEN + "Enabled");
				highjumpDye.setItemMeta(highjumpDyeMeta);
				inventory.setItem(10, highjumpDye);
			} else {
				ItemStack highjumpDye = new ItemStack(Material.INK_SACK, 1, (byte) 8);
				ItemMeta highjumpDyeMeta = highjumpDye.getItemMeta();
				highjumpDyeMeta.setDisplayName(ChatColor.GRAY + "Disabled");
				highjumpDye.setItemMeta(highjumpDyeMeta);
				inventory.setItem(10, highjumpDye);
			}
			
			//
			
			ItemStack toggleplayers = new ItemStack(Material.EYE_OF_ENDER);
			ItemMeta toggleplayersMeta = toggleplayers.getItemMeta();
			toggleplayersMeta.setDisplayName(Strings.getTogglePlayers());
			toggleplayers.setItemMeta(toggleplayersMeta);
			inventory.setItem(3, toggleplayers);
			
			if(Lists.invis.contains(p))
			{
				ItemStack toggleplayersDye = new ItemStack(Material.INK_SACK, 1, (byte) 10);
				ItemMeta toggleplayersDyeMeta = toggleplayersDye.getItemMeta();
				toggleplayersDyeMeta.setDisplayName(ChatColor.GREEN + "Enabled");
				toggleplayersDye.setItemMeta(toggleplayersDyeMeta);
				inventory.setItem(12, toggleplayersDye);
			} else {
				ItemStack toggleplayersDye = new ItemStack(Material.INK_SACK, 1, (byte) 8);
				ItemMeta toggleplayersDyeMeta = toggleplayersDye.getItemMeta();
				toggleplayersDyeMeta.setDisplayName(ChatColor.GRAY + "Disabled");
				toggleplayersDye.setItemMeta(toggleplayersDyeMeta);
				inventory.setItem(12, toggleplayersDye);
			}
			
			//
			
			ItemStack speed = new ItemStack(Material.SUGAR);
			ItemMeta speedMeta = speed.getItemMeta();
			speedMeta.setDisplayName(Strings.getSpeed());
			speed.setItemMeta(speedMeta);
			inventory.setItem(5, speed);
			
			if(p.hasPotionEffect(PotionEffectType.SPEED))
			{
				ItemStack speedDye = new ItemStack(Material.INK_SACK, 1, (byte) 10);
				ItemMeta speedDyeMeta = speedDye.getItemMeta();
				speedDyeMeta.setDisplayName(ChatColor.GREEN + "Enabled");
				speedDye.setItemMeta(speedDyeMeta);
				inventory.setItem(14, speedDye);
			} else {
				ItemStack speedDye = new ItemStack(Material.INK_SACK, 1, (byte) 8);
				ItemMeta speedDyeMeta = speedDye.getItemMeta();
				speedDyeMeta.setDisplayName(ChatColor.GRAY + "Disabled");
				speedDye.setItemMeta(speedDyeMeta);
				inventory.setItem(14, speedDye);
			}
			
			//
			
			ItemStack comingSoon = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta comingSoonMeta = (SkullMeta) comingSoon.getItemMeta();
			comingSoonMeta.setOwner("MHF_Question");
			comingSoonMeta.setDisplayName("Coming Soon");
			comingSoon.setItemMeta(comingSoonMeta);
			inventory.setItem(7, comingSoon);
			
			ItemStack csDye = new ItemStack(Material.INK_SACK, 1, (byte) 8);
			ItemMeta csDyeMeta = csDye.getItemMeta();
			csDyeMeta.setDisplayName(ChatColor.GRAY + "Disabled");
			csDye.setItemMeta(csDyeMeta);
			inventory.setItem(16, csDye);
			
			//
			
			p.openInventory(inventory);
		} catch(Exception ex) {}
	}

}
