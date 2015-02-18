package xyplocraft.net.kitpvp.managers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class KitManager
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	public static void giveStarter(Player p)
	{
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
	    ItemMeta helmetMeta = helmet.getItemMeta();
	    helmetMeta.setDisplayName(ChatColor.DARK_GRAY + "Starter Helmet");
	    helmet.setItemMeta(helmetMeta);
	    p.getInventory().setHelmet(helmet);
	    
	    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
	    ItemMeta chestplateMeta = chestplate.getItemMeta();
	    chestplateMeta.setDisplayName(ChatColor.DARK_GRAY + "Starter Chestplate");
	    chestplate.setItemMeta(chestplateMeta);
	    p.getInventory().setChestplate(chestplate);
	    
	    ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
	    ItemMeta leggingsMeta = leggings.getItemMeta();
	    leggingsMeta.setDisplayName(ChatColor.DARK_GRAY + "Starter Leggings");
	    leggings.setItemMeta(leggingsMeta);
	    p.getInventory().setLeggings(leggings);
	    
	    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	    ItemMeta bootsMeta = boots.getItemMeta();
	    bootsMeta.setDisplayName(ChatColor.DARK_GRAY + "Starter Boots");
	    boots.setItemMeta(bootsMeta);
	    p.getInventory().setBoots(boots);
	    
	    ItemStack bow = new ItemStack(Material.BOW);
	    bow.addEnchantment(Enchantment.DURABILITY, 1);
	    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
	    ItemMeta bowMeta = bow.getItemMeta();
	    bowMeta.setDisplayName(ChatColor.DARK_GRAY + "Starter Bow");
	    bow.setItemMeta(bowMeta);
	    p.getInventory().addItem(new ItemStack[] { bow });
	    
	    ItemStack axe = new ItemStack(Material.STONE_AXE);
	    axe.addEnchantment(Enchantment.DURABILITY, 3);
	    axe.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	    ItemMeta axeMeta = axe.getItemMeta();
	    axeMeta.setDisplayName(ChatColor.DARK_GRAY + "Starter Axe");
	    axe.setItemMeta(axeMeta);
	    p.getInventory().addItem(new ItemStack[] { axe });
	    
	    ItemStack arrow = new ItemStack(Material.ARROW);
	    p.getInventory().setItem(9, arrow);
	    
	    p.updateInventory();
	  }
	  
	  public static void giveMercenary(Player p)
	  {
	    ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
	    ItemMeta helmetMeta = helmet.getItemMeta();
	    helmetMeta.setDisplayName(ChatColor.YELLOW + "Mercenary Helmet");
	    helmet.setItemMeta(helmetMeta);
	    p.getInventory().setHelmet(helmet);
	    
	    ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
	    ItemMeta chestplateMeta = chestplate.getItemMeta();
	    chestplateMeta.setDisplayName(ChatColor.YELLOW + "Mercenary Chestplate");
	    chestplate.setItemMeta(chestplateMeta);
	    p.getInventory().setChestplate(chestplate);
	    
	    ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
	    ItemMeta leggingsMeta = leggings.getItemMeta();
	    leggingsMeta.setDisplayName(ChatColor.YELLOW + "Mercenary Leggings");
	    leggings.setItemMeta(leggingsMeta);
	    p.getInventory().setLeggings(leggings);
	    
	    ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
	    ItemMeta bootsMeta = boots.getItemMeta();
	    bootsMeta.setDisplayName(ChatColor.YELLOW + "Mercenary Boots");
	    boots.setItemMeta(bootsMeta);
	    p.getInventory().setBoots(boots);
	    
	    ItemStack sword = new ItemStack(Material.IRON_SWORD);
	    sword.addEnchantment(Enchantment.DURABILITY, 3);
	    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	    sword.addEnchantment(Enchantment.KNOCKBACK, 1);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.YELLOW + "Mercenary Sword");
	    sword.setItemMeta(swordMeta);
	    p.getInventory().addItem(new ItemStack[] { sword });
	    
	    ItemStack speed = new ItemStack(Material.SUGAR);
	    ItemMeta speedMeta = speed.getItemMeta();
	    speedMeta.setDisplayName(ChatColor.YELLOW + "Mercenary Speed");
	    speed.setItemMeta(speedMeta);
	    p.getInventory().addItem(new ItemStack[] { speed });
	    
	    p.setWalkSpeed(0.15F);
	    p.updateInventory();
	  }
	  
	  public static void giveAssassin(Player p)
	  {
	    ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
	    ItemMeta helmetMeta = helmet.getItemMeta();
	    helmetMeta.setDisplayName(ChatColor.GOLD + "Assassin Helmet");
	    helmet.setItemMeta(helmetMeta);
	    p.getInventory().setHelmet(helmet);
	    
	    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
	    ItemMeta chestplateMeta = chestplate.getItemMeta();
	    chestplateMeta.setDisplayName(ChatColor.GOLD + "Assassin Chestplate");
	    chestplate.setItemMeta(chestplateMeta);
	    p.getInventory().setChestplate(chestplate);
	    
	    ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
	    ItemMeta leggingsMeta = leggings.getItemMeta();
	    leggingsMeta.setDisplayName(ChatColor.GOLD + "Assassin Leggings");
	    leggings.setItemMeta(leggingsMeta);
	    p.getInventory().setLeggings(leggings);
	    
	    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	    ItemMeta bootsMeta = boots.getItemMeta();
	    bootsMeta.setDisplayName(ChatColor.GOLD + "Assassin Boots");
	    boots.setItemMeta(bootsMeta);
	    p.getInventory().setBoots(boots);
	    
	    ItemStack sword = new ItemStack(Material.WOOD_SWORD);
	    sword.addUnsafeEnchantment(Enchantment.DURABILITY, 6);
	    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.GOLD + "Assassin Sword");
	    sword.setItemMeta(swordMeta);
	    p.getInventory().addItem(new ItemStack[] { sword });
	    
	    ItemStack invis = new ItemStack(Material.NETHER_STAR);
	    ItemMeta invisMeta = invis.getItemMeta();
	    invisMeta.setDisplayName(ChatColor.GOLD + "Assassin Invisibility");
	    invis.setItemMeta(invisMeta);
	    p.getInventory().addItem(new ItemStack[] { invis });
	    
	    p.updateInventory();
	  }
	  
	  public static void giveHeavy(Player p)
	  {
	    ItemStack helmet = new ItemStack(Material.IRON_HELMET);
	    ItemMeta helmetMeta = helmet.getItemMeta();
	    helmetMeta.setDisplayName(ChatColor.GREEN + "Heavy Helmet");
	    helmet.setItemMeta(helmetMeta);
	    p.getInventory().setHelmet(helmet);
	    
	    ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
	    ItemMeta chestplateMeta = chestplate.getItemMeta();
	    chestplateMeta.setDisplayName(ChatColor.GREEN + "Heavy Chestplate");
	    chestplate.setItemMeta(chestplateMeta);
	    p.getInventory().setChestplate(chestplate);
	    
	    ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
	    ItemMeta leggingsMeta = leggings.getItemMeta();
	    leggingsMeta.setDisplayName(ChatColor.GREEN + "Heavy Leggings");
	    leggings.setItemMeta(leggingsMeta);
	    p.getInventory().setLeggings(leggings);
	    
	    ItemStack boots = new ItemStack(Material.IRON_BOOTS);
	    ItemMeta bootsMeta = boots.getItemMeta();
	    bootsMeta.setDisplayName(ChatColor.GREEN + "Heavy Boots");
	    boots.setItemMeta(bootsMeta);
	    p.getInventory().setBoots(boots);
	    
	    ItemStack sword = new ItemStack(Material.IRON_SWORD);
	    sword.addEnchantment(Enchantment.DURABILITY, 3);
	    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
	    sword.addEnchantment(Enchantment.KNOCKBACK, 1);
	    ItemMeta swordMeta = sword.getItemMeta();
	    swordMeta.setDisplayName(ChatColor.GREEN + "Heavy Sword");
	    sword.setItemMeta(swordMeta);
	    p.getInventory().addItem(new ItemStack[] { sword });
	    
	    ItemStack heavy = new ItemStack(Material.ANVIL);
	    ItemMeta heavyMeta = heavy.getItemMeta();
	    heavyMeta.setDisplayName(ChatColor.GREEN + "Heavy Repair");
	    heavy.setItemMeta(heavyMeta);
	    p.getInventory().addItem(new ItemStack[] { heavy });
	    
	    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2147483647, 1));
	    
	    p.updateInventory();
	  }
	  
}
