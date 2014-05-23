package com.teaminfinity.exigencies.objects.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLeaveEvent extends Event {

	private transient static final HandlerList handlers = new HandlerList();
	
	private transient Player player;
	
	public PlayerLeaveEvent(Player player)
	{
		this.player = player;
	}

	public HandlerList getHandlers() 
	{
		return handlers;
	}

	public static HandlerList getHandlerList() 
	{
		return handlers;
	}

	public Player getPlayer()
	{
		return player;
	}
	
}
