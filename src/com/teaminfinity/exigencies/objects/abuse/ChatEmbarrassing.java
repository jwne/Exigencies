package com.teaminfinity.exigencies.objects.abuse;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.utils.RandomManager;

public class ChatEmbarrassing implements AbuseTechnique {

	private transient String[] messages = new String[]
			{
			"ASL: 9, Orlando, Male. 3 inches and all hard.",
			"My penis often shrivels",
			"Despite my squeaky voice, I collectively have 3 pubes. GOML."
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
