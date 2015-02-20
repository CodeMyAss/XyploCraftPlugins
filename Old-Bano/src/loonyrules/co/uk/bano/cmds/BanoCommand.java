package loonyrules.co.uk.bano.cmds;

import loonyrules.co.uk.bano.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanoCommand implements CommandExecutor
{

	Core main = Core.plugin;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(label.equalsIgnoreCase("bano"))
		{
			sender.sendMessage(ChatColor.YELLOW + "Information about Bano");
			sender.sendMessage(ChatColor.DARK_GRAY + " - Current Version: " + ChatColor.DARK_AQUA + main.version);
			return true;
		}
		return false;
	}
	
	
	
}
