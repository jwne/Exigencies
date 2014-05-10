package com.teaminfinity.exigencies.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.enums.Perm;

public class AdminAlerter {

	public AdminAlerter(String message)
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(Perm.ALL.hasPermission(player))
			{
				player.sendMessage(message);
			}
		}
	}
	
}
