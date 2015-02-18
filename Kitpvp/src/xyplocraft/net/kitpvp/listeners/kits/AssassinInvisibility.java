package xyplocraft.net.kitpvp.listeners.kits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import xyplocraft.net.kitpvp.utils.Lists;

public class AssassinInvisibility
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	public static void getPlayer(Player p)
	{
		int time = ((Integer)Lists.AssassinInvisibility.get(p.getName())).intValue();
		p.sendMessage(ChatColor.RED + "You can't use this for another " + ChatColor.GRAY + time + ChatColor.YELLOW + " seconds!");
	}
	
}
