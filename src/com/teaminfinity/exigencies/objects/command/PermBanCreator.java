package com.teaminfinity.exigencies.objects.command;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.api.PlayerAPI;

public class PermBanCreator {

	public PermBanCreator(String target, String reason, CommandSender sender)
	{
		Bukkit.getBanList(Type.NAME).addBan(target, reason, null, PlayerAPI.getName(sender));
	}
	
}
