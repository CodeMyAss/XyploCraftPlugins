package xyplocraft.net.kitpvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.utils.Locations;

public class SlashSpawnCommand implements CommandExecutor
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
			sender.sendMessage(ChatColor.RED + "Woah, you're not a player...");
			return true;
	    }
	    final Player p = (Player)sender;
	    if (label.equalsIgnoreCase("spawn"))
	    {
	    	if (args.length == 0)
	    	{
	    		p.sendMessage(ChatColor.YELLOW + "Please wait " + ChatColor.GRAY + "5" + ChatColor.YELLOW + " seconds, do not move!");
	    		final Location before = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
	        
	    		Bukkit.getScheduler().runTaskLater(main, new Runnable()
	    		{
	    			public void run()
	    			{
	    				if (before.getX() == p.getLocation().getX()) {
	    					Locations.teleportToSpawn(p);
	    				} else {
	    					p.sendMessage(ChatColor.RED + "Teleportation cancelled, you moved!");
	    				}
	    			}
	    		}, 100L);
	    	}
	    	
	    	if (args.length == 1)
	    	{
	    		if (p.hasPermission("spawn.others"))
	    		{
	    			if (args[0] != null)
	    			{
	    				Player target = Bukkit.getPlayerExact(args[0]);
	    				if (target != null)
	    				{
	    					target.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.YELLOW + " teleported you to spawn.");
	    					Locations.teleportToSpawn(target);
	    					p.sendMessage(ChatColor.YELLOW + "Teleported " + ChatColor.GRAY + target.getName() + ChatColor.YELLOW + " to spawn.");
	    				} else {
	    					p.sendMessage(ChatColor.GRAY + args[0] + ChatColor.RED + " is not an online player.");
	    				}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.ITALIC + "/spawn <player>");
	    			}
	    		} else {
	    			p.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	    		}
	    	}
	    	
	    	if (args.length >= 2)
	    	{
	    		if (p.hasPermission("spawn.others"))
	    		{
	    			p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.ITALIC + "/spawn <player>");
	    		} else {
	    			p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.ITALIC + "/spawn");
	    		}
	    	}
	    	
	    	return true;
	    }
	    return false;
	}

}
