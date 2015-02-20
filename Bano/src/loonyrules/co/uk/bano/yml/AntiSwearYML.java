package loonyrules.co.uk.bano.yml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class AntiSwearYML
{
	public static YamlConfiguration as = null;
	public static File asFile = null;
	
	@SuppressWarnings("deprecation")
	public static void reloadAS()
	{
		if (asFile == null)
		{
			asFile = new File(Bukkit.getPluginManager().getPlugin("Bano").getDataFolder(), "antiswear.yml");
	    }
	    as = YamlConfiguration.loadConfiguration(asFile);
	    
	    InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Bano").getResource("antiswear.yml");
	    if (defConfigStream != null)
	    {
	      YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	      if ((!asFile.exists()) || (asFile.length() == 0L)) {
	    	  as.setDefaults(defConfig);
	      }
	    }
	  }
	  
	  public static FileConfiguration getAS()
	  {
	    if (as == null) {
	      reloadAS();
	    }
	    return as;
	  }
	  
	  public static void saveAS()
	  {
	    if ((as == null) || (asFile == null)) {
	      return;
	    }
	    try
	    {
	      getAS().save(asFile);
	    }
	    catch (IOException ex)
	    {
	      Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + asFile, ex);
	    }
	  }
}
