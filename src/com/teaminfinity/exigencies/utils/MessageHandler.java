package com.teaminfinity.exigencies.utils;

import org.bukkit.Bukkit;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.MessageVal;

public class MessageHandler {

	public static Files messageFile = null;

	public MessageHandler()
	{
		if(messageFile == null)
		{
			messageFile = new Files(Core.instance.getDataFolder(), "messages");
			if(!(messageFile.fileExists()))
			{
				regenerateConfig();
			}
			loadAllValues();
		}
	}

	private void regenerateConfig()
	{
		if(messageFile.fileExists())
		{
			messageFile.DeleteFile();
		}
		messageFile.createFile();
		messageFile.loadFile();
		for(MessageVal data:MessageVal.values())
		{
			messageFile.set(data.toString(), data.getValue());
		}
		messageFile.saveFile();
	}

	private void loadAllValues()
	{
		messageFile.loadFile();
		try
		{
			for(MessageVal data:MessageVal.values())
			{
				try
				{
					String stringType = messageFile.getString(data.toString());
					if(stringType == null)
					{
						System.out.println("[Exigencies] Incompatible message version! Regenerating!");
						regenerateConfig();
						loadAllValues();
						return;
					}
				}
				catch(Exception e)
				{
					//Found, but wrong type.
				}

				data.setValue( MessageAPI.addColour(messageFile.getString(data.toString())) );
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
