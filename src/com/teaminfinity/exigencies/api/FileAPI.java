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
		return new F(new File(Core.instance.getDataFolder() + "/plugindata/edit/with/caution"), 
				"temporarydatabase");
	}

	public static File getEssentialsDir()
	{
		File exigencies = Core.instance.getDataFolder();
		return new File(exigencies.getPath().replaceAll("Exigencies", "Essentials"));
	}

	public static F getWarp(String name)
	{
		return new F(getWarpDir(), name.toLowerCase());
	}

	public static File getWarpDir()
	{
		return new File(Core.instance.getDataFolder() + "/warps");
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
		return new F(new File(Core.instance.getDataFolder() + "/plugindata/edit/with/caution"), "uuiddatabase");
	}

	public static F getParticleEffectFile()
	{
		return new F(new File(Core.instance.getDataFolder() + "/plugindata/edit/with/caution"), "particleeffectdata");
	}

	public static F getJailedUserFile()
	{
		return new F(Core.instance.getDataFolder(), "jailedusers");
	}

	public static File getPlayerFileDir()
	{
		return new File(Core.instance.getDataFolder() + "/players");
	}

	public static File getJailFileDir()
	{
		return new File(Core.instance.getDataFolder() + "/jails");
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
