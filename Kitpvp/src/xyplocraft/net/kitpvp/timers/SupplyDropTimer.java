package xyplocraft.net.kitpvp.timers;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.managers.SupplyDropManager;
import xyplocraft.net.kitpvp.utils.Ints;
import xyplocraft.net.kitpvp.utils.Locations;

@SuppressWarnings("deprecation")
public class SupplyDropTimer implements Runnable
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	Kitpvp main = Kitpvp.plugin;
	  
	public void run()
	{
		if ((Ints.supplyDropTimer != -1) && (Bukkit.getOnlinePlayers().length >= 2))
	    {
			switch (Ints.supplyDropTimer)
			{
				case 0: 
					Ints.supplyDropTimer = -1;
					Location supplyDropLocation = generateLocation();
					SupplyDropManager.broadcastMessage(ChatColor.GOLD + "A new Supply Drop has spawned at the following location!");
					SupplyDropManager.broadcastMessage(ChatColor.YELLOW + " x: " + supplyDropLocation.getX());
					SupplyDropManager.broadcastMessage(ChatColor.YELLOW + " y: " + supplyDropLocation.getY());
					SupplyDropManager.broadcastMessage(ChatColor.YELLOW + " z: " + supplyDropLocation.getZ());
					SupplyDropManager.spawnSupplyDrop(supplyDropLocation);
				break;
				case 60: 
				case 120: 
				case 180: 
				case 240: 
				case 300: 
					int minute = 0;
					if (Ints.supplyDropTimer == 60)
					{
						minute = 1;
					}
					if (Ints.supplyDropTimer == 120)
					{
						minute = 2;
					}
					if (Ints.supplyDropTimer == 180)
					{
						minute = 3;
					}
					if (Ints.supplyDropTimer == 240)
					{
						minute = 4;
					}
					if (Ints.supplyDropTimer == 300)
					{
						minute = 5;
					}
					if (minute == 1)
					{
						SupplyDropManager.broadcastMessage(ChatColor.GOLD + "A new Supply Drop will drop in " + ChatColor.YELLOW + minute + ChatColor.GOLD + " minute!");
					} else {
						SupplyDropManager.broadcastMessage(ChatColor.GOLD + "A new Supply Drop will drop in " + ChatColor.YELLOW + minute + ChatColor.GOLD + " minutes!");
					}
				break;
	      
				case 1: 
				case 2: 
				case 3: 
				case 4: 
				case 5: 
				case 10: 
				case 30: 
					if (Ints.supplyDropTimer == 1)
					{
						SupplyDropManager.broadcastMessage(ChatColor.GOLD + "A new Supply Drop will drop in " + ChatColor.YELLOW + Ints.supplyDropTimer + ChatColor.GOLD + " second!");
					} else {
						SupplyDropManager.broadcastMessage(ChatColor.GOLD + "A new Supply Drop will drop in " + ChatColor.YELLOW + Ints.supplyDropTimer + ChatColor.GOLD + " seconds!");
					}
				break;
			}
			Ints.supplyDropTimer = Ints.supplyDropTimer - 1;
	    }
	}
	  
	public static Location generateLocation()
	{
		Random ran = new Random();
	    int r = ran.nextInt(5);
	    
	    if (r == 0)
	    {
	      SupplyDropManager.setSupplyDropLocation(Locations.getSD1());
	      return Locations.getSD1();
	    }
	    
	    if (r == 1)
	    {
	      SupplyDropManager.setSupplyDropLocation(Locations.getSD2());
	      return Locations.getSD2();
	    }
	    
	    if (r == 2)
	    {
	      SupplyDropManager.setSupplyDropLocation(Locations.getSD3());
	      return Locations.getSD3();
	    }
	    
	    if (r == 3)
	    {
	      SupplyDropManager.setSupplyDropLocation(Locations.getSD3());
	      return Locations.getSD3();
	    }
	    
	    if (r == 4)
	    {
	      SupplyDropManager.setSupplyDropLocation(Locations.getSD4());
	      return Locations.getSD4();
	    }
	    
	    return null;
	  }

}
