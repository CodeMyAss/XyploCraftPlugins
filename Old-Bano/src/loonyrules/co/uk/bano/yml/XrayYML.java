package loonyrules.co.uk.bano.yml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class XrayYML
{
	public static YamlConfiguration xray = null;
	public static File xrayFile = null;
	
	@SuppressWarnings("deprecation")
	public static void reloadXray()
	{
		if (xrayFile == null)
		{
			xrayFile = new File(Bukkit.getPluginManager().getPlugin("Bano").getDataFolder(), "xray.yml");
	    }
	    xray = YamlConfiguration.loadConfiguration(xrayFile);
	    
	    InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Bano").getResource("xray.yml");
	    if (defConfigStream != null)
	    {
	      YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	      if ((!xrayFile.exists()) || (xrayFile.length() == 0L)) {
	    	  xray.setDefaults(defConfig);
	      }
	    }
	  }
	  
	  public static FileConfiguration getXray()
	  {
	    if (xray == null) {
	      reloadXray();
	    }
	    return xray;
	  }
	  
	  public static void saveXray()
	  {
	    if ((xray == null) || (xrayFile == null)) {
	      return;
	    }
	    try
	    {
	      getXray().save(xrayFile);
	    }
	    catch (IOException ex)
	    {
	      Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + xrayFile, ex);
	    }
	  }
}
