package com.teaminfinity.exigencies.objects.events;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.teaminfinity.exigencies.enums.ConfigVal;

public class UserMessageEvent extends Event implements Cancellable {

	private transient static final HandlerList handlers = new HandlerList();
	
	private transient boolean cancelled = false;
	private transient CommandSender sender;
	private transient CommandSender reciever;
	private transient String message;
	private transient boolean spy = true;
	
	public UserMessageEvent(CommandSender sender, CommandSender reciever,
			String message)
	{
		this.setSender(sender);
		this.setReciever(reciever);
		this.setMessage(message);
		this.setSpy(ConfigVal.EXIGENCIES_LOGGER_MESSAGES_ENABLED.getBooleanValue());
	}
	
	public HandlerList getHandlers() 
	{
		return handlers;
	}

	public static HandlerList getHandlerList() 
	{
		return handlers;
	}

	@Override
	public boolean isCancelled()
	{
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}

	public CommandSender getSender() 
	{
		return sender;
	}

	public void setSender(CommandSender sender)
	{
		this.sender = sender;
	}

	public CommandSender getReciever()
	{
		return reciever;
	}

	public void setReciever(CommandSender reciever) 
	{
		this.reciever = reciever;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	public boolean isSpy() 
	{
		return spy;
	}

	public void setSpy(boolean spy)
	{
		this.spy = spy;
	}
}
