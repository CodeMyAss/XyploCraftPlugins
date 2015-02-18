package xyplocraft.net.kitpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.managers.KitManager;
import xyplocraft.net.kitpvp.utils.Lists;

@SuppressWarnings("deprecation")
public class PlayerInteractSignEventListener implements Listener
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	Kitpvp main = Kitpvp.plugin;
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onSignChangeEvent(SignChangeEvent e)
	{
	    if (e.getLine(0).equalsIgnoreCase("[Disposal]"))
	    {
	    	if ((e.getLine(1).equalsIgnoreCase("")) && (e.getLine(2).equalsIgnoreCase("")) && (e.getLine(3).equalsIgnoreCase("")))
	    	{
	    		e.setLine(0, ChatColor.DARK_BLUE + "[" + ChatColor.WHITE + "Dispose" + ChatColor.DARK_BLUE + "]");
	    	} else {
	    		e.setLine(0, ChatColor.RED + "[" + ChatColor.WHITE + "Dispose" + ChatColor.RED + "]");
	    	}
	    }
	    if (e.getLine(0).equalsIgnoreCase("[Kit]"))
	    {
	    	if ((Lists.kits.contains(e.getLine(1).toLowerCase())) && (e.getLine(2).equalsIgnoreCase("")) && (e.getLine(3).equalsIgnoreCase(""))) {
	    		e.setLine(0, ChatColor.DARK_BLUE + "[" + ChatColor.WHITE + "Kit" + ChatColor.DARK_BLUE + "]");
	    	} else {
	    		e.setLine(0, ChatColor.RED + "[" + ChatColor.WHITE + "Kit" + ChatColor.RED + "]");
	    	}
	    }
	}
	  
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerInteractsWithSign(PlayerInteractEvent e)
	{
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getTypeId() == 68))
		{
			Block pBlock = e.getClickedBlock();
			Sign clickedSign = (Sign)pBlock.getState();
			
			if (clickedSign.getLine(0).equalsIgnoreCase(ChatColor.DARK_BLUE + "[" + ChatColor.WHITE + "Dispose" + ChatColor.DARK_BLUE + "]"))
			{
				if ((clickedSign.getLine(1).equalsIgnoreCase("")) && (clickedSign.getLine(2).equalsIgnoreCase("")) && (clickedSign.getLine(3).equalsIgnoreCase("")))
				{
					Inventory disposal = Bukkit.createInventory(null, 27, ChatColor.WHITE + "Disposal");
					e.getPlayer().openInventory(disposal);
				}
			}
			
			if (clickedSign.getLine(0).equalsIgnoreCase(ChatColor.DARK_BLUE + "[" + ChatColor.WHITE + "Kit" + ChatColor.DARK_BLUE + "]")) 
			{
				if ((clickedSign.getLine(2).equalsIgnoreCase("")) && (clickedSign.getLine(3).equalsIgnoreCase("")))
				{
					if (Lists.kits.contains(clickedSign.getLine(1).toLowerCase()))
					{
						if (!Lists.selectedKit.contains(e.getPlayer().getName()))
						{
							if (clickedSign.getLine(1).equalsIgnoreCase("starter"))
							{
								KitManager.giveStarter(e.getPlayer());
								Lists.selectedKit.add(e.getPlayer().getName());
								Lists.starterKit.add(e.getPlayer().getName());
							}
							if (clickedSign.getLine(1).equalsIgnoreCase("mercenary"))
							{
								KitManager.giveMercenary(e.getPlayer());
								Lists.selectedKit.add(e.getPlayer().getName());
								Lists.mercenaryKit.add(e.getPlayer().getName());
							}
							if (clickedSign.getLine(1).equalsIgnoreCase("assassin"))
							{
								KitManager.giveAssassin(e.getPlayer());
								Lists.selectedKit.add(e.getPlayer().getName());
								Lists.assassinKit.add(e.getPlayer().getName());
							}
							if (clickedSign.getLine(1).equalsIgnoreCase("heavy"))
							{
								KitManager.giveHeavy(e.getPlayer());
								Lists.selectedKit.add(e.getPlayer().getName());
								Lists.heavyKit.add(e.getPlayer().getName());
							}
						} else {
							e.getPlayer().sendMessage(ChatColor.RED + "You've already selected a kit.");
						}
					} else {
						e.getPlayer().sendMessage(ChatColor.RED + "It doesn't look this like kit exists, sorry about that.");
					}
				}
			}
		}
	}

}
