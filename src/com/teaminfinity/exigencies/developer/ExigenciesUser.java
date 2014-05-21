package com.teaminfinity.exigencies.developer;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.api.JailAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.objects.F;

public class ExigenciesUser {

	/**
	 * 
	 * @param id needed id for the constructor
	 */
	public ExigenciesUser(UUID id)
	{
		this.id = id;
	}

	public F getFile()
	{
		return FileAPI.getFileForPlayer(id);
	}
	
	public boolean isJailed()
	{
		return JailAPI.isJailed(id);
	}
	
	public boolean isOp()
	{
		return Bukkit.getOfflinePlayer(id).isOp();
	}
	
	public boolean isBanned()
	{
		return Bukkit.getOfflinePlayer(id).isBanned();
	}
	
	public OfflinePlayer getOfflinePlayer()
	{
		return Bukkit.getOfflinePlayer(id);
	}
	
	public boolean isOnline()
	{
		return PlayerAPI.getPlayer(id) != null;
	}
	
	public String getLastKnownName()
	{
		F database = FileAPI.getUUIDDatabaseFile();
		return database.getString(id.toString());
	}
	
	public List<String> getUsedNames()
	{
		F playerFile = FileAPI.getFileForPlayer(id);
		playerFile.loadFile();
		return playerFile.getStringList("used_names");
	}

	public boolean isMuted()
	{
		F playerFile = FileAPI.getFileForPlayer(id);
		playerFile.loadFile();
		return playerFile.getBoolean("muted");
	}
	
	public Player getPlayer()
	{
		return PlayerAPI.getPlayer(id);
	}
	
	private final transient UUID id;

	public UUID getId() 
	{
		return id;
	}
	
}
