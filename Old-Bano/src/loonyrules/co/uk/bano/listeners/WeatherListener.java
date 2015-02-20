package loonyrules.co.uk.bano.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import loonyrules.co.uk.bano.Core;

public class WeatherListener implements Listener
{
	
	static Core main = Core.plugin;
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onWeatherChangeEvent(WeatherChangeEvent e)
	{
		e.setCancelled(true);
	}
	
}
