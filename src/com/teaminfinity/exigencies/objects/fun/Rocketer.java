package com.teaminfinity.exigencies.objects.fun;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.Particle;
import com.teaminfinity.exigencies.utils.SchedulingUtility;

public class Rocketer implements AbuseTechnique {

	public void run(UUID sender, UUID reciever)
	{
		Player pSender = PlayerAPI.getPlayer(sender);
		final Player pReciever = PlayerAPI.getPlayer(reciever);

		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}

		for(int i = 0; i < 5; i ++)
		{
			SchedulingUtility.run(new Runnable()
			{

				@Override
				public void run()
				{
					Particle.CLOUD.spawnParticle(pReciever.getWorld(), pReciever.getLocation(),
							0.4F, 0.4F, 0.4F, 0.05F, 75);
				}
			}
			,
			i * 5);
		}
		SchedulingUtility.run(new Runnable()
		{

			@Override
			public void run()
			{
				takeOff(pReciever);
			}
		}
		, 26);
	}

	private void takeOff(final Player pReciever)
	{
		pReciever.setVelocity(pReciever.getVelocity().setY(10D));
		for(int i = 0; i < 40; i ++)
		{
			SchedulingUtility.run(new Runnable()
			{
				@Override
				public void run()
				{
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
				}
			}
			, i);
		}
	}

}
