package loonyrules.co.uk.bano.cmds;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;
import loonyrules.co.uk.bano.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
public class UnbanCommand implements CommandExecutor
{

	static Core main = Core.plugin;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("unban") || label.equalsIgnoreCase("pardon"))
		{
			if(sender.hasPermission("bano.unban"))
			{
				if(args.length == 1)
				{
					if(!Functions.isBanned(args[0]))
					{
						Messages.PLAYER_IS_NOT_BANNED(sender);
					} else {
						Functions.UNBAN_PAYER(args[0], sender);
					}
				} else Messages.UNBAN_ARGS_ERROR(sender);
			} else Messages.INSUFFICIENT_PERMISSIONS(sender);
			return true;
		}
		
		return false;
	}

}
