package com.teaminfinity.exigencies.developer;

import java.util.UUID;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.api.PlayerAPI;

public class Exigencies {

	/**
	 * Central data store for all
	 * developers.
	 */
	public Exigencies()
	{
	}

	/**
	 * 
	 * @param name necessary name of the user
	 * @return attempts to find the uuid of the name and returns
	 */
	@Deprecated
	public ExigenciesUser getUser(String name)
	{
		UUID id = PlayerAPI.getByName(name);
		if(id == null)
		{
			return null;
		}
		return getUser(id);
	}
	
	/**
	 * 
	 * @param id necessary UUID of user
	 * @return returns the instantiated ExigenciesUser
	 */
	public ExigenciesUser getUser(UUID id)
	{
		return new ExigenciesUser(id);
	}
	
	private transient boolean nmsCompatible = true;
	
	/**
	 * @return returns whether NMS is found to be
	 * compatible.
	 */
	public boolean isNmsCompatible() 
	{
		return nmsCompatible;
	}

	public void setNmsCompatible(boolean nmsCompatible)
	{
		this.nmsCompatible = nmsCompatible;
	}
	
	/**
	 * A very sloppy method for getting the exigencies
	 * instance, but it can be more concise.
	 * 
	 * @return returns instance of exigencies
	 */
	public static Exigencies getInstance()
	{
		return Core.exigencies;
	}
	
	public static Core getCore()
	{
		return Core.instance;
	}
	
}
