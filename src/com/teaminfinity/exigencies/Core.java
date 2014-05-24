package com.teaminfinity.exigencies;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.teaminfinity.exigencies.developer.Exigencies;

public class Core extends JavaPlugin {

	public transient static Core instance;
	public transient static Exigencies exigencies;
	
	@Override
	public void onEnable() 
	{
		Core.instance = this;
		Core.exigencies = new Exigencies();
		new Registry();
		
		try
		{
			Updater updater = new Updater(this, "exigencies", getFile(),
					Updater.UpdateType.NO_DOWNLOAD, false);
			Core.exigencies.setUpdater(updater);
		}
		catch(Exception e)
		{
		}
	}

	@Override
	public void onDisable() 
	{
	}
	
	public File getFile()
	{
		return super.getFile();
	}
	
}    