package loonyrules.co.uk.bano.yml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MutedYML
{
	  public static YamlConfiguration muted = null;
	  public static File mutedFile = null;
	  
	  @SuppressWarnings("deprecation")
	  public static void reloadMuted()
	  {
	    if (mutedFile == null) {
	    	mutedFile = new File(
	        Bukkit.getPluginManager().getPlugin("Bano").getDataFolder(), "muted.yml");
	    }
	    muted = YamlConfiguration.loadConfiguration(mutedFile);
	    
	    InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Bano").getResource("muted.yml");
	    if (defConfigStream != null)
	    {
	      YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	      if ((!mutedFile.exists()) || (mutedFile.length() == 0L)) {
	        muted.setDefaults(defConfig);
	      }
	    }
	  }
	  
	  public static FileConfiguration getMuted()
	  {
	    if (muted == null) {
	      reloadMuted();
	    }
	    return muted;
	  }
	  
	  public static void saveMuted()
	  {
	    if ((muted == null) || (mutedFile == null)) {
	      return;
	    }
	    try
	    {
	      getMuted().save(mutedFile);
	    }
	    catch (IOException ex)
	    {
	      Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + mutedFile, ex);
	    }
	  }
}
