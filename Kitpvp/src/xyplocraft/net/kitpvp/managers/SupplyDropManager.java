package xyplocraft.net.kitpvp.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;

import xyplocraft.net.kitpvp.Kitpvp;

@SuppressWarnings({ "deprecation", "unused" })
public class SupplyDropManager
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	static Kitpvp main = Kitpvp.plugin;
	public static boolean enable = false;
	static Location supplyDropLocation = null;
	  
	public static Location getSupplyDropLocation()
	{
		return supplyDropLocation;
	}
	  
	public static void setSupplyDropLocation(Location location)
	{
	    supplyDropLocation = location;
	}
	  
	public static boolean supplyDropDown()
	{
		if (enable)
		{
			return true;
	    }
	    return false;
	}
	  
	public static void spawnSupplyDrop(Location loc)
	{
	    loc.setY(loc.getY() + 20.0D);
	    FallingBlock block = loc.getWorld().spawnFallingBlock(loc, Material.BEACON, (byte)0);
	    loc.getWorld().playSound(loc, Sound.LEVEL_UP, 100.0F, 100.0F);
	    loc.setY(loc.getY() - 10.0D);
	    setSupplyDropLocation(loc);
	    enable = true;
	}
	  
	public static boolean checkSupplyDrop(Location loc)
	{
	    if (supplyDropLocation == loc)
	    {
	    	return true;
	    }
	    return false;
	}
	  
	public static void broadcastMessage(String string)
	{
		Bukkit.broadcastMessage(string);
	}
	
}
