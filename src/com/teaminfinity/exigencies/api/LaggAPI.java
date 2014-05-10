package com.teaminfinity.exigencies.api;

import org.bukkit.Bukkit;
import org.bukkit.World;

public abstract class LaggAPI {

	public static int loadedChunks()
	{
		int total = 0;
		for(World world : Bukkit.getWorlds())
		{
			total += world.getLoadedChunks().length;
		}
		return total;
	}

	public static int totalEntities()
	{
		int total = 0;
		for(World world : Bukkit.getWorlds())
		{
			total += world.getEntities().size();
		}
		return total;
	}

}
