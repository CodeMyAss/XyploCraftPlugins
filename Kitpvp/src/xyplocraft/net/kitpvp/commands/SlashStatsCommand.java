package xyplocraft.net.kitpvp.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyplocraft.net.kitpvp.Kitpvp;

public class SlashStatsCommand implements CommandExecutor
{
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	  Kitpvp main = Kitpvp.plugin;
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
		  if (!(sender instanceof Player))
		  {
			  sender.sendMessage(ChatColor.RED + "You must be a player to do that!");
			  return true;
		  }
		  Player p = (Player)sender;
		  if (label.equalsIgnoreCase("stats"))
		  {
			  if (args.length == 0)
			  {
				  int points = 0;
				  int kills = 0;
				  int deaths = 0;
				  int assists = 0;
				  try
				  {
					  ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE uuid= '" + p.getUniqueId() + "';");
					  if (res.next())
					  {
						  points = res.getInt("points");
						  kills = res.getInt("kills");
						  deaths = res.getInt("deaths");
						  assists = res.getInt("assists");
					  }
				  } catch (SQLException sql) {
					  sql.printStackTrace();
				  }
				  p.sendMessage(ChatColor.AQUA + "Your players stats are as follows:");
				  p.sendMessage(ChatColor.GRAY + "  - Points: " + ChatColor.YELLOW + points);
				  p.sendMessage(ChatColor.GRAY + "  - Kills: " + ChatColor.YELLOW + kills);
				  p.sendMessage(ChatColor.GRAY + "  - Deaths: " + ChatColor.YELLOW + deaths);
				  p.sendMessage(ChatColor.GRAY + "  - Assists: " + ChatColor.YELLOW + assists);
			  }
			  
			  if (args.length == 1)
			  {
				  Player target = Bukkit.getPlayer(args[0]);
				  if (target != null)
				  {
					  int points = 0;
					  int kills = 0;
					  int deaths = 0;
					  int assists = 0;
					  try
					  {
						  ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE uuid= '" + target.getUniqueId() + "';");
						  if (res.next())
						  {
							  points = res.getInt("points");
							  kills = res.getInt("kills");
							  deaths = res.getInt("deaths");
							  assists = res.getInt("assists");
						  } else {
							  sender.sendMessage(ChatColor.GRAY + args[0] + ChatColor.RED + " has not joined the server!");
						  }
					  } catch (SQLException sql) {
						  sql.printStackTrace();
					  }
					  p.sendMessage(ChatColor.AQUA + "players stats for " + ChatColor.GRAY + args[0] + ChatColor.AQUA + " are as follows:");
					  p.sendMessage(ChatColor.GRAY + "  - Points: " + ChatColor.YELLOW + points);
					  p.sendMessage(ChatColor.GRAY + "  - Kills: " + ChatColor.YELLOW + kills);
					  p.sendMessage(ChatColor.GRAY + "  - Deaths: " + ChatColor.YELLOW + deaths);
					  p.sendMessage(ChatColor.GRAY + "  - Assists: " + ChatColor.YELLOW + assists);
				  }
			  }
			  return true;
		  }
		  return false;
	  }

}
