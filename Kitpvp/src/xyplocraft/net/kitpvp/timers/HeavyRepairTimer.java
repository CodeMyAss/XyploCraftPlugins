package xyplocraft.net.kitpvp.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import xyplocraft.net.kitpvp.utils.Lists;

public class HeavyRepairTimer implements Runnable
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	public void run()
	{
	    for (String s : Lists.heavyKit)
	    {
	    	if (Lists.HeavyRepair.containsKey(s))
	    	{
	    		int timeLeft = ((Integer)Lists.HeavyRepair.get(s)).intValue();
	    		if (timeLeft == 0)
	    		{
	    			Lists.HeavyRepair.remove(s);
	    			Player p = Bukkit.getPlayerExact(s);
	    			if (p != null && Lists.heavyKit.contains(p.getName()))
	    			{
	    				p.sendMessage(ChatColor.YELLOW + "You may now use your " + ChatColor.GOLD + "Assassin Invisibility" + ChatColor.YELLOW + " ability.");
	    			}
	    		} else {
	    			timeLeft--;
	    			Lists.HeavyRepair.put(s, Integer.valueOf(timeLeft));
	    		}
	    	}
	    }
	}

}
