package loonyrules.co.uk.bano.cmds;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;
import loonyrules.co.uk.bano.utils.Messages;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanCommand implements CommandExecutor
{

	static Core main = Core.plugin;
	
	@SuppressWarnings({"unused"})
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equalsIgnoreCase("ban"))
		{
			if(sender.hasPermission("bano.ban"))
			{
				if(args.length >= 2)
				{
					if(!main.getConfig().getStringList("general.ban-whitelist").contains(args[0]))
					{
						if(Functions.isBanned(args[0]))
						{
							Messages.PLAYER_IS_AREADY_BANNED(sender);
						} else {
							String reason = "";
							for(String s : args)
							{
								if(!s.equals(args[0]))
								{
									reason = reason + s + " ";
								}
							}
							if(reason != null)
							{
								Functions.BAN_PAYER(args[0], sender, reason);
							} else Messages.BAN_OOPS(sender);
						}
					} else Messages.CANNOT_DO_THAT_TO_THAT_PLAYER(sender);
				} else Messages.BAN_ARGS_ERROR(sender);
			} else Messages.INSUFFICIENT_PERMISSIONS(sender);
			return true;
		}
		
		return false;
	}
	
}
