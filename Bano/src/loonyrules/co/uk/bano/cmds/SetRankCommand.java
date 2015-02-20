package loonyrules.co.uk.bano.cmds;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;
import loonyrules.co.uk.bano.utils.Messages;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetRankCommand implements CommandExecutor
{
	
	static Core main = Core.plugin;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("setrank"))
		{
			if(sender.hasPermission("bano.setrank"))
			{
				if(args.length == 2)
				{
					if(args[0] != null)
					{
						if(args[1] != null)
						{
							Functions.SET_RANK(args[0], args[1], sender);
						} else Messages.SETRANK_ARGS_ERROR(sender);
					} else Messages.SETRANK_ARGS_ERROR(sender);
				} else Messages.SETRANK_ARGS_ERROR(sender);
			} else Messages.INSUFFICIENT_PERMISSIONS(sender);
			return true;
		}
		
		return false;
	}
	
}
