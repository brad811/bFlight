package com.bradsproject.BradleyJewell.bFlight;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Handle events for all Player related events
 * 
 * @author BradleyJewell
 */
public class bFlightPlayerListener extends PlayerListener
{
	private final bFlight plugin;
	
	public bFlightPlayerListener(bFlight instance)
	{
		plugin = instance;
	}
	
	@Override
	public void onPlayerLogin(PlayerLoginEvent event)
	{
		if(bFlight.Permissions == null
				|| bFlight.Permissions.has(event.getPlayer(), "bflight.bfly"))
		{
			plugin.active.put(event.getPlayer(), true);
		}
	}
}
