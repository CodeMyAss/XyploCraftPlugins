package xyplocraft.net.kitpvp.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;

import xyplocraft.net.kitpvp.Kitpvp;

public class Grant
{

	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	static Kitpvp main = Kitpvp.plugin;
	
	public static void assist(Player a)
	{
		UUID uuid = a.getUniqueId();
	    try
	    {
	    	ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE uuid= '" + uuid + "';");
	    	if (res.next())
	    	{
	    		int assists = res.getInt("assists");
	    		int newAssists = assists++;
	    		main.c.createStatement().executeUpdate("UPDATE `kitpvp_data` SET assists= '" + newAssists + "' WHERE uuid= '" + uuid + "'");
	    	}
	    } catch (SQLException sql) {
	    	sql.printStackTrace();
	    }
	}
	  
	public static void death(Player d)
	{
		try
		{
			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE uuid= '" + d.getUniqueId() + "';");
			if (res.next())
			{
				int deaths = res.getInt("deaths");
				deaths++;int newDeaths = deaths;
				main.c.createStatement().executeUpdate("UPDATE `kitpvp_data` SET deaths= '" + newDeaths + "' WHERE uuid= '" + d.getUniqueId() + "'");
			}
	    } catch (SQLException sql) {
	      sql.printStackTrace();
	    }
	}
	  
	public static void kill(Player k)
	{
		UUID uuid = k.getUniqueId();
	    try
	    {
	    	ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `kitpvp_data` WHERE uuid= '" + uuid + "';");
	    	if (res.next())
	    	{
	    		int kills = res.getInt("kills");
	    		kills++;int newKills = kills;
	    		int points = res.getInt("points");
	    		points += 15;
	    		main.c.createStatement().executeUpdate("UPDATE `kitpvp_data` SET kills= '" + newKills + "' WHERE uuid= '" + uuid + "'");
	    		main.c.createStatement().executeUpdate("UPDATE `kitpvp_data` SET points= '" + points + "' WHERE uuid= '" + uuid + "'");
	    	}
	    } catch (SQLException sql) {
	      sql.printStackTrace();
	    }
	    Scoreboards.setupMain(k);
	}
	
}
