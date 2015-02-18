package xyplocraft.net.hub.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import xyplocraft.net.hub.utils.Locations;
import loonyrules.co.uk.bano.Core;

public class SpawnCommand implements CommandExecutor
{

	Core main = Core.plugin;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			return true;
		}
		Player p = (Player) sender;
		if(label.equalsIgnoreCase("spawn"))
		{
			if(args.length >=2)
			{
				p.sendMessage(ChatColor.RED + "/spawn <player>");
			}
			if(args.length == 0)
			{
				Locations.teleportToSpawn(p);
			}
			if(args.length == 1)
			{
				if(p.hasPermission("hub.spawn.others"))
				{
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null)
					{
						Locations.teleportToSpawn(target);
					} else p.sendMessage(ChatColor.YELLOW + args[0] + ChatColor.RED + " is not online.");
				} else p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
			}
			return true;
		}
		return false;
	}

}
