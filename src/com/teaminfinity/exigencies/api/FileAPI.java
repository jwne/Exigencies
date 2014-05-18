package com.teaminfinity.exigencies.api;

import java.io.File;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.enums.LogType;
import com.teaminfinity.exigencies.utils.Files;

public abstract class FileAPI {

	public static Files getTemporaryDatabaseFile()
	{
		return new Files(new File(Core.instance.getDataFolder() + "/plugindata/edit/with/caution"), 
				"temporarydatabase");
	}

	public static File getEssentialsDir()
	{
		File exigencies = Core.instance.getDataFolder();
		return new File(exigencies.getPath().replaceAll("Exigencies", "Essentials"));
	}

	public static Files getWarp(String name)
	{
		return new Files(getWarpDir(), name.toLowerCase());
	}

	public static File getWarpDir()
	{
		return new File(Core.instance.getDataFolder() + "/warps");
	}

	public static Files getJailFile(String name)
	{
		return new Files(getJailFileDir(), name.toLowerCase());
	}

	public static Files getLogFile(LogType type)
	{
		return new Files(getLogFileDir(), type.toString().toLowerCase());
	}

	public static File getLogFileDir()
	{
		return new File(Core.instance.getDataFolder() + "/logs");
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

	public static Files getJailedUserFile()
	{
		return new Files(Core.instance.getDataFolder(), "jailedusers");
	}

	public static File getPlayerFileDir()
	{
		return new File(Core.instance.getDataFolder() + "/players");
	}

	public static File getJailFileDir()
	{
		return new File(Core.instance.getDataFolder() + "/jails");
	}

	public static Files getFileForPlayer(Player player)
	{
		return new Files(getPlayerFileDir(), player.getUniqueId().toString());
	}

	public static Files getFileForPlayer(UUID id)
	{
		return new Files(getPlayerFileDir(), id.toString());
	}

}
