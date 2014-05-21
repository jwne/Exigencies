package com.teaminfinity.exigencies;

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
	}

	@Override
	public void onDisable() 
	{
	}
	
}    