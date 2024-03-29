package com.teaminfinity.exigencies.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.ConfigVal;

public class MotdListener implements Listener {

	@EventHandler
	public void onServerListPing(ServerListPingEvent e)
	{
		e.setMotd(ConfigVal.SERVER_MOTD.getStringValue());
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		for(String str : ConfigVal.PLAYER_MOTD.getListStringValue())
		{
			e.getPlayer().sendMessage(MessageAPI.addColour(str).replaceAll("%PLAYERTOTAL%", 
					Bukkit.getOnlinePlayers().length + ""));
		}
	}
	
}
