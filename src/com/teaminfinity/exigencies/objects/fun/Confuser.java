package com.teaminfinity.exigencies.objects.fun;

import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;

public class Confuser implements AbuseTechnique {
	
	public void run(UUID sender, UUID reciever)
	{
		Player pSender = PlayerAPI.getPlayer(sender);
		Player pReciever = PlayerAPI.getPlayer(reciever);
		
		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}
		
		Entity entity = (Entity) pReciever;
		entity.remove();
	}
	
}
