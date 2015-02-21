package xyplocraft.net.prison.warps;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.error.YAMLException;

import xyplocraft.net.prison.Prison;

public class Warps implements CommandExecutor
{

	Prison main = Prison.getPlugin();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "Bruh, what are you trying to do?");
			return true;
		}
		Player p = (Player) sender;
		
		/* | /setwarp <name> | */
		if(label.equalsIgnoreCase("setwarp"))
		{
			if(args.length == 1)
			{
				
			} else checkArguments(p);
			return true;
		}
		
		/* | /warp <name> | /warp | */
		if(label.equalsIgnoreCase("warp"))
		{
			if(args.length == 0)
			{
				listWarps(p);
			}
			
			if(args.length == 1)
			{
				if(getWarpsList().contains(args[0]))
				{
					Location before = p.getLocation();
					pleaseWait(p);
					main.getBukkitScheduler().runTaskLater(main, new Runnable()
					{
						public void run()
						{
							if(p.getLocation().getX() == before.getX() && p.getLocation().getZ() == before.getZ())
							{
								String warp = args[0];
								boolean error = false;
								Location target = null;
								
								try {
									World world = Bukkit.getWorld(getWarpsConfig().getString("warpData." + warp + ".world"));
									double x = getWarpsConfig().getDouble("warpData." + warp + ".x");
									double y = getWarpsConfig().getDouble("warpData." + warp + ".y");
									double z = getWarpsConfig().getDouble("warpData." + warp + ".z");
									float yaw = getWarpsConfig().getInt("warpData." + warp + ".yaw");
									float pitch = getWarpsConfig().getInt("warpData." + warp + ".pitch");
									target = new Location(world, x, y, z, yaw, pitch);
								} catch(YAMLException yamlEx) {
									yamlEx.printStackTrace();
									error = true;
								}
								
								if(!(error) && (target != null))
								{
									p.teleport(target);
									teleportedWarp(p);
								} else nullWarp(p);
							} else youMoved(p);
						}
					}, 60L);
				} else whatWarp(p);
			}
			
			if(args.length >= 2)
			{
				checkArguments(p);
			}
			
			return true;
		}
		return false;
	}
	
	void listWarps(Player p)
	{
		p.sendMessage(ChatColor.YELLOW + "Here's a list of warps available for use: ");
		/* I'll add the rest of the code when I can be bothered :) */
	}
	
	void checkArguments(Player p)
	{
		p.sendMessage(ChatColor.RED + "Watch your arguments bro!");
	}
	
	void whatWarp(Player p)
	{
		p.sendMessage(ChatColor.RED + "That warp couldn't be found.");
	}
	
	void pleaseWait(Player p)
	{
		p.sendMessage(ChatColor.GREEN + "Please wait " + ChatColor.YELLOW + "3" + ChatColor.GREEN + " seconds to be teleported. Do not move!");
	}
	
	void youMoved(Player p)
	{
		p.sendMessage(ChatColor.RED + "Teleportation was cancelled, you moved!");
	}
	
	void nullWarp(Player p)
	{
		p.sendMessage(ChatColor.RED + "Couldn't retrieve warp data, teleportation was cancelled.");
	}
	
	void teleportedWarp(Player p)
	{
		p.sendMessage(ChatColor.GREEN + "You have a arrived at your destination.");
	}
	
	/*
	 * Getters and Setters
	 */
	
	public boolean containsWarp()
	{
		return false;
	}
	
	public List<String> getWarpsList()
	{
		return getWarpsConfig().getStringList("warps");
	}
	
	public void setWarpsList(List<String> newList)
	{
		getWarpsConfig().set("warps", newList);
		saveWarpsConfig();
	}
	
	/*
	 * Warps file (Too lazy to put this in a diff' class)
	 */
	
	private FileConfiguration customConfig = null;
	private File customConfigFile = null;
	
	public void reloadWarpsConfig()
	{
		if (customConfigFile == null)
	    {
			customConfigFile = new File(main.getDataFolder(), "customConfig.yml");
	    }
	    customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	 
	    /* Look for defaults in the jar */
	    Reader defConfigStream = null;
		try {
			defConfigStream = new InputStreamReader(main.getResource("customConfig.yml"), "UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	    if (defConfigStream != null)
	    {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        customConfig.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration getWarpsConfig()
	{
	    if (customConfig == null)
	    {
	        reloadWarpsConfig();
	    }
	    return customConfig;
	}
	
	public void saveWarpsConfig()
	{
	    if (customConfig == null || customConfigFile == null)
	    {
	        return;
	    }
	    try {
	        getWarpsConfig().save(customConfigFile);
	    } catch (IOException ex) {
	        main.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	    }
	}
	
}
