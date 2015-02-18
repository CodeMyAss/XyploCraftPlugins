package xyplocraft.net.kitpvp.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyplocraft.net.kitpvp.Kitpvp;

public class SlashTPACommand implements CommandExecutor
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	Kitpvp main = Kitpvp.plugin; 
	  
	HashMap<Player, Player> tpaHashMap = new HashMap<Player, Player>();
	HashMap<Player, Location> requestLocs = new HashMap<Player, Location>();
	  
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!(sender instanceof Player))
	    {
			sender.sendMessage(ChatColor.RED + "Woah, you're not a player...");
			return true;
	    }
	    final Player p = (Player)sender;
	    if (label.equalsIgnoreCase("tpa"))
	    {
	    	if (p.hasPermission("tpa.request"))
	    	{
	    		if ((args.length == 1) && (args[0] != null))
	    		{
	    			Player target = Bukkit.getPlayerExact(args[0]);
	    			if (target != null)
	    			{
	    				tpaHashMap.put(target, p);
	    				p.sendMessage(ChatColor.GRAY + args[0] + ChatColor.YELLOW + " has received your tpa request.");
	    				target.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.YELLOW + " has requested to teleport to you!");
	    				target.sendMessage(ChatColor.GRAY + "(" + ChatColor.DARK_GRAY + "/tpaccept" + ChatColor.YELLOW + " to accept their request, or " + ChatColor.DARK_GRAY + "/tpdeny" + ChatColor.YELLOW + " to deny it." + ChatColor.GRAY + ")");
	    			} else p.sendMessage(ChatColor.GRAY + args[0] + ChatColor.RED + " is not an online player.");
	    		} else p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.ITALIC + "/tpa <player>");
	    	} else  p.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	      return true;
	    }
	    
	    if (label.equalsIgnoreCase("tpaccept"))
	    {
	    	final Player requester = (Player)this.tpaHashMap.get(p);
	    	if (requester != null)
	    	{
	    		requester.sendMessage(ChatColor.YELLOW + "Please wait " + ChatColor.GRAY + "5" + ChatColor.YELLOW + " seconds, do not move!");
	    		requestLocs.put(requester, requester.getLocation());
	        
	    		final Location before = new Location(requester.getWorld(), requester.getLocation().getX(), requester.getLocation().getY(), requester.getLocation().getZ());
	        
	    		Bukkit.getScheduler().runTaskLater(main, new Runnable()
	    		{
	    			public void run()
	    			{
	    				tpaHashMap.remove(p);
	    				if (before.getX() == requester.getLocation().getX())
	    				{
	    					requester.teleport(p.getLocation());
	    					p.sendMessage(ChatColor.GRAY + requester.getName() + ChatColor.YELLOW + " has been teleported to you.");
	    					requester.sendMessage(ChatColor.YELLOW + "Teleporting you to " + ChatColor.GRAY + p.getName() + ChatColor.YELLOW + "!");
	    				} else {
	    					requester.sendMessage(ChatColor.RED + "Teleportation cancelled, you moved!");
	    					p.sendMessage(ChatColor.GRAY + requester.getName() + ChatColor.YELLOW + " moved so they were not teleported.");
	    				}
	    			}
	    		}, 100L);
	    	} else {
	    		p.sendMessage(ChatColor.RED + "You have no tpa requests.");
	    	}
	    	return true;
	    }
	    
	    if (label.equalsIgnoreCase("tpdeny"))
	    {
	    	Player requester = (Player)this.tpaHashMap.get(p);
	    	if ((requester != null) && (this.tpaHashMap.containsKey(p.getName())))
	    	{
	    		tpaHashMap.remove(p);
	    		p.sendMessage(ChatColor.YELLOW + "You rejected the tpa request from " + ChatColor.GRAY + requester.getName() + ChatColor.YELLOW + "!");
	    		if (requester != null)
	    		{
	    			requester.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.YELLOW + " rejected your tpa request!");
	    		}
	    	} else {
	    		p.sendMessage(ChatColor.RED + "You have no tpa requests.");
	    	}
	    	return true;
	    }
	    return false;
	}

}
