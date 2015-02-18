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
import xyplocraft.net.kitpvp.utils.Scoreboards;

@SuppressWarnings("unused")
public class SlashPointsCommand implements CommandExecutor
{

	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	Kitpvp main = Kitpvp.plugin;
	  
	  
	public static boolean isInt(String input)
	{
	    boolean error = false;
	    try
	    {
			int i = Integer.parseInt(input);
	    } catch (Exception ex) {
	    	error = true;
	    }
	    if (error)
	    {
	    	return false;
	    }
	    return true;
	}
	  
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	    if (label.equalsIgnoreCase("points"))
	    {
	    	if (sender.hasPermission("kitpvp.points.change"))
	    	{
	    		if ((args.length == 3) && (args[0] != null) && (args[1] != null) && (isInt(args[2])))
	    		{
	    			if (args[0].equalsIgnoreCase("give"))
	    			{
	    				int points = 0;
	    				try
	    				{
	    					ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE ign= '" + args[1] + "';");
	    					if (res.next())
	    					{
	    						points = res.getInt("points");
	    						points += Integer.parseInt(args[2]);
	    						main.c.createStatement().executeUpdate("UPDATE `kitpvp_data` SET points= '" + points + "' WHERE ign= '" + args[1] + "'");
	    						sender.sendMessage(ChatColor.GRAY + args[1] + ChatColor.YELLOW + "'s points are now " + ChatColor.GRAY + points + ChatColor.YELLOW + "!");
	    						Player target = Bukkit.getPlayer(args[1]);
	    						if (target != null)
	    						{
	    							Scoreboards.setupMain(target);
	    						}
	    					} else {
	    						sender.sendMessage(ChatColor.GRAY + args[1] + ChatColor.RED + " has not joined the server!");
	    					}
	    				} catch (SQLException sql) {
	    					sql.printStackTrace();
	    				}
	    			}
	    			
	    			if (args[0].equalsIgnoreCase("take"))
	    			{
	    				int points = 0;
	    				try
	    				{
	    					ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE ign= '" + args[1] + "';");
	    					if (res.next())
	    					{
	    						points = res.getInt("points");
	    						points -= Integer.parseInt(args[2]);
	    						main.c.createStatement().executeUpdate("UPDATE `kitpvp_data` SET points= '" + points + "' WHERE ign= '" + args[1] + "'");
	    						sender.sendMessage(ChatColor.GRAY + args[1] + ChatColor.YELLOW + "'s points are now " + ChatColor.GRAY + points + ChatColor.YELLOW + "!");
	    						Player target = Bukkit.getPlayer(args[1]);
	    						if (target != null)
	    						{
	    							Scoreboards.setupMain(target);
	    						}
	    					} else {
	    						sender.sendMessage(ChatColor.GRAY + args[1] + ChatColor.RED + " has not joined the server!");
	    					}
	    				} catch (SQLException sql) {
	    					sql.printStackTrace();
	    				}
	    			}
	    			
	    			if (args[0].equalsIgnoreCase("set"))
	    			{
	    				int points = 0;
	    				try
	    				{
	    					ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE ign= '" + args[1] + "';");
	    					if (res.next())
	    					{
	    						points = res.getInt("points");
	    						points = Integer.parseInt(args[2]);
	    						main.c.createStatement().executeUpdate("UPDATE `kitpvp_data` SET points= '" + points + "' WHERE ign= '" + args[1] + "'");
	    						sender.sendMessage(ChatColor.GRAY + args[1] + ChatColor.YELLOW + "'s points are now " + ChatColor.GRAY + points + ChatColor.YELLOW + "!");
	    						Player target = Bukkit.getPlayer(args[1]);
	    						if (target != null)
	    						{
	    							Scoreboards.setupMain(target);
	    						}
	    					} else {
	    						sender.sendMessage(ChatColor.GRAY + args[1] + ChatColor.RED + " has not joined the server!");
	    					}
	    				} catch (SQLException sql) {
	    					sql.printStackTrace();
	    				}
	    			}
	    		} else  {
	    			sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.ITALIC + "/points <give:take:set> <player> <points>");
	    		}
	    	} else {
	    		sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	    	}
	    	return true;
	    }
	    return false;
	}

}
