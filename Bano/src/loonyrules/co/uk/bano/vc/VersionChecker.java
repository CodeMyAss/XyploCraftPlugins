package loonyrules.co.uk.bano.vc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.annotation.processing.FilerException;

import org.bukkit.Bukkit;

import loonyrules.co.uk.bano.Core;

public class VersionChecker
{
	
	static Core main = Core.plugin;
	static double latestVersion = 0.0;
	
	public static void checkForUpdate()
	{	
//		double thisVersion = main.version;
//		latestVersion = 0.0;
//		String downloadLink = null;
//		String plugin = "bano";
//		String finalDl = null;
//		
//		try {
//			ResultSet res = main.c.createStatement().executeQuery("SELECT * FROM `pl_versions` WHERE plugin= '" + plugin + "';");
//			if(res.next())
//			{
//				latestVersion = res.getDouble("updatedVersion");
//				downloadLink = res.getString("download");
//				String s[] = null;
//				
//				if(thisVersion < latestVersion)
//				{
//					try {
//						URL url = new URL(downloadLink);
//						Scanner input = new Scanner(url.openStream());
//						String line = null;
//						while(input.hasNext())
//						{
//							line = input.nextLine();
//							s = line.split("=");
//							finalDl = s[1];
//						}
//						
//						if(finalDl != null)
//						{
//							if(latestVersion > thisVersion)
//							{
//								System.out.println("New version of Bano has been downloaded! (Old: v" + thisVersion + ") (New: v" + latestVersion + ")");
//								File f = new File("/home/minecraft/multicraft/servers/server4/plugins/Bano.jar");
//								downloadFile(finalDl, f);
//							} else System.out.println("Bano is up to date, v" + thisVersion + "!");
//						}
//					} catch(IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		} catch (SQLException sql) {
//			sql.printStackTrace();
//		}
		
	}
	
	public static void downloadFile(String sourcePath, File destinationPath)
	{
		   try
		   {
			   if(destinationPath != null && destinationPath.getPath() != null && latestVersion != main.version)
			   {
				   URL url = new URL(sourcePath);
				   Files.delete(destinationPath.toPath());
				   Files.copy(url.openStream(), destinationPath.toPath(),StandardCopyOption.REPLACE_EXISTING);
				   Bukkit.getPluginManager().disablePlugin(main);
				   Bukkit.getPluginManager().enablePlugin(main);
			   }
		   } catch (IOException e) {}
    }
	
}
