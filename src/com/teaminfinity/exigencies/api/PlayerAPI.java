package com.teaminfinity.exigencies.api;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.enums.Perm;
import com.teaminfinity.exigencies.objects.F;

public class PlayerAPI {

	private static transient HashMap<CommandSender, CommandSender> latestReply = new HashMap<CommandSender, CommandSender>();
	
	public static CommandSender getLatestReply(CommandSender sender)
	{
		if(!(latestReply.containsKey(sender)))
		{
			return null;
		}
		return latestReply.get(sender);
	}
	
	public static void addLatestReply(CommandSender sender, CommandSender dat)
	{
		latestReply.put(sender, dat);
	}
	
	public static void messageStaff(String message)
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(Perm.ALL.hasPermission(player))
			{
				player.sendMessage(message);
			}
		}
	}
	
	public static UUID getUUID(OfflinePlayer player)
	{
		return player.getUniqueId();
	}
	
	public static UUID getByName(String name)
	{
		F file = FileAPI.getUUIDDatabaseFile();
		file.loadFile();
		String data = file.getString(name.toLowerCase());
		if(data == null)
		{
			return null;
		}
		return UUID.fromString(data);
	}
	
	public static String getName(Player player)
	{
		if(player.getDisplayName() != null)
		{
			return MessageAPI.addColour(player.getDisplayName());
		}
		else
		{
			return player.getName();
		}
	}
	
	public static Player getPlayer(UUID id)
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(player.getUniqueId().equals(id))
			{
				return player;
			}
		}
		return null;
	}
	
	public static Player getPlayer(String string)
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(player.getName().toLowerCase().contains(string.toLowerCase()))
			{
				return player;
			}
		}
		return null;
	}
	
	public static Player getPlayerExact(String string)
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(player.getName().equalsIgnoreCase(string))
			{
				return player;
			}
		}
		return null;
	}
	
	public static String getName(CommandSender sender)
	{
		if(sender instanceof Player)
		{
			return getName((Player)sender);
		}
		else
		{
			return sender.getName();
		}
	}
	
}
