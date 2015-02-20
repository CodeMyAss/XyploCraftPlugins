package loonyrules.co.uk.bano.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messages
{

	public static void PLAYER_IS_MUTED(Player p)
	{
		p.sendMessage(ChatColor.RED + "You cannot chat when you're muted.");
	}

	public static void WAIT_TO_CHAT(Player p)
	{
		p.sendMessage(ChatColor.RED + "You need to wait to speak again.");
	}

	public static void INSUFFICIENT_PERMISSIONS(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	}

	public static void SETRANK_ARGS_ERROR(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/setrank <player> <rank>");
	}

	public static void SETPOINTS_ARGS_ERROR(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/setpoints <player> <points>");
	}

	public static void PLAYER_IS_AREADY_BANNED(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "That player is already a banned player.");
	}

	public static void BAN_OOPS(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "It looks as if you " + ChatColor.GOLD + "forgot" + ChatColor.RED + " to add a reason.");
	}

	public static void BAN_ARGS_ERROR(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/ban <player> <reason>");
	}

	public static void PLAYER_IS_NOT_BANNED(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "That player is not a banned player.");
	}
	
	public static void UNBAN_ARGS_ERROR(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/unban <player>");
	}

	public static void MUTE_ARGS_ERROR(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/mute <player>");
	}

	public static void CANNOT_DO_THAT_TO_THAT_PLAYER(CommandSender sender)
	{
		sender.sendMessage(ChatColor.RED + "That player is immune to that function!");
	}

	public static void FLY_ARGS_ERROR(Player p)
	{
		p.sendMessage(ChatColor.RED + "Usage: " + ChatColor.GOLD + "/fly <player>");
	}
	
}
