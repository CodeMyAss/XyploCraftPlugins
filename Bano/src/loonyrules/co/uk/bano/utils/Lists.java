package loonyrules.co.uk.bano.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Lists
{
	public static List<Integer> timers = new ArrayList<Integer>();	
	public static List<String> cancelledCommands = Arrays.asList(new String[] { "/pl", "/plugins", "/?", "/bukkit", "/me", "/kill", "/minecraft", "/ver" });
	public static List<Player> godmode = new ArrayList<Player>();
	public static List<Player> fly = new ArrayList<Player>();
	public static HashMap<Player, String> muted = new HashMap<Player, String>();
	public static List<Player> CAN_SPEAK = new ArrayList<Player>();
	public static HashMap<UUID, String> playersRanks = new HashMap<UUID, String>();
	public static List<String> mutedPlayers = new ArrayList<String>();
	public static HashMap<UUID, String> altCheck = new HashMap<UUID, String>();
	public static List<String> ignorePlayers = new ArrayList<String>();
}
