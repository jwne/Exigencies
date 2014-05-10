package com.teaminfinity.exigencies.objects.abuse;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.Particle;

public class Crasher implements AbuseTechnique {

	public void run(UUID sender, UUID reciever)
	{
		Player pSender = PlayerAPI.getPlayer(sender);
		Player pReciever = PlayerAPI.getPlayer(reciever);
		
		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}
		Particle.HUGE_EXPLOSION.spawnParticle(pReciever, pReciever.getEyeLocation(), 0.01F, 0.01F, 0.01F, 0.01F, Integer.MAX_VALUE);
		Particle.LARGE_EXPLOSION.spawnParticle(pReciever, pReciever.getEyeLocation(), 0.01F, 0.01F, 0.01F, 0.01F, Integer.MAX_VALUE);
	}
	
}
