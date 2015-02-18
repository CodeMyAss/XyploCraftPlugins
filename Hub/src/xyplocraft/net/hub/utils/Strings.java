package xyplocraft.net.hub.utils;

import org.bukkit.ChatColor;

public class Strings
{
	
	/**
	 * @author LoonyRules and CodeBeasty
	 */
	
	/**
	 * Navigation Menu : START
	 */
	
	protected static String navigationDisplayName = "" + ChatColor.BOLD + ChatColor.YELLOW + "Navigation Menu";
	
	public static String getNavigationDisplayName()
	{
		return navigationDisplayName;
	}
	
	public static void setNavigationDisplayName(String name)
	{
		navigationDisplayName = name;
	}
	
	protected static String navigationTitle = "" + ChatColor.BOLD + ChatColor.YELLOW + "Teleport to a server";
	
	public static String getNavigationTitle()
	{
		return navigationTitle;
	}
	
	public static void setNavigationTitle(String name)
	{
		navigationTitle = name;
	}
	
	public static String getNavigationSpawn()
	{
		return ChatColor.YELLOW + "▬▬▬► " + ChatColor.GREEN + "SPAWN" + ChatColor.YELLOW + " ◄▬▬▬";
	}
	
	public static String getNavigationKitPvP()
	{
		return ChatColor.YELLOW + "▬▬▬► " + ChatColor.GOLD + "KITPVP" + ChatColor.YELLOW + " ◄▬▬▬";
	}
	
	public static String getNavigationPrison()
	{
		return ChatColor.YELLOW + "▬▬▬► " + ChatColor.GRAY + "PRISON" + ChatColor.YELLOW + " ◄▬▬▬";
	}
	
	public static String getNavigationCreative()
	{
		return ChatColor.YELLOW + "▬▬▬► " + ChatColor.WHITE + "CREATIVE" + ChatColor.YELLOW + " ◄▬▬▬";
	}
	
	public static String getNavigationSurvivalGames()
	{
		return ChatColor.YELLOW + "▬▬▬► " + ChatColor.LIGHT_PURPLE + "SURVIVAL GAMES" + ChatColor.YELLOW + " ◄▬▬▬";
	}
	
	public static String getNavigationSkyWars()
	{
		return ChatColor.YELLOW + "▬▬▬► " + ChatColor.DARK_PURPLE + "SKY WARS" + ChatColor.YELLOW + " ◄▬▬▬";
	}
	
	public static String getNavigationSkyBlock()
	{
		return ChatColor.YELLOW + "▬▬▬► " + ChatColor.BLUE + "SKY BLOCK" + ChatColor.YELLOW + " ◄▬▬▬";
	}
	
	/**
	 * Navigation Menu : END
	 */
	
	/**
	 * Player Stats : START
	 */
	
	protected static String playerstatsDisplayName = "" + ChatColor.BOLD + ChatColor.LIGHT_PURPLE + "Account Stats";
	
	public static String getPlayerStatsDisplayName()
	{
		return playerstatsDisplayName;
	}
	
	public static void setPlayerStatsDisplayName(String name)
	{
		playerstatsDisplayName = name;
	}

	protected static String playerstatsTitle = "" + ChatColor.BOLD + ChatColor.LIGHT_PURPLE + "Your account stats";
	
	public static String getPlayerstatsTitle()
	{
		return playerstatsTitle;
	}
	
	public static void setPlayerstatsTitle(String name)
	{
		playerstatsTitle = name;
	}
	
	/**
	 * Player Stats : END
	 */
	
	/**
	 * Extras : START
	 */
	
	protected static String extrasDisplayName = "" + ChatColor.BOLD + ChatColor.GREEN + "Extras";
	
	public static String getExtrasDisplayName()
	{
		return extrasDisplayName;
	}
	
	public static void setExtrasDisplayName(String name)
	{
		extrasDisplayName = name;
	}
	
	
	protected static String extrasTitle = "" + ChatColor.BOLD + ChatColor.GREEN + "Select a feature";;
	
	public static String getExtrasTitle()
	{
		return extrasTitle;
	}
	
	public static void setExtrasTitle(String name)
	{
		extrasTitle = name;
	}
	
	/**
	 * Extras : END
	 */
	
	/**
	 * Scoreboard : START
	 */
	
	protected static String scoreboardTitle = "" + ChatColor.YELLOW + "▬▬▬► " + ChatColor.GOLD + ChatColor.BOLD + "XyploCraft" + ChatColor.RESET + ChatColor.YELLOW + " ◄▬▬▬";
	
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

	/**
	 * Parkour : START
	 */
	
	protected static String ParkourExitTear = "" + ChatColor.BOLD + ChatColor.YELLOW + "Quit Parkour";
	
	public static String getParkourExitTear()
	{
		return ParkourExitTear;
	}
	
	public static void setParkourExitTear(String name)
	{
		ParkourExitTear = name;
	}
	
	/**
	 * Parkour : END
	 */
	
	/**
	 * High Jump : START
	 */
	
	protected static String highJump = "" + ChatColor.YELLOW + "High Jump";
	
	public static String getHighJump()
	{
		return highJump;
	}
	
	public static void setHighJump(String name)
	{
		highJump = name;
	}
	
	/**
	 * High Jump : END
	 */
	
	/**
	 * Toggle Player : START
	 */
	
	protected static String togglePlayers = "" + ChatColor.YELLOW + "Toggle Players";
	
	public static String getTogglePlayers()
	{
		return togglePlayers;
	}
	
	public static void setTogglePlayers(String name)
	{
		togglePlayers = name;
	}
	
	/**
	 * Toggle Player : END
	 */
	
	/**
	 * Speed : START
	 */
	
	protected static String speed = "" + ChatColor.YELLOW + "Speed";
	
	public static String getSpeed()
	{
		return speed;
	}
	
	public static void setSpeed(String name)
	{
		speed = name;
	}
	
	/**
	 * Speed : END
	 */
	
}
