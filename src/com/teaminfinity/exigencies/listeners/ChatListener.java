package com.teaminfinity.exigencies.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.api.TemporaryAPI;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.TempValueType;
import com.teaminfinity.exigencies.objects.command.TempResult;
import com.teaminfinity.exigencies.utils.Files;

public class ChatListener implements Listener {

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
	{
		TempResult result = TemporaryAPI.get(e.getPlayer(), TempValueType.MUTE);
		if(result.isActive())
		{
			e.getPlayer().sendMessage(MessageAPI.getReformat(MessageVal
					.COMMAND_TEMPMUTE_CANCEL_CHAT, result.getStrTimeLeft(true)));
			e.setCancelled(true);
		}
		else
		{
			Files playerFile = FileAPI.getFileForPlayer(e.getPlayer());
			playerFile.loadFile();
			if(playerFile.getBoolean("muted"))
			{
				e.setCancelled(true);
				e.getPlayer().sendMessage(MessageVal.COMMAND_MUTE_MUTED_STOPCHAT.getValue());
			}
		}
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onAsyncPlayerChat2(AsyncPlayerChatEvent e)
	{
		if(ConfigVal.EXIGENCIES_CHAT_ENABLED.getBooleanValue())
		{
			e.setFormat(ConfigVal.EXIGENCIES_CHAT_FORMAT.getStringValue()
					.replaceAll("%PLAYER%", PlayerAPI.getName(e.getPlayer()))
					.replaceAll("%MESSAGE%", e.getMessage()));
		}
	}
	
}
