package xyplocraft.net.kitpvp.utils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Inventorys
{

	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	public static void setupMain(Player p)
	{
		p.setMaxHealth(20.0);
	    p.setHealth(20.0);
	    p.setFoodLevel(20);
	    p.setLevel(0);
	    p.setExp(0.0F);
	    p.getInventory().clear();
	    p.getInventory().setArmorContents(null);
	    for (PotionEffect pe : p.getActivePotionEffects())
	    {
	    	p.removePotionEffect(pe.getType());
	    }
	}

}
