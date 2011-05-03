package com.bradsproject.BradleyJewell.bFlight;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;

/**
 * bFlight for Bukkit
 * 
 * @author BradleyJewell
 */
public class bFlight extends JavaPlugin
{
	private final bFlightPlayerListener playerListener = new bFlightPlayerListener(this);
	private final bFlightVehicleListener vehicleListener = new bFlightVehicleListener(this);
	final HashMap<Player, Boolean> active = new HashMap<Player, Boolean>();
	
	public static AnjoPermissionsHandler Permissions = null;
	
	public void onEnable()
	{
		// Register our events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_LOGIN, playerListener, Priority.Normal, this);
		pm.registerEvent(Event.Type.VEHICLE_DAMAGE, vehicleListener, Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
		
		// EXAMPLE: Custom code, here we just output some info so we can check
		// all is well
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion()
				+ " is enabled!");
	}
	
	public void onDisable()
	{
		// NOTE: All registered events are automatically unregistered when a
		// plugin is disabled
		
		// EXAMPLE: Custom code, here we just output some info so we can check
		// all is well
		System.out.println("bFlight has been disabled!");
	}
	
	public boolean isFlying(final Player player)
	{
		if(active.containsKey(player))
		{
			return active.get(player);
		} else
		{
			return false;
		}
	}
	
	public void setFlying(final Player player, final boolean value)
	{
		active.put(player, value);
		if(value == true)
		{
			player.sendMessage("You are now flying!");
		} else
		{
			player.sendMessage("You are no longer flying!");
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(!(sender instanceof Player))
			return false;
		
		Player player = (Player) sender;
		String commandName = cmd.getName().toLowerCase();
		
		if(commandName.equals("bgive")
				&& (bFlight.Permissions == null || bFlight.Permissions.has(player,
						"bflight.bgive")))
		{
			System.out.println(player.getDisplayName() + " got flying materials!");
			player.getInventory().addItem(new ItemStack(Material.MINECART, 1));
			player.getInventory().addItem(new ItemStack(Material.BOAT, 1));
			player.getInventory().addItem(new ItemStack(Material.ARROW, 2));
			player.getInventory().addItem(new ItemStack(Material.RAILS, 2));
		} else if(commandName.equals("bfly")
				&& (bFlight.Permissions == null || bFlight.Permissions.has(player,
						"bflight.bfly")))
		{
			if(!isFlying(player))
				setFlying(player, true);
			else
				setFlying(player, false);
		}
		return true;
	}
}
