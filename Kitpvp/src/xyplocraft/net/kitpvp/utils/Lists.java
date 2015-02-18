package xyplocraft.net.kitpvp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

public class Lists
{
	/**
	 * @author LoonyRules
	 * Website: http://www.loonyrules.co.uk
	 * Twitter: http://www.twitter.com/RealLoonyRules
	 * YouTube: http://www.youtube.com/user/nGxIEpiiKZzx
	 */
	
	public static HashMap<String, Integer> killstreaks = new HashMap<String, Integer>();
	public static List<String> editMode = new ArrayList<String>();
	public static HashMap<Player, Double> assistList = new HashMap<Player, Double>();
	public static List<Player> assistListV = new ArrayList<Player>();
	public static List<String> kits = Arrays.asList(new String[] { "starter", "mercenary", "assassin", "heavy" });
	public static List<String> soupSigns = Arrays.asList(new String[] { "automatic", "manual" });
	public static List<String> selectedKit = new ArrayList<String>();
	public static List<String> starterKit = new ArrayList<String>();
	public static List<String> mercenaryKit = new ArrayList<String>();
	public static List<String> assassinKit = new ArrayList<String>();
	public static List<String> heavyKit = new ArrayList<String>();
	public static HashMap<String, Integer> MercenarySugar = new HashMap<String, Integer>();
	public static HashMap<String, Integer> AssassinInvisibility = new HashMap<String, Integer>();
	public static HashMap<String, Integer> HeavyRepair = new HashMap<String, Integer>();
	
}
