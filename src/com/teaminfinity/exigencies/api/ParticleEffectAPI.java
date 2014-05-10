package com.teaminfinity.exigencies.api;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.enums.Particle;
import com.teaminfinity.exigencies.utils.Files;

public abstract class ParticleEffectAPI {

	private transient static Files particleFile = null;
	
	public static void init()
	{
		particleFile = FileAPI.getParticleEffectFile();
		if(!(particleFile.fileExists()))
		{
			particleFile.createFile();
		}
		particleFile.loadFile();
	}
	
	public static void setForPlayer(Player player, Particle effect)
	{
		if(effect == null)
		{
			particleFile.set(player.getUniqueId().toString(), null);
			return;
		}
		particleFile.set(player.getUniqueId().toString(), effect.toString());
		particleFile.saveFile();
	}

	public static Particle getForPlayer(Player player)
	{
		String target = particleFile.getString(player.getUniqueId().toString());
		if(target == null)
		{
			return null;
		}
		return Particle.valueOf(target);
	}
	
}
