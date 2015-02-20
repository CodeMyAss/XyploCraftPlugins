package loonyrules.co.uk.bano.cmds;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;
import loonyrules.co.uk.bano.utils.Messages;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetPointsCommand implements CommandExecutor
{
	
	static Core main = Core.plugin;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("setpoints"))
		{
			if(sender.hasPermission("bano.setpoints"))
			{
				if(args.length == 2)
				{
					if(args[0] != null)
					{
						if(args[1] != null)
						{
							int i = 0;
							boolean error = false;
							try {
								i = Integer.parseInt(args[1]);
							} catch(NumberFormatException ex) {
								error = true;
							}
							if(!error)
							{
								Functions.SET_POINTS(args[0], i, sender);
							} else Messages.SETPOINTS_ARGS_ERROR(sender);
						} else Messages.SETPOINTS_ARGS_ERROR(sender);
					} else Messages.SETPOINTS_ARGS_ERROR(sender);
				} else Messages.SETPOINTS_ARGS_ERROR(sender);
			} else Messages.INSUFFICIENT_PERMISSIONS(sender);
			return true;
		}
		
		return false;
	}

}
