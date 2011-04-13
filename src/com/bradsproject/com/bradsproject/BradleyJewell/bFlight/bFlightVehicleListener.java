package com.bradsproject.BradleyJewell.bFlight;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.util.Vector;

public class bFlightVehicleListener extends VehicleListener
{
	private final bFlight plugin;
	
	public bFlightVehicleListener(bFlight instance)
	{
		plugin = instance;
	}
	
	@Override
	public void onVehicleDamage (VehicleDamageEvent event)
	{
		double vX = 5;
		double vY = 0.5;
		Player player = (Player) event.getVehicle().getPassenger();
		if(plugin.isFlying(player) && player.getItemInHand().getType() == Material.ARROW)
		{
			if(event.getVehicle().getPassenger() == event.getAttacker())
			{
				Location location = player.getLocation();
				int yaw = (int)location.getYaw();
				yaw = Math.abs(yaw % 360);
				
				if(yaw <= 38 || yaw >= 320)
				{
					//System.out.println("West! ("+yaw+")");
					event.getVehicle().setVelocity(new Vector(0,vY,vX));
				}
				else if(yaw >= 216 && yaw < 320 )
				{
					//System.out.println("North! ("+yaw+")");
					event.getVehicle().setVelocity(new Vector(vX,vY,0));
				}
				else if(yaw >= 143 && yaw < 216)
				{
					//System.out.println("East! ("+yaw+")");
					event.getVehicle().setVelocity(new Vector(0,vY,-vX));
				}
				else
				{
					//System.out.println("South! ("+yaw+")");
					event.getVehicle().setVelocity(new Vector(-vX,vY,0));
				}
				
				event.setDamage(event.getDamage() / 2);
			}
			event.setCancelled(true);
		}
	}
}
