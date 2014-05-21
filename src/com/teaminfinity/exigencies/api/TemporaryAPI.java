package com.teaminfinity.exigencies.api;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.enums.TempValueType;
import com.teaminfinity.exigencies.objects.F;
import com.teaminfinity.exigencies.objects.command.TempResult;

public abstract class TemporaryAPI {

	private transient static F temporarydatabase = null;
	
	public static void init()
	{
		temporarydatabase = FileAPI.getTemporaryDatabaseFile();
		if(!(temporarydatabase.fileExists()))
		{
			temporarydatabase.createFile();
		}
		temporarydatabase.loadFile();
	}
	
	public static void set(Player player, TempValueType type, long value)
	{
		temporarydatabase.set(type.getPath() + player.getUniqueId().toString(), value);
	}
	
	public static TempResult get(Player player, TempValueType type)
	{
		long value = temporarydatabase.getLong(type.getPath() + player.getUniqueId().toString());
		return new TempResult(player.getUniqueId(), value, type);
	}
	
}
