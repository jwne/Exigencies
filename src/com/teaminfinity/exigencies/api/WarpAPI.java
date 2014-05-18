package com.teaminfinity.exigencies.api;

import java.io.File;

import com.teaminfinity.exigencies.utils.Files;

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
			Files file = new Files(essentialsWarpDir, str); 
			Files newFile = FileAPI.getWarp(file.getName().replaceAll(".yml", ""));
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
		System.out.println("[Exigencies] Transferred " + transferred + " warps.");
		essentialsHasWarps = false;
	}
	
}
