package com.teaminfinity.exigencies.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.teaminfinity.exigencies.api.JailAPI;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.utils.SchedulingUtility;

public class JailListener implements Listener {

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
	{
		if(!(ConfigVal.JAIL_BLOCK_CHAT.getBooleanValue()))
		{
			return;
		}
		if(JailAPI.isJailed(e.getPlayer().getUniqueId()))
		{
			e.getPlayer().sendMessage(MessageVal.COMMAND_JAIL_STOP.getValue());
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e)
	{
		if(!(ConfigVal.JAIL_BLOCK_COMMANDS.getBooleanValue()))
		{
			return;
		}
		if(JailAPI.isJailed(e.getPlayer().getUniqueId()))
		{
			e.getPlayer().sendMessage(MessageVal.COMMAND_JAIL_STOP.getValue());
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e)
	{
		if(JailAPI.isJailed(e.getPlayer().getUniqueId()))
		{
			SchedulingUtility.run(new Runnable()
			{
				@Override
				public void run()
				{
					e.getPlayer().teleport(JailAPI.getJail(e.getPlayer().getUniqueId()).getLoc());
				}
			}
			, 3);
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent e)
	{
		if(JailAPI.isJailed(e.getPlayer().getUniqueId()))
		{
			SchedulingUtility.run(new Runnable()
			{
				@Override
				public void run()
				{
					e.getPlayer().teleport(JailAPI.getJail(e.getPlayer().getUniqueId()).getLoc());
				}
			}
			, 3);
		}
	}
	
}
