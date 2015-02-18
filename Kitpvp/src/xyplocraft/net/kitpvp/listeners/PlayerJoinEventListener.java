package xyplocraft.net.kitpvp.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import xyplocraft.net.kitpvp.Kitpvp;
import xyplocraft.net.kitpvp.utils.Inventorys;
import xyplocraft.net.kitpvp.utils.Locations;
import xyplocraft.net.kitpvp.utils.Scoreboards;

public class PlayerJoinEventListener implements Listener
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	Kitpvp main = Kitpvp.plugin;
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerJoinEvent(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		p.setGameMode(GameMode.ADVENTURE);
		Locations.teleportToSpawn(p);
		Scoreboards.setupMain(p);
		Inventorys.setupMain(p);
		p.setWalkSpeed(0.2F);
	}
	
}
