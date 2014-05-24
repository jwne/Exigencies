package com.teaminfinity.exigencies.developer;

import java.util.UUID;

import org.bukkit.Bukkit;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.Updater;
import com.teaminfinity.exigencies.api.PlayerAPI;

public class Exigencies {

	public Exigencies()
	{
		this.setNmsCompatible(checkCompat());
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
	
	private transient final String edition = "R1";
	private transient final String version = "1.7";
	
	private boolean checkCompat()
	{
		return matchesVersion(edition) && matchesVersion(version);
	}
	
    public static boolean matchesVersion(String s){
        return Bukkit.getVersion().contains(s)
        		|| Bukkit.getServer().getClass().getPackage().getName().toLowerCase()
        		.contains(s.toLowerCase());
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
	
	private transient Updater updater = null;

	public boolean canUpdate()
	{
		return updater.getResult().equals(
				Updater.UpdateResult.UPDATE_AVAILABLE);
	}
	
	public Updater.UpdateResult getUpdateResult() 
	{
		return updater.getResult();
	}

	public void setUpdater(Updater updater)
	{
		this.updater = updater;
	}
	
	public Updater getUpdater()
	{
		return updater;
	}

	public String getLatestVersion() 
	{
		if(updater.getLatestVersionString() == null)
		{
			return Core.instance.getDescription().getVersion();
		}
		return updater.getLatestVersionString();
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
