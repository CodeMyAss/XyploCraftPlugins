package loonyrules.co.uk.bano.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.yml.MutedYML;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Functions
{
	static Core main = Core.plugin;
	
	public static List<String> getIgnoredPlayers()
	{
		return Lists.ignorePlayers;
	}
	
	public static void insertIgnoredPlayers(Player p)
	{
		if(!Lists.ignorePlayers.contains(p.getName()))
		{
			Lists.ignorePlayers.add(p.getName());
		}
	}
	
	public static void removeIgnoredPlayers(Player p)
	{
		if(Lists.ignorePlayers.contains(p.getName()))
		{
			Lists.ignorePlayers.remove(p.getName());
		}
	}
	
	public static void RESET_MUTED_LIST()
	{
		Lists.mutedPlayers.clear();
		for (String s : MutedYML.getMuted().getStringList("muted"))
		{
			Lists.mutedPlayers.add(s);
		}
	}

	public static void MANUAL_RESET_DISPAY_NAMES(Player p)
	{
		Lists.playersRanks.remove(p);
		if (p != null) {
			// First join
			String rank = null;
			UUID uuid = p.getUniqueId();
			try {
				ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `users` WHERE uuid= '" + uuid + "';");
				if (res.next()) {
					rank = res.getString("rank");
					Lists.playersRanks.put(p.getUniqueId(), rank.toLowerCase());
				} else main.c.createStatement().executeUpdate("INSERT INTO `users` (`uuid`, `ign`, `rank`, `points`) VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', '" + "Default" + "', '0');");
			} catch (SQLException sql) {
				sql.printStackTrace();
			}
			//
			Functions.SETUP_DISPLAY_NAME(p);
		}
	}

	public static String getPlayerRank(UUID uuid)
	{
		return Lists.playersRanks.get(uuid);
	}
	
	public static void setPlayerRank(UUID uuid, String rank)
	{
		if(Lists.playersRanks.containsKey(uuid))
		{
			Lists.playersRanks.remove(uuid);
		}
		Lists.playersRanks.put(uuid, rank);
	}
	
	public static void SETUP_DISPLAY_NAME(Player p)
	{
		String name = p.getName();
		UUID uuid = p.getUniqueId();

		String rank = ChatColor.RESET + "null" + " » " + name;

		if (Lists.playersRanks.get(uuid) != null)
		{
			if (Lists.playersRanks.get(uuid).equalsIgnoreCase("user"))
			{
				rank = "" + ChatColor.YELLOW + ChatColor.BOLD + "User" + ChatColor.RESET + ChatColor.GRAY + " » " + ChatColor.WHITE + name;
			}

			if (Lists.playersRanks.get(uuid).equalsIgnoreCase("xyplo++"))
			{
				rank = "" + ChatColor.GOLD + ChatColor.BOLD + "Xyplo++" + ChatColor.RESET + ChatColor.GRAY + " » " + ChatColor.WHITE + name;
			}

			if (Lists.playersRanks.get(uuid).equalsIgnoreCase("xyplo+"))
			{
				rank = "" + ChatColor.GREEN + ChatColor.BOLD + "Xyplo+" + ChatColor.RESET + ChatColor.GRAY + " » " + ChatColor.WHITE + name;
			}

			if (Lists.playersRanks.get(uuid).equalsIgnoreCase("xyplo"))
			{
				rank = "" + ChatColor.BLUE + ChatColor.BOLD + "Xyplo" + ChatColor.RESET + ChatColor.GRAY + " » " + ChatColor.WHITE + name;
			}

			if (Lists.playersRanks.get(uuid).equalsIgnoreCase("mod"))
			{
				rank = "" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Mod" + ChatColor.RESET + ChatColor.GRAY + " » " + ChatColor.WHITE + name;
			}

			if (Lists.playersRanks.get(uuid).equalsIgnoreCase("admin"))
			{
				rank = "" + ChatColor.RED + ChatColor.BOLD + "Admin" + ChatColor.RESET + ChatColor.GRAY + " » " + ChatColor.WHITE + name;
			}

			if (Lists.playersRanks.get(uuid).equalsIgnoreCase("dev"))
			{
				rank = "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Developer" + ChatColor.RESET + ChatColor.GRAY + " » " + ChatColor.WHITE + name;
			}

			if (Lists.playersRanks.get(uuid).equalsIgnoreCase("owner"))
			{
				rank = "" + ChatColor.AQUA + ChatColor.BOLD + "Owner" + ChatColor.RESET + ChatColor.GRAY + " » " + ChatColor.WHITE + name;
			}
		}

		p.setDisplayName(rank);
		p.setCustomName(rank);
		p.setCustomNameVisible(true);
		p.setPlayerListName(rank);
	}

	@SuppressWarnings("deprecation")
	public static void SET_RANK(String string, String string2, CommandSender sender)
	{
		try {
			OfflinePlayer p = Bukkit.getOfflinePlayer(string);
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `users` WHERE uuid= '" + p.getUniqueId() + "';");
			if (res.next()) {
				sender.sendMessage(ChatColor.GREEN + "You changed the rank of: " + ChatColor.AQUA + string + ChatColor.GREEN + " to " + ChatColor.AQUA + string2);
				main.c.createStatement().executeUpdate("UPDATE `users` SET rank= '" + string2 + "' WHERE uuid= '" + p.getUniqueId() + "'");
			} else {
				sender.sendMessage(ChatColor.RED + "The player could not be found in the database.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			MANUAL_RESET_DISPAY_NAMES(p);
		}
	}

	@SuppressWarnings("deprecation")
	public static void SET_POINTS(String string, Integer i, CommandSender sender)
	{
		try {
			OfflinePlayer p = Bukkit.getOfflinePlayer(string);
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `users` WHERE uuid= '" + p.getUniqueId() + "';");
			if (res.next()) {
				if (sender instanceof Player) {
					sender.sendMessage(ChatColor.GREEN + "You changed the coins of: " + ChatColor.AQUA + string + ChatColor.GREEN + " to " + ChatColor.AQUA + i);
				}
				main.c.createStatement().executeUpdate("UPDATE `users` SET points= '" + i + "' WHERE uuid= '" + p.getUniqueId() + "'");
			} else {
				sender.sendMessage(ChatColor.RED + "The player could not be found in the database.");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static boolean isMuted(String s) {
		if (MutedYML.getMuted().getStringList("muted").contains(s))
		{
			return true;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public static boolean isBanned(String s)
	{
		OfflinePlayer p = Bukkit.getOfflinePlayer(s);
		try {
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `bano_bans` WHERE bannedUUID= '" + p.getUniqueId() + "';");
			if (res.next()) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public static void BAN_PAYER(String string, CommandSender sender, String reason)
	{
		OfflinePlayer p = Bukkit.getOfflinePlayer(string);
		try {
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `bano_bans` WHERE bannedUUID= '" + p.getUniqueId() + "';");
			if (res.next()) {
				return;
			}
			if (sender instanceof Player) {
				Player banner = (Player) sender;
				main.c.createStatement().executeUpdate("INSERT INTO `bano_bans` (`bannedUUID`, `bannedIGN`, `bannerUUID`, `bannerIGN`, `reason`) VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', '" + banner.getUniqueId() + "', '" + banner.getName() + "', '" + reason + "');");
				noticeNewBan(p.getName(), banner, reason);
			} else {
				main.c.createStatement().executeUpdate("INSERT INTO `bano_bans` (`bannedUUID`, `bannedIGN`, `bannerUUID`, `bannerIGN`, `reason`) VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', '" + "CONSOLE" + "', '" + "CONSOLE" + "', '" + reason + "');");
				noticeNewBan(p.getName(), sender, reason);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void noticeNewBan(String name, CommandSender sender, String reason)
	{
		String banner = sender.getName();
		Bukkit.broadcastMessage(ChatColor.AQUA + name + ChatColor.GOLD + " has been banned by " + ChatColor.AQUA + banner);
		Bukkit.broadcastMessage(ChatColor.YELLOW + " - Reason: " + ChatColor.GREEN + reason);
		Bukkit.broadcastMessage(ChatColor.YELLOW + " - Length: " + ChatColor.GREEN + "Permanant");

		Player p = Bukkit.getPlayer(name);
		if (p != null) {
			p.kickPlayer(ChatColor.GRAY + "You were banned by " + ChatColor.GOLD + banner + ChatColor.GRAY + "." + ChatColor.LIGHT_PURPLE + " Reason: " + ChatColor.GREEN + reason);
		}
	}

	@SuppressWarnings("deprecation")
	public static void UNBAN_PAYER(String string, CommandSender sender)
	{
		OfflinePlayer p = Bukkit.getOfflinePlayer(string);
		try {
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `bano_bans` WHERE bannedUUID= '" + p.getUniqueId() + "';");
			if (res.next()) {
				main.c.createStatement().executeUpdate("DELETE FROM `bano_bans` WHERE bannedUUID= '" + p.getUniqueId() + "';");
				noticeNewUnBan(p.getName(), sender);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void noticeNewUnBan(String name, CommandSender sender)
	{
		String unbanner = sender.getName();
		Bukkit.broadcastMessage(ChatColor.AQUA + name + ChatColor.GOLD + " has been unbanned by " + ChatColor.AQUA + unbanner);
	}

	@SuppressWarnings("deprecation")
	public static void UNMUTE_PLAYER(String s, CommandSender sender)
	{
		List<String> a = MutedYML.getMuted().getStringList("muted");
		a.remove(s);
		MutedYML.getMuted().set("muted", a);
		MutedYML.saveMuted();
		RESET_MUTED_LIST();

		Player p2 = Bukkit.getPlayerExact(s);
		if (p2 != null) {
			p2.sendMessage("" + ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " unmuted you.");
		}

		sender.sendMessage(ChatColor.AQUA + s + ChatColor.GOLD + " is now unmuted.");
	}

	@SuppressWarnings("deprecation")
	public static void MUTE_PLAYER(String s, CommandSender sender)
	{
		List<String> a = MutedYML.getMuted().getStringList("muted");
		a.add(s);
		MutedYML.getMuted().set("muted", a);
		MutedYML.saveMuted();
		RESET_MUTED_LIST();

		Player p2 = Bukkit.getPlayerExact(s);
		if (p2 != null) {
			p2.sendMessage("" + ChatColor.AQUA + sender.getName() + ChatColor.GOLD + " muted you.");
		}

		sender.sendMessage(ChatColor.AQUA + s + ChatColor.GOLD + " is now muted.");
	}

	public static boolean checkFly(Player p)
	{
		if (Lists.fly.contains(p))
		{
			return true;
	    }
		return false;
	}

	public static void flyOn(Player p)
	{
		p.setAllowFlight(true);
	    Lists.fly.add(p);
	}
	  
	public static void flyOff(Player p)
	{
	    p.setAllowFlight(false);
	    p.setFlying(false);
	    Lists.fly.remove(p);
	}
	
}