package com.teaminfinity.exigencies.objects.fun;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.utils.RandomManager;

public class ChatJustinBieber implements AbuseTechnique {

	private transient String[] messages = new String[]
			{
			"I LOVE JUSTIN BIEBER",
			"Never say never <3",
			"Cut for bieber <3",
			"Drown for bieber <3",
			"Baby, baby, baby oooooooooooooooooooooo",
			"I <3 JB",
			"JB is sinless like Jesus"
			}
	;
	
	public void run(UUID sender, UUID reciever)
	{
		Player pSender = PlayerAPI.getPlayer(sender);
		Player pReciever = PlayerAPI.getPlayer(reciever);
		
		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}
		
		pReciever.chat(messages[RandomManager.nextInt(messages.length)]);
	}
	
}
