package com.teaminfinity.exigencies.objects.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerFirstJoinEvent extends Event {

	private transient static final HandlerList handlers = new HandlerList();
	
	private transient PlayerJoinEvent joinEvent;
	private transient Player player;

	public PlayerFirstJoinEvent(PlayerJoinEvent joinEvent)
	{
		this.joinEvent = joinEvent;
		this.player = joinEvent.getPlayer();
	}

	public PlayerJoinEvent getJoinEvent() 
	{
		return joinEvent;
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
