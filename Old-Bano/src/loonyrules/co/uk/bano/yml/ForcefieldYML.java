package loonyrules.co.uk.bano.yml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ForcefieldYML
{
	public static YamlConfiguration forcefield = null;
	public static File forcefieldFile = null;
	
	@SuppressWarnings("deprecation")
	public static void reloadForcefield()
	{
		if (forcefieldFile == null)
		{
			forcefieldFile = new File(Bukkit.getPluginManager().getPlugin("Bano").getDataFolder(), "forcefield.yml");
	    }
	    forcefield = YamlConfiguration.loadConfiguration(forcefieldFile);
	    
	    InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Bano").getResource("forcefield.yml");
	    if (defConfigStream != null)
	    {
	      YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	      if ((!forcefieldFile.exists()) || (forcefieldFile.length() == 0L)) {
	    	  forcefield.setDefaults(defConfig);
	      }
	    }
	  }
	  
	  public static FileConfiguration getForcefield()
	  {
	    if (forcefield == null) {
	      reloadForcefield();
	    }
	    return forcefield;
	  }
	  
	  public static void saveForcefield()
	  {
	    if ((forcefield == null) || (forcefieldFile == null)) {
	      return;
	    }
	    try
	    {
	      getForcefield().save(forcefieldFile);
	    }
	    catch (IOException ex)
	    {
	      Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + forcefieldFile, ex);
	    }
	  }
}
