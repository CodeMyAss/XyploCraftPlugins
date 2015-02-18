package xyplocraft.net.hub.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Locations
{

	/**
	 * @author LoonyRules and CodeBeasty
	 */
	
	protected static World world = Bukkit.getWorld("hub");
	protected static double x = -81.5;
	protected static double y = 30;
	protected static double z = 308.5;
	protected static float yaw = 1;
	protected static float pitch = 0;
	protected static Location spawn = new Location(world, x, y, z, yaw, pitch);
	
	public static void teleportToSpawn(Player p)
	{
		p.teleport(spawn);
	}

}
