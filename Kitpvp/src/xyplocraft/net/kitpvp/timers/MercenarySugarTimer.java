package xyplocraft.net.kitpvp.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import xyplocraft.net.kitpvp.utils.Lists;

public class MercenarySugarTimer implements Runnable
{

	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	public void run()
	{
		for (String s : Lists.mercenaryKit)
		{
			if (Lists.MercenarySugar.containsKey(s))
			{
				int timeLeft = ((Integer)Lists.MercenarySugar.get(s)).intValue();
				if (timeLeft == 0)
				{
					Lists.MercenarySugar.remove(s);
					Player p = Bukkit.getPlayerExact(s);
					if (p != null && Lists.MercenarySugar.containsKey(p.getName()))
					{
						p.sendMessage(ChatColor.YELLOW + "You may now use your Mercenary Speed ability.");
					}
				} else {
					timeLeft--;
					Lists.MercenarySugar.put(s, Integer.valueOf(timeLeft));
				}
			}
	    }
	}

}
