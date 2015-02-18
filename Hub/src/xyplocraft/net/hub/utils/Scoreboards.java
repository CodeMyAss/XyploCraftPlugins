package xyplocraft.net.hub.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import loonyrules.co.uk.bano.Core;
import loonyrules.co.uk.bano.utils.Functions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Scoreboards
{

	/**
	 * @author LoonyRules and CodeBeasty
	 */
	
	static Core main = Core.plugin;
	
	public static void setupMain(Player p)
	{
		int SQLScore = 0;
		String[] bungeeData = null;
		String banoRank = Functions.getPlayerRank(p.getUniqueId());
		
		try {
			bungeeData = Sockets.getBungeeSocket(25533);
		} catch(Exception ex) {}
		
		try {
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `users` WHERE uuid= '" + p.getUniqueId() + "';");
			if(res.next())
			{
				SQLScore = res.getInt("points");
			}	
		} catch(SQLException sqlex) {
			sqlex.printStackTrace();
		}
		
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		Scoreboard s = sm.getNewScoreboard();
		Objective o = s.registerNewObjective("Dash", "dummy");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(Strings.getScoreboardTitle());
		
		Score spacer1 = null;
		spacer1 = o.getScore(ChatColor.DARK_AQUA + "");
		spacer1.setScore(12);
		
		Score nameTitle = null;
		Score name = null;
		nameTitle = o.getScore("" + ChatColor.GOLD + ChatColor.BOLD + "Player:");
		nameTitle.setScore(11);
		name = o.getScore(ChatColor.GREEN + p.getName());
		name.setScore(10);
		
		Score spacer2 = null;
		spacer2 = o.getScore(ChatColor.DARK_BLUE + "");
		spacer2.setScore(9);
		
		Score rankTitle = null;
		Score rank = null;
		rankTitle = o.getScore("" + ChatColor.GOLD + ChatColor.BOLD + "Rank:");
		rankTitle.setScore(8);
		rank = o.getScore(ChatColor.GREEN + banoRank.substring(0, 1).toUpperCase() + banoRank.substring(1));
		rank.setScore(7);
		
		Score spacer3 = null;
		spacer3 = o.getScore(ChatColor.DARK_GRAY + "");
		spacer3.setScore(6);
		
		Score gemsTitle = null;
		Score gems = null;
		gemsTitle = o.getScore("" + ChatColor.GOLD + ChatColor.BOLD + "Score:");
		gemsTitle.setScore(5);
		gems = o.getScore("" + ChatColor.GREEN + SQLScore);
		gems.setScore(4);
		
		Score spacer4 = null;
		spacer4 = o.getScore(ChatColor.DARK_GREEN + "");
		spacer4.setScore(3);
		
		Score onlineplayersTitle = null;
		Score onlineplayers = null;
		onlineplayersTitle = o.getScore("" + ChatColor.GOLD + ChatColor.BOLD + "Online Players:");
		onlineplayersTitle.setScore(2);
		onlineplayers = o.getScore("" + ChatColor.GREEN + bungeeData[1] + "/" + bungeeData[2]);
		onlineplayers.setScore(1);
		
		Score spacer5 = null;
		spacer5 = o.getScore(ChatColor.DARK_PURPLE + "");
		spacer5.setScore(0);
		
		p.setScoreboard(s);
		
	}

}
