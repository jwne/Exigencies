package com.teaminfinity.exigencies.siri;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
		e.getPlayer().sendMessage(response.getType().toString());
	}
	
}
