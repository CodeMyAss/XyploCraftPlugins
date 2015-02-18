package xyplocraft.net.hub.managers;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import xyplocraft.net.hub.utils.Lists;
import xyplocraft.net.hub.utils.Strings;
import loonyrules.co.uk.bano.Core;

public class InventoryClickEventManager implements Listener
{
	
	Core main = Core.plugin;
	
	@SuppressWarnings({ "incomplete-switch", "deprecation" })
	@EventHandler(priority=EventPriority.HIGH)
	public void onInventoryClickEvent(InventoryClickEvent e) throws IOException
	{
		if(!(e.getWhoClicked() instanceof Player))
		{
			return;
		}
		Player p = (Player) e.getWhoClicked();
		if(e.getAction() != null && e.getCurrentItem() != null && e.getCurrentItem().getType() != null && e.getCurrentItem().getType() != Material.AIR && e.getInventory() != null && e.getInventory().getTitle() != null && e.getInventory().getType() != null)
		{
			if(e.getInventory().getTitle().equalsIgnoreCase(Strings.getNavigationTitle()))
			{
				switch(e.getCurrentItem().getType())
				{
					case GOLD_SWORD:
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Strings.getNavigationKitPvP()))
						{
							ByteArrayDataOutput out = ByteStreams.newDataOutput();
							out.writeUTF("Connect");
							out.writeUTF(p.getName());
							out.writeUTF("pvp");
							p.sendPluginMessage(main, "BungeeCord", out.toByteArray());
						}
					break;
					case GRASS:
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Strings.getNavigationSpawn()))
						{
							p.performCommand("spawn");
						}
					break;
				}
			}
			
			if(e.getInventory().getTitle().equalsIgnoreCase(Strings.getExtrasTitle()))
			{
				switch(e.getCurrentItem().getType())
				{
					case IRON_BOOTS:
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Strings.getHighJump()))
						{
							ItemStack dye = e.getInventory().getItem(10);
							if(dye.getType() == Material.INK_SACK)
							{
								if(dye.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Enabled"))
								{
									// Turn to disabled
									ItemStack toggleplayersDye = new ItemStack(Material.INK_SACK, 1, (byte) 8);
									ItemMeta toggleplayersDyeMeta = toggleplayersDye.getItemMeta();
									toggleplayersDyeMeta.setDisplayName(ChatColor.GRAY + "Disabled");
									toggleplayersDye.setItemMeta(toggleplayersDyeMeta);
									p.getOpenInventory().setItem(10, toggleplayersDye);
									e.getWhoClicked().removePotionEffect(PotionEffectType.JUMP);
								} else {
									// Turn to enabled
									ItemStack toggleplayersDye = new ItemStack(Material.INK_SACK, 1, (byte) 10);
									ItemMeta toggleplayersDyeMeta = toggleplayersDye.getItemMeta();
									toggleplayersDyeMeta.setDisplayName(ChatColor.GREEN + "Enabled");
									toggleplayersDye.setItemMeta(toggleplayersDyeMeta);
									p.getOpenInventory().setItem(10, toggleplayersDye);
									p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
									p.updateInventory();
								}
							}
						}
					break;
					case EYE_OF_ENDER:
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Strings.getTogglePlayers()))
						{
							ItemStack dye = e.getInventory().getItem(12);
							if(dye.getType() == Material.INK_SACK)
							{
								if(dye.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Enabled"))
								{
									// Turn to disabled
									ItemStack toggleplayersDye = new ItemStack(Material.INK_SACK, 1, (byte) 8);
									ItemMeta toggleplayersDyeMeta = toggleplayersDye.getItemMeta();
									toggleplayersDyeMeta.setDisplayName(ChatColor.GRAY + "Disabled");
									toggleplayersDye.setItemMeta(toggleplayersDyeMeta);
									p.getOpenInventory().setItem(12, toggleplayersDye);
									for(Player plO : Bukkit.getOnlinePlayers())
									{
										p.showPlayer(plO);
									}
									Lists.invis.remove(p);
								} else {
									// Turn to enabled
									ItemStack toggleplayersDye = new ItemStack(Material.INK_SACK, 1, (byte) 10);
									ItemMeta toggleplayersDyeMeta = toggleplayersDye.getItemMeta();
									toggleplayersDyeMeta.setDisplayName(ChatColor.GREEN + "Enabled");
									toggleplayersDye.setItemMeta(toggleplayersDyeMeta);
									p.getOpenInventory().setItem(12, toggleplayersDye);
									for(Player plO : Bukkit.getOnlinePlayers())
									{
										p.hidePlayer(plO);
									}
									Lists.invis.add(p);
								}
							}
						}
					break;
					case SUGAR:
						if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Strings.getSpeed()))
						{
							ItemStack dye = e.getInventory().getItem(14);
							if(dye.getType() == Material.INK_SACK)
							{
								if(dye.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Enabled"))
								{
									// Turn to disabled
									ItemStack toggleplayersDye = new ItemStack(Material.INK_SACK, 1, (byte) 8);
									ItemMeta toggleplayersDyeMeta = toggleplayersDye.getItemMeta();
									toggleplayersDyeMeta.setDisplayName(ChatColor.GRAY + "Disabled");
									toggleplayersDye.setItemMeta(toggleplayersDyeMeta);
									p.getOpenInventory().setItem(14, toggleplayersDye);
									e.getWhoClicked().removePotionEffect(PotionEffectType.SPEED);
								} else {
									// Turn to enabled
									ItemStack toggleplayersDye = new ItemStack(Material.INK_SACK, 1, (byte) 10);
									ItemMeta toggleplayersDyeMeta = toggleplayersDye.getItemMeta();
									toggleplayersDyeMeta.setDisplayName(ChatColor.GREEN + "Enabled");
									toggleplayersDye.setItemMeta(toggleplayersDyeMeta);
									p.getOpenInventory().setItem(14, toggleplayersDye);
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
									p.updateInventory();
								}
							}
						}
					break;
				}	
			}
		}
	}
	
}
