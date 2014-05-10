package com.teaminfinity.exigencies.objects.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JailDeleteEvent extends Event {

	private transient static final HandlerList handlers = new HandlerList();
	
	private transient String name;
	
	public JailDeleteEvent(String name)
	{
		this.name = name;
	}

	public HandlerList getHandlers() 
	{
		return handlers;
	}

	public static HandlerList getHandlerList() 
	{
		return handlers;
	}

	public String getName()
	{
		return name;
	}
	
}
