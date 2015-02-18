package xyplocraft.net.hub.utils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Inventorys
{

	/**
	 * @author LoonyRules and CodeBeasty
	 */
	
	public static void setupMain(Player p)
	{
		/**
		 * Miscellaneous : START
		 */
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setHealth(20.0);
		p.setMaxHealth(20.0);
		p.setFoodLevel(20);
		p.setLevel(0);
		p.setGameMode(GameMode.ADVENTURE);
		for(PotionEffect pe : p.getActivePotionEffects())
		{
			p.removePotionEffect(pe.getType());
		}
		
		/**
		 * Miscellaneous : END
		 */
		
		/**
		 * ItemStacks : START
		 */
		
		ItemStack navigation = new ItemStack(Material.NETHER_STAR);
		ItemMeta navigationMeta = navigation.getItemMeta();
		navigationMeta.setDisplayName(Strings.getNavigationDisplayName());
		navigation.setItemMeta(navigationMeta);
		p.getInventory().setItem(0, navigation);
		
		ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta playerheadMeta = (SkullMeta) playerhead.getItemMeta();
		playerheadMeta.setOwner(p.getName());
		playerheadMeta.setDisplayName(Strings.getPlayerStatsDisplayName());
		playerhead.setItemMeta(playerheadMeta);
		p.getInventory().setItem(4, playerhead);
		
		ItemStack extras = new ItemStack(Material.DRAGON_EGG);
		ItemMeta extrasMeta = extras.getItemMeta();
		extrasMeta.setDisplayName(Strings.getExtrasDisplayName());
		extras.setItemMeta(extrasMeta);
		p.getInventory().setItem(8, extras);
		
		/**
		 * ItemStacks : END
		 */
		
		/**
		 *  Potion Effects : START
		 */
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
		
		/**
		 * Potion Effects : END
		 */
		
		p.updateInventory();
	}

	public static void setupParkour(Player p)
	{
		p.setHealth(20.0);
		p.setMaxHealth(20.0);
		p.setFoodLevel(20);
		p.setLevel(0);
		p.setGameMode(GameMode.ADVENTURE);
		for(PotionEffect pe : p.getActivePotionEffects())
		{
			p.removePotionEffect(pe.getType());
		}
		
		ItemStack air = new ItemStack(Material.AIR);
		p.getInventory().setItem(0, air);
		
		ItemStack exit = new ItemStack(Material.GHAST_TEAR);
		ItemMeta exitMeta = exit.getItemMeta();
		exitMeta.setDisplayName(Strings.ParkourExitTear);
		exit.setItemMeta(exitMeta);
		p.getInventory().setItem(8, exit);
		
		p.updateInventory();
	}

}
