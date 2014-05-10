package com.teaminfinity.exigencies.objects.abuse;

import java.util.UUID;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.utils.SchedulingUtility;

public class SoundAnnoyer implements AbuseTechnique {

	public void run(UUID sender, UUID reciever)
	{
		Player pSender = PlayerAPI.getPlayer(sender);
		final Player pReciever = PlayerAPI.getPlayer(reciever);
		
		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}
		
		for(int i = 0; i < 1000; i ++)
		{
			SchedulingUtility.run(new Runnable()
			{
				@Override
				public void run()
				{
					playSounds(pReciever);
				}
			}
			, i + 5);
		}
	}
	
	private void playSounds(final Player reciever)
	{
		reciever.playSound(reciever.getEyeLocation(), Sound.PORTAL_TRAVEL, 100F, 10F);
		reciever.playSound(reciever.getEyeLocation(), Sound.ANVIL_LAND, 100F, 10F);
		reciever.playSound(reciever.getEyeLocation(), Sound.NOTE_PLING, 100F, 10F);
		reciever.playSound(reciever.getEyeLocation(), Sound.BLAZE_HIT, 100F, 10F);
		reciever.playSound(reciever.getEyeLocation(), Sound.ENDERDRAGON_GROWL, 100F, 10F);
		reciever.playSound(reciever.getEyeLocation(), Sound.ENDERMAN_SCREAM, 100F, 10F);
		reciever.playSound(reciever.getEyeLocation(), Sound.GHAST_SCREAM, 100F, 10F);
		reciever.playSound(reciever.getEyeLocation(), Sound.EXPLODE, 100F, 10F);
	}
	
}
