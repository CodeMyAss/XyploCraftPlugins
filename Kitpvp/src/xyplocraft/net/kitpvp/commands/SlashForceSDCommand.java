package xyplocraft.net.kitpvp.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.managers.SupplyDropManager;
import xyplocraft.net.kitpvp.timers.SupplyDropTimer;
import xyplocraft.net.kitpvp.utils.Ints;

public class SlashForceSDCommand implements CommandExecutor
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
		if (label.equalsIgnoreCase("forcesd"))
	    {
			if (sender.hasPermission("kitpvp.commands.forcesd"))
			{
				Ints.supplyDropTimer = -1;
				Location supplyDropLocation = SupplyDropTimer.generateLocation();
				SupplyDropManager.broadcastMessage(ChatColor.GOLD + "A new Supply Drop has spawned at the following location!");
				SupplyDropManager.broadcastMessage(ChatColor.YELLOW + " x: " + supplyDropLocation.getX());
				SupplyDropManager.broadcastMessage(ChatColor.YELLOW + " y: " + supplyDropLocation.getY());
				SupplyDropManager.broadcastMessage(ChatColor.YELLOW + " z: " + supplyDropLocation.getZ());
				SupplyDropManager.spawnSupplyDrop(supplyDropLocation);
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
			}
			return true;
	    }
	    return false;
	}

}
