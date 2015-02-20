package loonyrules.co.uk.bano.timer;

import loonyrules.co.uk.bano.utils.Functions;
import loonyrules.co.uk.bano.vc.VersionChecker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DisplayNameRefresher implements Runnable
{
	
	public void run()
	{
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(ChatColor.AQUA + "Database Refreshing >> Forcing Name Updates!");
		Bukkit.broadcastMessage("");
		for(Player p : Bukkit.getOnlinePlayers())
		{
			Functions.MANUAL_RESET_DISPAY_NAMES(p);
		}
		VersionChecker.checkForUpdate();
	}
	
}
