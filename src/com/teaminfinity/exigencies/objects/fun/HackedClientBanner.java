package com.teaminfinity.exigencies.objects.fun;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.Particle;

public class HackedClientBanner implements AbuseTechnique {
	
	public void run(UUID sender, UUID reciever)
	{
		final Player pSender = PlayerAPI.getPlayer(sender);
		final Player pReciever = PlayerAPI.getPlayer(reciever);
		
		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}

		Bukkit.dispatchCommand(pSender, "ban " + pReciever.getName() + " Hacked Client");
		Particle.SMOKE.spawnParticle(pReciever.getWorld(), pReciever.getEyeLocation(),
				0.4F, 0.4F, 0.4F, 0.4F, 500);
	}
	
}
