package com.teaminfinity.exigencies.utils;

import org.bukkit.Bukkit;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.ConfigVal;

public class ConfigHandler {

	public static Files configFile = null;
	
	public void saveValue(ConfigVal val)
	{
		configFile.set(val.key, val.getValue());
		configFile.saveFile();
	}
	
	public ConfigHandler()
	{
		if(configFile == null)
		{
			configFile = new Files(Core.instance.getDataFolder(), "config");
			if(!(configFile.fileExists()))
			{
				regenerateConfig();
			}
		}
	}
	
	public void regenerateConfig()
	{
		if(configFile.fileExists())
		{
			configFile.DeleteFile();
		}
		configFile.createFile();
		configFile.loadFile();
		for(ConfigVal data:ConfigVal.values())
		{
			configFile.set(data.key, data.getValue());
		}
		configFile.saveFile();
	}
	
	public void loadAllValues()
	{
		configFile.loadFile();
		try
		{
			for(ConfigVal data:ConfigVal.values())
			{
				try
				{
					String stringType = configFile.getString(data.key);
					if(stringType == null)
					{
						System.out.println("[Exigencies] Incompatible config version! Regenerating!");
						regenerateConfig();
						loadAllValues();
						return;
					}
				}
				catch(Exception e)
				{
					//Found, but wrong type.
				}
				
				switch(data.type)
				{
				case STRING:
					data.setValue( MessageAPI.addColour(configFile.getString(data.key)) );
					break;
				case INTEGER:
					data.setValue( configFile.getInt(data.key) );
					break;
				case DOUBLE:
					data.setValue( configFile.getDouble(data.key) );
					break;
				case LIST_STRING:
					data.setValue( configFile.getStringList(data.key) );
					break;
				case BOOLEAN:
					data.setValue( configFile.getBoolean(data.key) );	
					break;
				case LONG:
					data.setValue( configFile.getLong(data.key) );
					break;
				case FLOAT:
					data.setValue( configFile.getFloat(data.key) );
					break;
				default:
					System.out.println("[Exigencies] Unable to find value type for: "+data.toString());
					break;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("[Exigencies] Unable to load values! Restarting!");
			Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, new Runnable()
			{

				@Override
				public void run() 
				{
					regenerateConfig();
					loadAllValues();
				}
				
			}
			, 20L);
		}
	}
	
}
