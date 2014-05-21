package com.teaminfinity.exigencies.api;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.teaminfinity.exigencies.objects.F;
import com.teaminfinity.exigencies.objects.exception.WarpNotFoundException;
import com.teaminfinity.exigencies.objects.exception.WorldNotFoundException;

public abstract class WarpAPI {

	public static void init()
	{
		File exigenciesWarpDir = FileAPI.getWarpDir();
		exigenciesWarpDir.mkdirs();
		
		File essentialsDir = FileAPI.getEssentialsDir();
		File essentialsWarpDir = new File(essentialsDir.getPath() + "/warps");
		essentialsHasWarps = essentialsWarpDir.exists();
		if(essentialsHasWarps)
		{
			essentialsHasWarps = essentialsWarpDir.list() != null;
			if(essentialsHasWarps)
			{
				essentialsHasWarps = essentialsWarpDir.list().length > 0;
			}
			else
			{
				System.out.println("[Exigencies] No essentials warps found. Aborted copying");
			}
		}
		else
		{
			System.out.println("[Exigencies] Essentials not found. Aborted warp copying.");
		}
		
		if(essentialsHasWarps)
		{
			transferEssentialsWarps();
		}
	}
	
	public static String[] getWarps()
	{
		return FileAPI.getWarpDir().list();
	}
	
	/*
	 * I want it to transfer all the warps from essentials to here
	 */
	private transient static boolean essentialsHasWarps = false;
	
	public static boolean essentialsHasWarps()
	{
		return essentialsHasWarps;
	}
	
	public static void transferEssentialsWarps()
	{
		System.out.println("[Exigencies] Transferring essentials warps.");
		int transferred = 0;
		File essentialsDir = FileAPI.getEssentialsDir();
		File essentialsWarpDir = new File(essentialsDir + "/warps");
		essentialsWarpDir.mkdirs();
		for(String str : essentialsWarpDir.list())
		{
			F file = new F(essentialsWarpDir, str); 
			F newFile = FileAPI.getWarp(file.getName().replaceAll(".yml", ""));
			if(newFile.fileExists())
			{
				newFile.deleteFile();
			}
			file.loadFile();
			newFile.loadFile();
			
			newFile.set("name", file.getString("name"));
			newFile.set("world", file.getString("world"));
			newFile.set("x", file.getDouble("x"));
			newFile.set("y", file.getDouble("y"));
			newFile.set("z", file.getDouble("z"));
			newFile.set("yaw", file.getFloat("yaw"));
			newFile.set("pitch", file.getFloat("pitch"));
			
			newFile.saveFile();
			file.deleteFile();
			transferred++;
		}
		System.out.println("[Exigencies] Transferred " + transferred + " warp"
				+ ((transferred > 1) ? "s." : "."));
		essentialsHasWarps = false;
	}
	
	public static void delWarp(String name) throws WarpNotFoundException
	{		
		F file = FileAPI.getWarp(name);
		if(!(file.fileExists()))
		{
			throw new WarpNotFoundException(name);
		}
		file.deleteFile();
	}
	
	public static Location getWarp(String name) throws WorldNotFoundException,
		WarpNotFoundException
	{
		F file = FileAPI.getWarp(name);
		if(!(file.fileExists()))
		{
			throw new WarpNotFoundException(name);
		}
		file.loadFile();
		World world = Bukkit.getWorld(file.getString("world"));
		if(world == null)
		{
			throw new WorldNotFoundException(file.getString("world"));
		}
		Location loc = new Location(world,
				file.getDouble("x"),
				file.getDouble("y"),
				file.getDouble("z"));
		loc.setYaw(file.getFloat("yaw"));
		loc.setPitch(file.getFloat("pitch"));
		return loc;
	}
	
	public static void setWarp(Location location, String name)
	{
		F file = FileAPI.getWarp(name);
		file.loadFile();
		file.set("name", name);
		file.set("world", location.getWorld().getName());
		file.set("x", location.getX());
		file.set("y", location.getY());
		file.set("z", location.getZ());
		file.set("yaw", location.getYaw());
		file.set("pitch", location.getPitch());
		file.saveFile();
	}
	
}
