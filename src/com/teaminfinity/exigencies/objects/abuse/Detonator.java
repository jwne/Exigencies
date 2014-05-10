package com.teaminfinity.exigencies.objects.abuse;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.Particle;

public class Detonator implements AbuseTechnique {

	public void run(UUID sender, UUID reciever)
	{
		Player pSender = PlayerAPI.getPlayer(sender);
		Player pReciever = PlayerAPI.getPlayer(reciever);
		
		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}
		
		Particle.EXPLODE.spawnParticle(pReciever.getWorld(), pReciever.getLocation(),
				0.01F, 0.01F, 0.01F, 0.01F, 15);
		Particle.FIREWORK_SPARK.spawnParticle(pReciever.getWorld(), pReciever.getLocation(),
				0.4F, 0.4F, 0.4F, 0.4F, 15);
		Particle.FLAME.spawnParticle(pReciever.getWorld(), pReciever.getLocation(),
				0.4F, 0.4F, 0.4F, 0.4F, 15);
		Particle.SMOKE.spawnParticle(pReciever.getWorld(), pReciever.getLocation(),
				0.4F, 0.4F, 0.4F, 0.4F, 15);
		Particle.LAVA.spawnParticle(pReciever.getWorld(), pReciever.getLocation(),
				0.4F, 0.4F, 0.4F, 0.4F, 15);
		pReciever.getWorld().createExplosion(pReciever.getLocation(), 5F);
	}
	
}
