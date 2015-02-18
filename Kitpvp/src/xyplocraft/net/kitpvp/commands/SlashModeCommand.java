package xyplocraft.net.kitpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.utils.Lists;

public class SlashModeCommand implements CommandExecutor
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
	    Player p = (Player)sender;
	    if (label.equalsIgnoreCase("mode"))
	    {
	    	if (p.hasPermission("kitpvp.editmode"))
	    	{
	    		if (Lists.editMode.contains(p.getName()))
	    		{
	    			p.sendMessage(ChatColor.YELLOW + "Edit Mode" + ChatColor.RED + " deactivated.");
	    			Lists.editMode.remove(p.getName());
	    		} else {
	    			p.sendMessage(ChatColor.YELLOW + "Edit Mode" + ChatColor.GREEN + " activated.");
	    			Lists.editMode.add(p.getName());
	    		}
	    	} else {
	    		p.sendMessage(ChatColor.RED + "You don't have permissino to do that!");
	    	}
	    	return true;
	    }
	    return false;
	}

}
