package com.teaminfinity.exigencies.siri;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.teaminfinity.exigencies.enums.MessageVal;

public class SiriListener implements Listener {

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
	{
		if(e.isCancelled())
		{
			return;
		}
		if(!(e.getMessage().toLowerCase().contains("siri")))
		{
			return;
		}
		e.setCancelled(true);
		SiriResponse response = new SiriResponseDeterminer(e).getResponse();
		e.getPlayer().sendMessage(MessageVal.COLOR_PRIMARY.getValue() + 
				response.getType().getMessage());
	}
	
}
