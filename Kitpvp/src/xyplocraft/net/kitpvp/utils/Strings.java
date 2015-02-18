package xyplocraft.net.kitpvp.utils;

import org.bukkit.ChatColor;

public class Strings
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	/**
	 * Scoreboard : START
	 */
	
	protected static String scoreboardTitle = "" + ChatColor.YELLOW + "▬▬► " + ChatColor.GOLD + ChatColor.BOLD + "XyploCraft" + ChatColor.RESET + ChatColor.YELLOW + " ◄▬▬";
	
	public static String getScoreboardTitle()
	{
		return scoreboardTitle;
	}
	
	public static void setScoreboardTitle(String name)
	{
		scoreboardTitle = name;
	}
	
	/**
	 * Scoreboard : STOP
	 */
	
}
