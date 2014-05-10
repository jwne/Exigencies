package com.teaminfinity.exigencies.objects.command;

import java.util.UUID;

import org.bukkit.ChatColor;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.TempValueType;

public class TempResult {

	public TempResult(UUID id, long value, TempValueType type)
	{
		this.id = id;
		this.value = value;
		this.type = type;
	}
	
	private transient UUID id;
	private transient long value;
	private transient TempValueType type;
	
	public long getTimeLeft()
	{
		return (System.currentTimeMillis() - value);
	}
	
	public String getStrTimeLeft(boolean stripColor)
	{
		if(stripColor)
		{
			return ChatColor.stripColor(MessageAPI.getTimeLeftString(getTimeLeft()));
		}
		return MessageAPI.getTimeLeftString(getTimeLeft());
	}
	
	public boolean isActive()
	{
		if(value == 0)
		{
			return false;
		}
		if(System.currentTimeMillis() >= value)
		{
			return true;
		}
		return true;
	}
	
	public UUID getId()
	{
		return id;
	}
	
	public long getValue()
	{
		return value;
	}
	
	public TempValueType getType()
	{
		return type;
	}
	
}
