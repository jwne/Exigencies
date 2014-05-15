package com.teaminfinity.exigencies.siri;

import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SiriResponseDeterminer {

	public SiriResponseDeterminer(AsyncPlayerChatEvent event)
	{
		for(SiriResponse response : SiriResponse.values())
		{
			if(response.getCriteria().isMatch(event.getMessage()))
			{
				this.response = response;
			}
		}
		if(response == null)
		{
			response = SiriResponse.UNKNOWN;
		}
	}
	
	private transient SiriResponse response = null;

	public SiriResponse getResponse()
	{
		return response;
	}
	
}
