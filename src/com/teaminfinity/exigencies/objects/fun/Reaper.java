package com.teaminfinity.exigencies.objects.fun;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.objects.ExigenciesRunnable;
import com.teaminfinity.exigencies.utils.RandomManager;
import com.teaminfinity.exigencies.utils.SchedulingUtility;

public class Reaper implements ExigenciesRunnable {

	private transient Skeleton reaper;
	private transient int taskId;
	private transient ReaperStage stage = ReaperStage.SPAWNING;
	
	public Reaper(Location loc)
	{
		reaper = (Skeleton) loc.getWorld().spawnEntity(loc.getWorld()
				.getHighestBlockAt(loc).getLocation().add(0, 64, 0),
				EntityType.SKELETON);
		begin();
	}

	@Override
	public void run() 
	{
		if(reaper.isDead())
		{
			end();
			return;
		}
		if(ReaperStage.SPAWNING.equals(stage))
		{
			strikeLightning();
			reaper.setFireTicks(0);
			reaper.setHealth(20D);
		}
		if(reaper.isOnGround())
		{
			this.stage = ReaperStage.ACTIVE;
		}
		spawnHead();
		
		if(RandomManager.nextInt(1000) == 999)
		{
			reaper.getWorld().playSound(reaper.getEyeLocation(), Sound.ENDERDRAGON_GROWL, 10F, 1F);
		}
	}
	
	private void spawnHead()
	{
		final WitherSkull skull = reaper.launchProjectile(WitherSkull.class);
		skull.setVelocity(reaper.getEyeLocation().getDirection());
		skull.setYield(-5F);
		skull.setCharged(false);
		skull.setBounce(true);
		skull.setIsIncendiary(false);
		skull.setFireTicks(100000);
	}
	

	@Override
	public void begin()
	{
		reaper.setCustomName(ChatColor.WHITE + "Reaper");
		reaper.setSkeletonType(SkeletonType.WITHER);
		reaper.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 50));

		ItemStack helm = new ItemStack(Material.LEATHER_HELMET, 1);
		ItemStack body = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		ItemStack legs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta lam = (LeatherArmorMeta)helm.getItemMeta();
		lam.setColor(Color.BLACK);
		helm.setItemMeta(lam);
		body.setItemMeta(lam);
		legs.setItemMeta(lam);
		boots.setItemMeta(lam);
		reaper.getEquipment().setArmorContents(new ItemStack[]
				{
				helm,
				body,
				legs,
				boots
				}
		);
		reaper.getEquipment().setItemInHand(new ItemStack(Material.IRON_HOE, 1));
		taskId = SchedulingUtility.beginRepeating(this, 2);
	}

	@Override
	public void end() 
	{
		SchedulingUtility.stopRepeating(taskId);
		reaper.remove();
	}
	
	
	public enum ReaperStage {
		
		SPAWNING,
		ACTIVE;
		
	}
	
	private void strikeLightning()
	{
		List<Location> locations = getLocations(reaper.getLocation());
		int i = 0;
		for (final Location loc : locations) 
		{
			Bukkit.getScheduler().scheduleSyncDelayedTask(Core.instance, new Runnable()
			{
				public void run() 
				{
					reaper.getWorld().strikeLightning(loc);
				}
			}
			, i * 2);
			i++;
		}
	}
	
	private List<Location> getLocations(Location loc)
	{
		ArrayList<Location> locations = new ArrayList<Location>();
		for (int i = 5; i > 8; i -= 2) 
		{
			locations.add(new Location(loc.getWorld(), loc.getX(), loc.getY() + i, loc.getZ()));
		}
		locations.add(loc);
		return locations;
	}
	
}
