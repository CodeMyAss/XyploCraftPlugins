package xyplocraft.net.kitpvp.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import xyplocraft.net.kitpvp.utils.Lists;

public class AssassinInvisibilityTimer implements Runnable
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	public void run()
	{
		for (String s : Lists.assassinKit)
		{
			if (Lists.AssassinInvisibility.containsKey(s))
			{
				int timeLeft = ((Integer)Lists.AssassinInvisibility.get(s)).intValue();
				if (timeLeft == 0)
				{
					Lists.AssassinInvisibility.remove(s);
					Player p = Bukkit.getPlayerExact(s);
					if (p != null && Lists.AssassinInvisibility.containsKey(p.getName()))
					{
						p.sendMessage(ChatColor.YELLOW + "You may now use your " + ChatColor.GOLD + "Assassin Invisibility" + ChatColor.YELLOW + " ability.");
					}
				} else {
					timeLeft--;
					Lists.AssassinInvisibility.put(s, Integer.valueOf(timeLeft));
				}
			}
	    }
	}

}
