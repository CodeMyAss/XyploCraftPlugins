package loonyrules.co.uk.bano.yml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FlyYML
{
	public static YamlConfiguration fly = null;
	public static File flyFile = null;
	
	@SuppressWarnings("deprecation")
	public static void reloadFly()
	{
		if (flyFile == null)
		{
			flyFile = new File(Bukkit.getPluginManager().getPlugin("Bano").getDataFolder(), "fly.yml");
	    }
	    fly = YamlConfiguration.loadConfiguration(flyFile);
	    
	    InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Bano").getResource("fly.yml");
	    if (defConfigStream != null)
	    {
	      YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	      if ((!flyFile.exists()) || (flyFile.length() == 0L)) {
	    	  fly.setDefaults(defConfig);
	      }
	    }
	  }
	  
	  public static FileConfiguration getFly()
	  {
	    if (fly == null) {
	      reloadFly();
	    }
	    return fly;
	  }
	  
	  public static void saveFly()
	  {
	    if ((fly == null) || (flyFile == null)) {
	      return;
	    }
	    try
	    {
	      getFly().save(flyFile);
	    }
	    catch (IOException ex)
	    {
	      Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + flyFile, ex);
	    }
	  }
}
