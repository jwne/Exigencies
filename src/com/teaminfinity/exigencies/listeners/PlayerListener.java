package com.teaminfinity.exigencies.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.api.IpAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.Perm;
import com.teaminfinity.exigencies.enums.UUIDManagementType;
import com.teaminfinity.exigencies.objects.UUIDManager;
import com.teaminfinity.exigencies.objects.events.PlayerFirstJoinEvent;
import com.teaminfinity.exigencies.utils.Files;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerFirstJoin(PlayerFirstJoinEvent e)
	{
		Files file = FileAPI.getFileForPlayer(e.getPlayer());
		file.createFile();
		file.loadFile();
		file.set("first_join", System.currentTimeMillis());
		file.saveFile();
		if(ConfigVal.FIRST_JOIN_MESSAGE_ENABLED.getBooleanValue())
		{
			Bukkit.broadcastMessage(MessageAPI.getReformat(MessageVal.FIRST_LOGIN_MESSAGE, e.getPlayer()));
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		if(Perm.ALL.hasPermission(e.getPlayer()))
		{
			e.getPlayer().sendMessage(MessageAPI.getReformat(MessageVal.JOIN_NOTIFY_MESSAGE,
					Core.instance.getDescription().getVersion()));
		}
		Files file = FileAPI.getFileForPlayer(e.getPlayer());
		if(!(file.fileExists()))
		{
			Bukkit.getPluginManager().callEvent(new PlayerFirstJoinEvent(e));
		}

		/**
		 * The reason for this is because 1.7.5 is when the update to 
		 * switch from UUID's came out, therefore anything below this needs to be
		 * cancelled.
		 */
		if(Core.instance.getServer().getVersion().contains("1.7.2"))
		{
			IpAPI.logIp(e.getPlayer().getUniqueId(), IpAPI.getIp(e.getPlayer().getAddress()));
			new UUIDManager(e.getPlayer().getUniqueId(), e.getPlayer().getName(),
					UUIDManagementType.BOTH);
		}
	}
	
	@EventHandler
	public void onPrePlayerLogin(AsyncPlayerPreLoginEvent e)
	{
		 /*
		  * The reason for this is because 1.7.5 is when the update to 
		  * switch from UUID's came out, therefore anything below this 
		  * needs to be cancelled.
		  */
		if(!(Core.instance.getServer().getVersion().contains("1.7.2")))
		{
			IpAPI.logIp(e.getUniqueId(), IpAPI.getIp(e.getAddress()));
			new UUIDManager(e.getUniqueId(), e.getName(),
					UUIDManagementType.BOTH);
		}
	}
	
}