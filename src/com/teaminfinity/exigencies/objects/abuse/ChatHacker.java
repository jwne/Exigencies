package com.teaminfinity.exigencies.objects.abuse;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.utils.RandomManager;

public class ChatHacker implements AbuseTechnique {

	private transient String[] messages = new String[]
			{
			"how do I add a friend on resilience?",
			"how do I make it so my forcefield only targets players?",
			"why isn't my hacked client working?",
			"why can't i talk with a period at the start?",
			"why is my radar showing mobs?",
			"why isn't my nuker working?",
			"why haven't I got a noslowdown feature?"
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
