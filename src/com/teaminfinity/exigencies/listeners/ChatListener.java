package com.teaminfinity.exigencies.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.TemporaryAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.TempValueType;
import com.teaminfinity.exigencies.objects.command.TempResult;

public class ChatListener implements Listener {

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
	{
		TempResult result = TemporaryAPI.get(e.getPlayer(), TempValueType.MUTE);
		if(result.isActive())
		{
			e.getPlayer().sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_TEMPMUTE_CANCEL_CHAT, 
					result.getStrTimeLeft(true)));
			e.setCancelled(true);
		}
	}
	
}
