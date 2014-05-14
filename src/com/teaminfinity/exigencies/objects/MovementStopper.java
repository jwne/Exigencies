package com.teaminfinity.exigencies.objects;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.teaminfinity.exigencies.enums.MessageVal;

public class MovementStopper implements Listener {

	/**
	 * For the listener registration
	 */
	public MovementStopper()
	{
	}
	
	@Deprecated
	public MovementStopper(UUID user, MovementStopperObject stopper)
	{
		MovementStopper.append(user, stopper);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		if(!(stoppers.containsKey(e.getPlayer().getUniqueId())))
		{
			return;
		}
		stoppers.remove(e.getPlayer().getUniqueId());
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e)
	{
		if(!(stoppers.containsKey(e.getPlayer().getUniqueId())))
		{
			return;
		}
		
		if(e.getTo().toVector().equals(e.getFrom().toVector()))
		{
			return;
		}
		
		MovementStopperObject stopper = stoppers.get(e.getPlayer().getUniqueId());
		switch(stopper.getType())
		{
		case TELEPORT:
			e.getPlayer().sendMessage(MessageVal.MOVEMENT_STOPPER_TELEPORT.getValue());
			break;
		}
		stoppers.remove(e.getPlayer().getUniqueId());
	}

	public static void cycle(int since)
	{
		for(Entry<UUID, MovementStopperObject> stopper : stoppers.entrySet())
		{
			stopper.getValue().decrease(since, stopper.getKey());
			if(stopper.getValue().ready())
			{
				stopper.getValue().run();
				stoppers.remove(stopper.getKey());
			}
		}
	}
	
	private transient static HashMap<UUID, MovementStopperObject> stoppers 
		= new HashMap<>();
		
	public static void append(UUID user, MovementStopperObject stopper)
	{
		stoppers.put(user, stopper);
	}
	
}
