package com.teaminfinity.exigencies;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

	public transient static Core instance;
	
	@Override
	public void onEnable() 
	{
		Core.instance = this;
		new Registry();
	}

	@Override
	public void onDisable() 
	{
	}
	
}    