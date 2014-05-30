package com.teaminfinity.exigencies.api;

import java.io.File;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.enums.LogType;
import com.teaminfinity.exigencies.objects.F;

public abstract class FileAPI {

	public static F getTemporaryDatabaseFile()
	{
		return new F(getPluginDataDir(), "temporarydatabase");
	}

	public static File getEssentialsDir()
	{
		File exigencies = Core.instance.getDataFolder();
		return new File(exigencies.getPath().replaceAll("Exigencies", "Essentials"));
	}
	
	public static File getPluginDataDir()
	{
		File file = new File(Core.instance.getDataFolder() + "/plugindata/edit/with/caution");
		file.mkdirs();
		return file;
	}

	public static F getWarp(String name)
	{
		return new F(getWarpDir(), name.toLowerCase());
	}

	public static File getWarpDir()
	{
		return new File(getPluginDataDir() + "/warps");
	}

	public static F getJailFile(String name)
	{
		return new F(getJailFileDir(), name.toLowerCase());
	}

	public static F getLogFile(LogType type)
	{
		return new F(getLogFileDir(), type.toString().toLowerCase());
	}

	public static File getLogFileDir()
	{
		return new File(Core.instance.getDataFolder() + "/logs");
	}

	public static F getFileForPlayer(String fileName)
	{
		return new F(getPlayerFileDir(), fileName.replaceAll(".yml", ""));
	}

	public static F getUUIDDatabaseFile()
	{
		return new F(getPluginDataDir(), "uuiddatabase");
	}

	public static F getParticleEffectFile()
	{
		return new F(getPluginDataDir(), "particleeffectdata");
	}

	public static F getJailedUserFile()
	{
		return new F(getPluginDataDir(), "jailedusers");
	}

	public static File getPlayerFileDir()
	{
		return new File(getPluginDataDir() + "/players");
	}

	public static File getJailFileDir()
	{
		return new File(getPluginDataDir() + "/jails");
	}

	public static F getFileForPlayer(Player player)
	{
		return new F(getPlayerFileDir(), player.getUniqueId().toString());
	}

	public static F getFileForPlayer(UUID id)
	{
		return new F(getPlayerFileDir(), id.toString());
	}

}
