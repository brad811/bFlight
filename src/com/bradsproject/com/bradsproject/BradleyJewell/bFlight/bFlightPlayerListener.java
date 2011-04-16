package com.bradsproject.BradleyJewell.bFlight;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.util.Vector;

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
	
	@Override
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if(bFlight.Permissions == null
				|| bFlight.Permissions.has(event.getPlayer(), "bflight.bfly"))
		{
			if(plugin.isFlying(player) && player.getItemInHand().getType() == Material.FEATHER)
			{
				Vector dir = player.getLocation().getDirection();
				Vector vec = new Vector(dir.getX()*0.8, 0.8, dir.getZ()*0.8);
				player.setVelocity(vec);
				player.setFallDistance(-100);
			}
		}
	}
}
