package xyplocraft.net.hub.listeners;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import xyplocraft.net.hub.utils.Inventorys;
import xyplocraft.net.hub.utils.Lists;
import xyplocraft.net.hub.utils.Locations;
import xyplocraft.net.hub.utils.Scoreboards;
import xyplocraft.net.hub.utils.Strings;
import loonyrules.co.uk.bano.Core;

public class ParkourSignsListener implements Listener
{

	Core main = Core.plugin;
	
	@SuppressWarnings({ "static-access", "deprecation" })
	@EventHandler
	public void onPlayerInteractEvent(final PlayerInteractEvent e)
	{
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(e.getPlayer().getItemInHand().getType() == Material.GHAST_TEAR && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(Strings.getParkourExitTear()))
			{
				if(Lists.Parkour.containsKey(e.getPlayer()))
				{
					if(Lists.Parkour.get(e.getPlayer()).equalsIgnoreCase("easy"))
					{
						e.getPlayer().sendMessage(ChatColor.YELLOW + "You're no longer a part of the " + ChatColor.GREEN + "easy" + ChatColor.YELLOW + " parkour.");
					}
					if(Lists.Parkour.get(e.getPlayer()).equalsIgnoreCase("medium"))
					{
						e.getPlayer().sendMessage(ChatColor.YELLOW + "You're no longer a part of the " + ChatColor.GOLD + "medium" + ChatColor.YELLOW + " parkour.");
					}
					if(Lists.Parkour.get(e.getPlayer()).equalsIgnoreCase("hard"))
					{
						e.getPlayer().sendMessage(ChatColor.YELLOW + "You're no longer a part of the " + ChatColor.RED + "hard" + ChatColor.YELLOW + " parkour.");
					}
					Lists.Parkour.remove(e.getPlayer());
					Inventorys.setupMain(e.getPlayer());
					Locations.teleportToSpawn(e.getPlayer());
				}
			}
		}
		
		Block pBlock = e.getClickedBlock();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getTypeId() == 68)
		{
			Sign clickedSign = (Sign) pBlock.getState();
			if(ChatColor.stripColor(clickedSign.getLine(1)).equalsIgnoreCase("Parkour"))
			{
				
				// Easy
				if(ChatColor.stripColor(clickedSign.getLine(2)).equalsIgnoreCase("Easy"))
				{
					//Start
					if(ChatColor.stripColor(clickedSign.getLine(3)).equalsIgnoreCase("Start"))
					{
						if(!Lists.Parkour.containsKey(e.getPlayer()))
						{
							Lists.Parkour.put(e.getPlayer(), "easy");
							e.getPlayer().sendMessage(ChatColor.YELLOW + "You're now a part of the " + ChatColor.GREEN + "easy" + ChatColor.YELLOW + " parkour.");
							Inventorys.setupParkour(e.getPlayer());
						} else {
							if(Lists.Parkour.get(e.getPlayer()).equalsIgnoreCase("easy"))
							{
								Lists.Parkour.remove(e.getPlayer());
								e.getPlayer().sendMessage(ChatColor.YELLOW + "You're no longer a part of the " + ChatColor.GREEN + "easy" + ChatColor.YELLOW + " parkour.");
								Inventorys.setupMain(e.getPlayer());
							} else e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "You're already participating in a parkour.");
						}
					}
						
					//Finish
					if(ChatColor.stripColor(clickedSign.getLine(3)).equalsIgnoreCase("Finish"))
					{
						if(Lists.Parkour.containsKey(e.getPlayer()) && Lists.Parkour.get(e.getPlayer()).equalsIgnoreCase("easy"))
						{
							e.getPlayer().sendMessage(ChatColor.YELLOW + "Congratulations! You completed the " + ChatColor.GREEN + "easy" + ChatColor.YELLOW + " parkour!");
							if(!main.plugin.getConfig().getStringList("parkour.easy").contains(e.getPlayer().getName()))
							{
								e.getPlayer().sendMessage(ChatColor.GREEN + "Reward +500 coins!");
								List<String> l = main.plugin.getConfig().getStringList("parkour.easy");
								l.add(e.getPlayer().getName());
								main.plugin.getConfig().set("parkour.easy", l);
								main.plugin.saveConfig();
								try {
									ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `users` WHERE uuid= '" + e.getPlayer().getUniqueId() + "';");
									if(res.next())
									{
										int SQLscore = res.getInt("points");
										int newScore = SQLscore + 500;
										main.c.createStatement().executeUpdate("UPDATE `users` SET points= '" + newScore + "' WHERE uuid= '" + e.getPlayer().getUniqueId() + "'");
									}
								} catch(SQLException sql) {
									sql.printStackTrace();
								}
								main.bs.runTaskLater(main, new Runnable()
								{
									public void run()
									{
										Scoreboards.setupMain(e.getPlayer());
									}
								}, 20L);
							}
							Lists.Parkour.remove(e.getPlayer());
							Inventorys.setupMain(e.getPlayer());
							Locations.teleportToSpawn(e.getPlayer());
						} else return;
					}
				}
				
				// Medium
				if(ChatColor.stripColor(clickedSign.getLine(2)).equalsIgnoreCase("Medium"))
				{
					//Start
					if(ChatColor.stripColor(clickedSign.getLine(3)).equalsIgnoreCase("Start"))
					{
						if(!Lists.Parkour.containsKey(e.getPlayer()))
						{
							Lists.Parkour.put(e.getPlayer(), "medium");
							e.getPlayer().sendMessage(ChatColor.YELLOW + "You're now a part of the " + ChatColor.GOLD + "medium" + ChatColor.YELLOW + " parkour.");
							Inventorys.setupParkour(e.getPlayer());
						} else {
							if(Lists.Parkour.get(e.getPlayer()).equalsIgnoreCase("medium"))
							{
								Lists.Parkour.remove(e.getPlayer());
								e.getPlayer().sendMessage(ChatColor.YELLOW + "You're no longer a part of the " + ChatColor.GOLD + "medium" + ChatColor.YELLOW + " parkour.");
								Inventorys.setupMain(e.getPlayer());
							} else e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "You're already participating in a parkour.");
						}
					}
						
					//Finish
					if(ChatColor.stripColor(clickedSign.getLine(3)).equalsIgnoreCase("Finish"))
					{
						if(Lists.Parkour.containsKey(e.getPlayer()) && Lists.Parkour.get(e.getPlayer()).equalsIgnoreCase("medium"))
						{
							e.getPlayer().sendMessage(ChatColor.YELLOW + "Congratulations! You completed the " + ChatColor.GOLD + "medium" + ChatColor.YELLOW + " parkour!");
							if(!main.plugin.getConfig().getStringList("parkour.medium").contains(e.getPlayer().getName()))
							{
								e.getPlayer().sendMessage(ChatColor.GREEN + "Reward +2500 coins!");
								List<String> l = main.plugin.getConfig().getStringList("parkour.medium");
								l.add(e.getPlayer().getName());
								main.plugin.getConfig().set("parkour.medium", l);
								main.plugin.saveConfig();
								try {
									ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `users` WHERE uuid= '" + e.getPlayer().getUniqueId() + "';");
									if(res.next())
									{
										int SQLscore = res.getInt("points");
										int newScore = SQLscore + 2500;
										main.c.createStatement().executeUpdate("UPDATE `users` SET points= '" + newScore + "' WHERE uuid= '" + e.getPlayer().getUniqueId() + "'");
									}
								} catch(SQLException sql) {
									sql.printStackTrace();
								}
								main.bs.runTaskLater(main, new Runnable()
								{
									public void run()
									{
										Scoreboards.setupMain(e.getPlayer());
									}
								}, 20L);
							}
							Lists.Parkour.remove(e.getPlayer());
							Inventorys.setupMain(e.getPlayer());
							Locations.teleportToSpawn(e.getPlayer());
						} else return;
					}
				}
				
				// Hard
				if(ChatColor.stripColor(clickedSign.getLine(2)).equalsIgnoreCase("hard"))
				{
					//Start
					if(ChatColor.stripColor(clickedSign.getLine(3)).equalsIgnoreCase("Start"))
					{
						if(!Lists.Parkour.containsKey(e.getPlayer()))
						{
							Lists.Parkour.put(e.getPlayer(), "hard");
							e.getPlayer().sendMessage(ChatColor.YELLOW + "You're now a part of the " + ChatColor.RED + "hard" + ChatColor.YELLOW + " parkour.");
							Inventorys.setupParkour(e.getPlayer());
						} else {
							if(Lists.Parkour.get(e.getPlayer()).equalsIgnoreCase("hard"))
							{
								Lists.Parkour.remove(e.getPlayer());
								e.getPlayer().sendMessage(ChatColor.YELLOW + "You're no longer a part of the " + ChatColor.RED + "hard" + ChatColor.YELLOW + " parkour.");
								Inventorys.setupMain(e.getPlayer());
							} else e.getPlayer().sendMessage(ChatColor.DARK_AQUA + "You're already participating in a parkour.");
						}
					}
						
					//Finish
					if(ChatColor.stripColor(clickedSign.getLine(3)).equalsIgnoreCase("Finish"))
					{
						if(Lists.Parkour.containsKey(e.getPlayer()) && Lists.Parkour.get(e.getPlayer()).equalsIgnoreCase("hard"))
						{
							e.getPlayer().sendMessage(ChatColor.YELLOW + "Congratulations! You completed the " + ChatColor.RED + "hard" + ChatColor.YELLOW + " parkour!");
							if(!main.plugin.getConfig().getStringList("parkour.hard").contains(e.getPlayer().getName()))
							{
								e.getPlayer().sendMessage(ChatColor.GREEN + "Reward +5000 coins!");
								List<String> l = main.plugin.getConfig().getStringList("parkour.hard");
								l.add(e.getPlayer().getName());
								main.plugin.getConfig().set("parkour.hard", l);
								main.plugin.saveConfig();
								try {
									ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `users` WHERE uuid= '" + e.getPlayer().getUniqueId() + "';");
									if(res.next())
									{
										int SQLscore = res.getInt("points");
										int newScore = SQLscore + 5000;
										main.c.createStatement().executeUpdate("UPDATE `users` SET points= '" + newScore + "' WHERE uuid= '" + e.getPlayer().getUniqueId() + "'");
									}
								} catch(SQLException sql) {
									sql.printStackTrace();
								}
								main.bs.runTaskLater(main, new Runnable()
								{
									public void run()
									{
										Scoreboards.setupMain(e.getPlayer());
									}
								}, 20L);
							}
							Lists.Parkour.remove(e.getPlayer());
							Inventorys.setupMain(e.getPlayer());
							Locations.teleportToSpawn(e.getPlayer());
						} else return;
					}
				}
				
			}
		}
	}

}
