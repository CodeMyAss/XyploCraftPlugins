package xyplocraft.net.kitpvp.timers;

import org.bukkit.Effect;
import org.bukkit.Location;

import xyplocraft.net.kitpvp.managers.SupplyDropManager;

public class SupplyDropParticleTimer implements Runnable
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	public void run()
	{
	    if (SupplyDropManager.supplyDropDown())
	    {
	    	Location loc = SupplyDropManager.getSupplyDropLocation();
	    	loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 0, 50);
	    }
	}

}
