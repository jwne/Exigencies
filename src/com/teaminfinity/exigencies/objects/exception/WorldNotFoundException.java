package com.teaminfinity.exigencies.objects.exception;

@SuppressWarnings("serial")
public class WorldNotFoundException extends Exception {
	
	public WorldNotFoundException(String string) 
	{
		this.world = string;
	}

	private transient final String world;
	
	public String getWorld()
	{
		return world;
	}
}
