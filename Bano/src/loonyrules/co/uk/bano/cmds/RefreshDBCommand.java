package loonyrules.co.uk.bano.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;
import loonyrules.co.uk.bano.vc.VersionChecker;

public class RefreshDBCommand implements CommandExecutor
{
	
	Core main = Core.plugin;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(label.equalsIgnoreCase("refreshdb"))
		{
			if(sender.hasPermission("bano.commands.refreshdb"))
			{
				sender.sendMessage("Refresh has been updated");
				VersionChecker.checkForUpdate();
				for(Player p : Bukkit.getOnlinePlayers())
				{
					Functions.MANUAL_RESET_DISPAY_NAMES(p);
				}
			} else sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
			return true;
		}
		return false;
	}
	
}
