package xyplocraft.net.kitpvp.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import loonyrules.co.uk.bano.Core;

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
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	static Core main = Core.plugin;

	public static void setupMain(Player p)
	{
		int SQLScore = 0;
		int SQLkills = 0;
		int SQLdeaths = 0;
		
		try {
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE uuid= '" + p.getUniqueId() + "';");
			if(res.next())
			{
				SQLScore = res.getInt("points");
				SQLkills = res.getInt("kills");
				SQLdeaths = res.getInt("deaths");
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
		
		Score PointsTitle = null;
		Score points = null;
		PointsTitle = o.getScore("" + ChatColor.GOLD + ChatColor.BOLD + "Points:");
		PointsTitle.setScore(8);
		points = o.getScore("" + ChatColor.GREEN + SQLScore);
		points.setScore(7);
		
		Score spacer3 = null;
		spacer3 = o.getScore(ChatColor.DARK_GRAY + "");
		spacer3.setScore(6);
		
		Score killsTitle = null;
		Score kills = null;
		killsTitle = o.getScore("" + ChatColor.GOLD + ChatColor.BOLD + "Kills:");
		killsTitle.setScore(5);
		kills = o.getScore("" + ChatColor.GREEN + SQLkills);
		kills.setScore(4);
		
		Score spacer4 = null;
		spacer4 = o.getScore(ChatColor.DARK_GREEN + "");
		spacer4.setScore(3);
		
		Score deathsTitle = null;
		Score deaths = null;
		deathsTitle = o.getScore("" + ChatColor.GOLD + ChatColor.BOLD + "Deaths:");
		deathsTitle.setScore(2);
		deaths = o.getScore("" + ChatColor.GREEN + SQLdeaths);
		deaths.setScore(1);
		
		Score spacer5 = null;
		spacer5 = o.getScore(ChatColor.DARK_PURPLE + "");
		spacer5.setScore(0);
		
		p.setScoreboard(s);
		
	}

}
