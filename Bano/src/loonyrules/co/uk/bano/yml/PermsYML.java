package loonyrules.co.uk.bano.yml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class PermsYML
{
	
	public static YamlConfiguration perms = null;
	public static File permsFile = null;
	
	@SuppressWarnings("deprecation")
	public static void reloadPerms()
	{
		if (permsFile == null)
		{
			permsFile = new File(Bukkit.getPluginManager().getPlugin("Bano").getDataFolder(), "permissions.yml");
	    }
	    perms = YamlConfiguration.loadConfiguration(permsFile);
	    
	    InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Bano").getResource("permissions.yml");
	    if (defConfigStream != null)
	    {
	      YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	      if ((!permsFile.exists()) || (permsFile.length() == 0L)) {
	    	  perms.setDefaults(defConfig);
	      }
	    }
	  }
	  
	  public static FileConfiguration getPerms()
	  {
	    if (perms == null) {
	      reloadPerms();
	    }
	    return perms;
	  }
	  
	  public static void savePerms()
	  {
	    if ((perms == null) || (permsFile == null)) {
	      return;
	    }
	    try
	    {
	      getPerms().save(permsFile);
	    }
	    catch (IOException ex)
	    {
	      Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + permsFile, ex);
	    }
	  }
	
}
