package com.teaminfinity.exigencies.objects.fun;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.teaminfinity.exigencies.enums.Particle;
import com.teaminfinity.exigencies.objects.BetterRunnable;
import com.teaminfinity.exigencies.utils.RandomManager;
import com.teaminfinity.exigencies.utils.SchedulingUtility;

public class SheepCannon implements BetterRunnable {

	private transient int life = 0;
	private transient int taskId;
	private transient Sheep sheep;
	
	public SheepCannon(Location eyeLocation)
	{
		sheep = (Sheep) eyeLocation.getWorld().spawnEntity(eyeLocation, EntityType.SHEEP);
		sheep.setVelocity(eyeLocation.getDirection().multiply(6.47D));
		sheep.setColor(DyeColor.values()[RandomManager.nextInt(DyeColor.values().length)]);
		sheep.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 50));
		sheep.setCanPickupItems(false);
		sheep.setBreed(false);
		begin();
	}
	
	@Override
	public void run() 
	{
		life++;
		if(life >= 50000)
		{
			end();
			return;
		}

		if(sheep.isDead())
		{
			end();
			return;
		}
		
		if(sheep.isOnGround())
		{
			end();
			return;
		}
		
		Location hit = calculateHit();
		if(hit != null)
		{
			end();
		}
		else
		{
			Particle.WITCH_MAGIC.spawnParticle(sheep.getWorld(),
					sheep.getLocation(), 0.01F, 0.01F, 0.01F, 0.01F, 1);
			Particle.ENCHANTMENT_TABLE.spawnParticle(sheep.getWorld(),
					sheep.getLocation(), 0.01F, 0.01F, 0.01F, 0.01F, 1);
			Particle.WITCH_MAGIC.spawnParticle(sheep.getWorld(),
					sheep.getLocation(), 0.01F, 0.01F, 0.01F, 0.01F, 1);
			Particle.INSTANT_SPELL.spawnParticle(sheep.getWorld(),
					sheep.getLocation(), 0.01F, 0.01F, 0.01F, 0.01F, 1);
			Particle.HEART.spawnParticle(sheep.getWorld(),
					sheep.getLocation(), 0.01F, 0.01F, 0.01F, 0.01F, 1);
		}
	}

	@Override
	public void begin() 
	{
		taskId = SchedulingUtility.beginRepeating(this, 1);
	}

	@Override
	public void end() 
	{
		Particle.EXPLODE.spawnParticle(sheep.getWorld(),
				sheep.getLocation(), 0.01F, 0.01F, 0.01F, 0.01F, 5);
		Particle.HUGE_EXPLOSION.spawnParticle(sheep.getWorld(),
				sheep.getLocation(), 0.01F, 0.01F, 0.01F, 0.01F, 2);
		Particle.LARGE_SMOKE.spawnParticle(sheep.getWorld(),
				sheep.getLocation(), 0.4F, 0.4F, 0.4F, 0.4F, 25);
		Particle.SMOKE.spawnParticle(sheep.getWorld(),
				sheep.getLocation(), 0.4F, 0.4F, 0.4F, 0.4F, 25);
		Particle.CLOUD.spawnParticle(sheep.getWorld(),
				sheep.getLocation(), 0.4F, 0.4F, 0.4F, 0.4F, 25);
		Particle.LAVA.spawnParticle(sheep.getWorld(),
				sheep.getLocation(), 0.4F, 0.4F, 0.4F, 0.4F, 25);
		sheep.getWorld().createExplosion(sheep.getLocation(), 3F);
		sheep.remove();
		SchedulingUtility.stopRepeating(taskId);
	}
	
	private Location calculateHit()
	{
		/**
		 * Down
		 */
		double downDistance = sheep.getLocation().distance
				(sheep.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
		boolean downValid = 
				(sheep.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR);
		if(downValid)
		{
			if(downDistance < 1)
			{
				return (sheep.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
			}
		}

		/**
		 * Up
		 */
		double upDistance = sheep.getLocation().distance
				(sheep.getLocation().getBlock().getRelative(BlockFace.UP).getLocation());
		boolean upValid = 
				(sheep.getLocation().getBlock().getRelative(BlockFace.UP).getType() != Material.AIR);
		if(upValid)
		{
			if(upDistance < 1)
			{
				return (sheep.getLocation().getBlock().getRelative(BlockFace.UP).getLocation());
			}
		}


		/**
		 * East
		 */
		double eastDistance = sheep.getLocation().distance
				(sheep.getLocation().getBlock().getRelative(BlockFace.EAST).getLocation());
		boolean eastValid = 
				(sheep.getLocation().getBlock().getRelative(BlockFace.EAST).getType() != Material.AIR);
		if(eastValid)
		{
			if(eastDistance < 1)
			{
				return (sheep.getLocation().getBlock().getRelative(BlockFace.EAST).getLocation());
			}
		}

		/**
		 * West
		 */
		double westDistance = sheep.getLocation().distance
				(sheep.getLocation().getBlock().getRelative(BlockFace.WEST).getLocation());
		boolean westValid = 
				(sheep.getLocation().getBlock().getRelative(BlockFace.WEST).getType() != Material.AIR);
		if(westValid)
		{
			if(westDistance < 1)
			{
				return (sheep.getLocation().getBlock().getRelative(BlockFace.WEST).getLocation());
			}
		}

		/**
		 * South
		 */
		double southDistance = sheep.getLocation().distance
				(sheep.getLocation().getBlock().getRelative(BlockFace.SOUTH).getLocation());
		boolean southValid = 
				(sheep.getLocation().getBlock().getRelative(BlockFace.SOUTH).getType() != Material.AIR);
		if(southValid)
		{
			if(southDistance < 1)
			{
				return (sheep.getLocation().getBlock().getRelative(BlockFace.SOUTH).getLocation());
			}
		}

		/**
		 * North
		 */
		double northDistance = sheep.getLocation().distance
				(sheep.getLocation().getBlock().getRelative(BlockFace.NORTH).getLocation());
		boolean northValid = 
				(sheep.getLocation().getBlock().getRelative(BlockFace.NORTH).getType() != Material.AIR);
		if(northValid)
		{
			if(northDistance < 1)
			{
				return (sheep.getLocation().getBlock().getRelative(BlockFace.NORTH).getLocation());
			}
		}


		return null;
	}

}
