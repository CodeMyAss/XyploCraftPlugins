package xyplocraft.net.hub.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Lists
{
	public static List<Player> isFlying = new ArrayList<Player>();
	public static List<Player> editMap = new ArrayList<Player>();
	public static List<Material> stopBlocksInteract = Arrays.asList(new Material[] { Material.AIR });
	public static List<Player> cooldownPlayerVis = new ArrayList<Player>();
	public static List<Player> isHigh = new ArrayList<Player>();
	public static List<Player> invis = new ArrayList<Player>();
	
	public static HashMap<Player, String> Parkour = new HashMap<Player, String>();
}
