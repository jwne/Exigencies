package com.teaminfinity.exigencies.utils;

import org.bukkit.Bukkit;

import com.teaminfinity.exigencies.Core;

public class SchedulingUtility {
	
	public static void run(Runnable runnable, int i)
	{
		Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, runnable, i);
	}
	
	public static int beginRepeating(Runnable runnable, int i)
	{
		return Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, runnable, i, i);
	}
	
	public static void stopRepeating(int id)
	{
		Bukkit.getScheduler().cancelTask(id);
	}
}