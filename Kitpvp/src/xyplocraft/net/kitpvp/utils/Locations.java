package xyplocraft.net.kitpvp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Locations
{
	
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	static World world = Bukkit.getWorld("pvp1");
	static double x = 1117.5;
	static double y = 34;
	static double z = 245.5;
	static float yaw = 1;
	static float pitch = 1;
	static Location spawn = new Location(world, x, y, z, yaw, pitch);
	
	static Location sdloc1 = new Location(world, 1093.5D, 24.0D, 298.5D);
	static Location sdloc2 = new Location(world, 1151.5D, 26.0D, 310.5D);
	static Location sdloc3 = new Location(world, 1157.5D, 29.0D, 360.5D);
	static Location sdloc4 = new Location(world, 1093.5D, 25.0D, 354.5D);
	
	public static void teleportToSpawn(Player p)
	{
		p.teleport(spawn);
	}
	
	public static Location getSpawn()
	{
		return spawn;
	}
	
	public static Location getSD1()
	{
		return sdloc1;
	}
	
	public static Location getSD2()
	{
		return sdloc2;
	}
	
	public static Location getSD3()
	{
		return sdloc3;
	}
	
	public static Location getSD4()
	{
		return sdloc4;
	}
	
}
