package loonyrules.co.uk.bano.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;
import loonyrules.co.uk.bano.utils.Messages;

public class FlyCommand implements CommandExecutor
{

	static Core main = Core.plugin;
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "Woah, you must be a player to do this!");
			return false;
		}
		Player p = (Player) sender;
		if(label.equalsIgnoreCase("fly"))
		{
			if(args.length == 0)
			{
				if (p.hasPermission("bano.fly.self"))
			    {
					if (Functions.checkFly(p))
			        {
						p.sendMessage(ChatColor.YELLOW + "Flying is now " + ChatColor.RED + "disabled.");
						Functions.flyOff(p);
			        } else {
			            p.sendMessage(ChatColor.YELLOW + "Flying is now " + ChatColor.GREEN + "enabled.");
			            Functions.flyOn(p);
			        }
			    } else Messages.INSUFFICIENT_PERMISSIONS(p);
			} 
			
			if(args.length == 1)
			{
				if (p.hasPermission("bano.fly.others"))
			    {
					if(args[0] != null)
					{
						Player target = Bukkit.getPlayer(args[0]);
						if(target != null)
						{
							if (Functions.checkFly(target))
					        {
								p.sendMessage(ChatColor.YELLOW + "Flight for " + ChatColor.GRAY + p.getName() + ChatColor.YELLOW + " is" + ChatColor.RED + " disabled" + ChatColor.YELLOW + ".");
								target.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.RED + " disabled " + ChatColor.YELLOW + "your flight.");
								Functions.flyOff(target);
					        } else {
					        	p.sendMessage(ChatColor.YELLOW + "Flight for " + ChatColor.GRAY + p.getName() + ChatColor.YELLOW + " is" + ChatColor.GREEN + " enabled" + ChatColor.YELLOW + ".");
					            target.sendMessage(ChatColor.GRAY + p.getName() + ChatColor.GREEN + " enabled " + ChatColor.YELLOW + "your flight.");
					            Functions.flyOn(target);
					        }
						} else p.sendMessage(ChatColor.RED + "That player is not online.");
					} else Messages.FLY_ARGS_ERROR(p);
			    } else Messages.INSUFFICIENT_PERMISSIONS(p);
			}
			
			if(args.length >= 3)
			{
				Messages.FLY_ARGS_ERROR(p);
			}
			
			return true;
		}
		
		return false;
	}
	
}
