package com.teaminfinity.exigencies.objects.command;

import java.util.Date;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.api.PlayerAPI;

public class TempBanCreator {

	public TempBanCreator(String target, String reason, CommandSender sender, Date date)
	{
		Bukkit.getBanList(Type.NAME).addBan(target, reason, date, PlayerAPI.getName(sender));
	}
	
}
