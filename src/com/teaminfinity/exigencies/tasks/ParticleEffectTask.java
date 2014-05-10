package com.teaminfinity.exigencies.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.ParticleEffectAPI;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.Particle;

public class ParticleEffectTask implements Runnable {

	private transient static boolean PARTICLE_EFFECT_USEEYELOCATION = false;
	private transient static int PARTICLE_EFFECT_AMOUNT = 50;
	private transient static float PARTICLE_EFFECT_OFFSETX = 0.001F;
	private transient static float PARTICLE_EFFECT_OFFSETY = 0.001F;
	private transient static float PARTICLE_EFFECT_OFFSETZ = 0.001F;
	private transient static float PARTICLE_EFFECT_SPEED = 0.001F;
	
	public static void init()
	{
		PARTICLE_EFFECT_AMOUNT = ConfigVal.PARTICLE_EFFECT_AMOUNT.getIntegerValue();
		PARTICLE_EFFECT_USEEYELOCATION = ConfigVal.PARTICLE_EFFECT_USEEYELOCATION.getBooleanValue();
		PARTICLE_EFFECT_OFFSETX = ConfigVal.PARTICLE_EFFECT_OFFSETX.getFloatValue();
		PARTICLE_EFFECT_OFFSETY = ConfigVal.PARTICLE_EFFECT_OFFSETY.getFloatValue();
		PARTICLE_EFFECT_OFFSETZ = ConfigVal.PARTICLE_EFFECT_OFFSETZ.getFloatValue();
		PARTICLE_EFFECT_SPEED = ConfigVal.PARTICLE_EFFECT_SPEED.getFloatValue();
	}
	
	@Override
	public void run() 
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			Particle effect = ParticleEffectAPI.getForPlayer(player);
			if(effect != null)
			{
				if(effect == Particle.NONE)
				{
					ParticleEffectAPI.setForPlayer(player, null);
					continue;
				}
				effect.spawnParticle(player.getWorld(), ((PARTICLE_EFFECT_USEEYELOCATION)
						? player.getEyeLocation() : player.getLocation()), PARTICLE_EFFECT_OFFSETX,
						PARTICLE_EFFECT_OFFSETY, PARTICLE_EFFECT_OFFSETZ, PARTICLE_EFFECT_SPEED, 
						PARTICLE_EFFECT_AMOUNT);
			}
		}
	}

}
