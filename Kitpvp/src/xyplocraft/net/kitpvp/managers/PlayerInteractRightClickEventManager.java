package xyplocraft.net.kitpvp.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.listeners.kits.AssassinInvisibility;
import xyplocraft.net.kitpvp.listeners.kits.HeavyRepair;
import xyplocraft.net.kitpvp.listeners.kits.MercenarySugar;
import xyplocraft.net.kitpvp.utils.Lists;

public class PlayerInteractRightClickEventManager implements Listener
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	  Kitpvp main = Kitpvp.plugin;
	  
	  @EventHandler(priority=EventPriority.HIGHEST)
	  public void onRightClickInteract(final PlayerInteractEvent e)
	  {
		  if (((e.getAction() != null) && (e.getAction() == Action.RIGHT_CLICK_AIR)) || ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getPlayer().getItemInHand() != null) && (e.getPlayer().getItemInHand().getType() != null)))
		  {
			  if ((e.getPlayer().getItemInHand() != null) && (e.getPlayer().getItemInHand().getType() == Material.SUGAR) && (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Mercenary Speed")) && (Lists.mercenaryKit.contains(e.getPlayer().getName()))) {
				  if (!Lists.MercenarySugar.containsKey(e.getPlayer().getName()))
				  {
					  e.getPlayer().sendMessage(ChatColor.YELLOW + "Mercenary Speed" + ChatColor.GREEN + " activated.");
					  Lists.MercenarySugar.put(e.getPlayer().getName(), Integer.valueOf(15));
					  e.getPlayer().setWalkSpeed(0.3F);
					  Bukkit.getScheduler().runTaskLater(main, new Runnable()
					  {
						  public void run()
						  {
							  if ((e.getPlayer() != null) && (Lists.mercenaryKit.contains(e.getPlayer().getName())))
							  {
								  e.getPlayer().setWalkSpeed(0.15F);
								  e.getPlayer().sendMessage(ChatColor.YELLOW + "Mercenary Speed" + ChatColor.RED + " deactivated.");
							  }
						  }
					  }, 60L);
				  } else {
					  MercenarySugar.getPlayer(e.getPlayer());
				  }
			  }
			  
			  if ((e.getPlayer().getItemInHand() != null) && (e.getPlayer().getItemInHand().getType() == Material.NETHER_STAR) && (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Assassin Invisibility")) && (Lists.assassinKit.contains(e.getPlayer().getName()))) {
				  if (!Lists.AssassinInvisibility.containsKey(e.getPlayer().getName()))
				  {
					  e.getPlayer().sendMessage(ChatColor.GOLD + "Assassin Invisibility" + ChatColor.GREEN + " activated.");
					  Lists.AssassinInvisibility.put(e.getPlayer().getName(), Integer.valueOf(15));
					  e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1));
					  final ItemStack[] armour = e.getPlayer().getInventory().getArmorContents();
					  e.getPlayer().getInventory().setArmorContents(null);
					  e.getPlayer().updateInventory();
					  Bukkit.getScheduler().runTaskLater(main, new Runnable()
					  {
						  public void run()
						  {
							  Player pl = e.getPlayer();
							  if ((pl != null) && (Lists.assassinKit.contains(e.getPlayer().getName())))
							  {
								  pl.getInventory().setArmorContents(armour);
								  pl.updateInventory();
								  pl.sendMessage(ChatColor.GOLD + "Assassin Invisibility" + ChatColor.RED + " deactivated.");
							  }
						  }
					  }, 100L);
				  } else { 
					  AssassinInvisibility.getPlayer(e.getPlayer());
				  }
			  }
			  
			  if ((e.getPlayer().getItemInHand() != null) && (e.getPlayer().getItemInHand().getType() == Material.ANVIL) && (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Heavy Repair")) && (Lists.heavyKit.contains(e.getPlayer().getName()))) {
				  if (!Lists.HeavyRepair.containsKey(e.getPlayer().getName()))
				  {
					  Lists.HeavyRepair.put(e.getPlayer().getName(), Integer.valueOf(15));
					  e.getPlayer().sendMessage(ChatColor.GREEN + "Heavy Repair" + ChatColor.GREEN + " activated.");
					  ItemStack anvil = e.getPlayer().getItemInHand();
					  ItemMeta meta = anvil.getItemMeta();
					  meta.setDisplayName(ChatColor.GREEN + "Heavy Repair " + ChatColor.RED + "COOLING DOWN");
	          
					  e.getPlayer().getItemInHand().setItemMeta(meta);
					  e.getPlayer().setItemInHand(anvil);
	          
					  final int tid = main.bs.scheduleSyncRepeatingTask(main, new Runnable()
					  {
						  public void run()
						  {
							  int a = e.getPlayer().getInventory().first(Material.IRON_SWORD);
							  ItemStack sword = e.getPlayer().getInventory().getItem(a);
							  if (sword != null)
							  {
								  if (Lists.heavyKit.contains(e.getPlayer().getName()))
								  {
									  sword.setDurability((short)(sword.getDurability() - 10));
								  }
							  }
							  ItemStack[] armour = e.getPlayer().getInventory().getArmorContents();
							  if (armour != null)
							  {
								  for (ItemStack is : armour)
								  {
									  if (is != null)
									  {
										  if (Lists.heavyKit.contains(e.getPlayer().getName()))
										  {
											  is.setDurability((short)(is.getDurability() - 10));
										  }
									  }
								  }
							  }
						  }
					  }, 0L, 10L);
					  main.bs.runTaskLater(main, new Runnable()
					  {
						  public void run()
						  {
							  main.bs.cancelTask(tid);
							  if (Lists.heavyKit.contains(e.getPlayer().getName()))
							  {
								  int i = e.getPlayer().getInventory().first(Material.ANVIL);
								  ItemStack anvil = e.getPlayer().getInventory().getItem(i);
								  ItemMeta meta = anvil.getItemMeta();
								  meta.setDisplayName(ChatColor.GREEN + "Heavy Repair");
								  anvil.setItemMeta(meta);
								  e.getPlayer().getInventory().setItem(i, anvil);
								  e.getPlayer().updateInventory();
								  e.getPlayer().sendMessage(ChatColor.GREEN + "Heavy Repair" + ChatColor.RED + " deactivated.");
							  }
						  }
					  }, 100L);
				  } else {
					  HeavyRepair.getPlayer(e.getPlayer());
				  }
			  }
		  }
	  }

}
