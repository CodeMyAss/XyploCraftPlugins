package loonyrules.co.uk.bano.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;
import loonyrules.co.uk.bano.utils.Messages;

public class MuteCommand implements CommandExecutor
{

	static Core main = Core.plugin;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		if(label.equalsIgnoreCase("mute"))
		{
			if(sender.hasPermission("bano.mute"))
			{
				if(args.length == 1)
				{
					if(args[0] != null)
					{
						if(!main.getConfig().getStringList("general.mute-whitelist").contains(args[0]))
						{
							if(Functions.isMuted(args[0]))
							{
								// Unmute
								Functions.UNMUTE_PLAYER(args[0], sender);
							} else {
								// Mute
								Functions.MUTE_PLAYER(args[0], sender);
							}
						} else Messages.CANNOT_DO_THAT_TO_THAT_PLAYER(sender);
					} else Messages.MUTE_ARGS_ERROR(sender);
				} else Messages.MUTE_ARGS_ERROR(sender);
			} else Messages.INSUFFICIENT_PERMISSIONS(sender);
			return true;
		}
		
		return false;
	}

}
