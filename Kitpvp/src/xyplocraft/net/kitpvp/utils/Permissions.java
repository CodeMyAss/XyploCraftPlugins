package xyplocraft.net.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Permissions
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	public static void givePerm(Player p, String perm)
	{
		String s = "pex user " + p.getName() + " add " + perm;
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
	}
	  
	public static void removePerm(Player p, String perm)
	{
		String s = "pex user " + p.getName() + " remove " + perm;
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
	}
	
}
