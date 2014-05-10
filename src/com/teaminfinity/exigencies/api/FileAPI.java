package com.teaminfinity.exigencies.api;

import java.io.File;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.utils.Files;

public abstract class FileAPI {

	private transient static boolean USE_UUID = true;

	public static Files getTemporaryDatabaseFile()
	{
		return new Files(new File(Core.instance.getDataFolder() + "/plugindata/edit/with/caution"), "temporarydatabase");
	}

	public static Files getFileForPlayer(String fileName)
	{
		return new Files(getPlayerFileDir(), fileName.replaceAll(".yml", ""));
	}
	
	public static Files getUUIDDatabaseFile()
	{
		return new Files(new File(Core.instance.getDataFolder() + "/plugindata/edit/with/caution"), "uuiddatabase");
	}
	
	public static Files getParticleEffectFile()
	{
		return new Files(new File(Core.instance.getDataFolder() + "/plugindata/edit/with/caution"), "particleeffectdata");
	}
	
	public static File getPlayerFileDir()
	{
		return new File(Core.instance.getDataFolder() + "/players");
	}
	
	public static Files getFileForPlayer(Player player)
	{
		if(USE_UUID)
		{
			return new Files(getPlayerFileDir(), player.getUniqueId().toString());
		}
		else
		{
			return new Files(getPlayerFileDir(), player.getName().toLowerCase());
		}
	}

	public static Files getFileForPlayer(UUID id)
	{
		return new Files(getPlayerFileDir(), id.toString());
	}

}
