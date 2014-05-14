package com.teaminfinity.exigencies.objects.fun;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.Particle;
import com.teaminfinity.exigencies.utils.SchedulingUtility;

public class TortureWiper implements AbuseTechnique {

	public void run(UUID sender, UUID reciever)
	{
		Player pSender = PlayerAPI.getPlayer(sender);
		final Player pReciever = PlayerAPI.getPlayer(reciever);
		
		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}
		
		List<ItemStack> items = new ArrayList<ItemStack>();

		for(ItemStack item : pReciever.getEnderChest().getContents())
		{
			items.add(item);
		}
		for(ItemStack item : pReciever.getInventory().getContents())
		{
			items.add(item);
		}
		
		final List<ItemStack> copy = items;
		pReciever.getInventory().clear();
		pReciever.getEnderChest().clear();
		
		int current = 0;
		for(final ItemStack is : copy)
		{
			if(is == null)
			{
				continue;
			}
			if(is.getType() == Material.AIR)
			{
				continue;
			}

			pReciever.getWorld().playSound(pReciever.getLocation(), Sound.CHEST_OPEN, 0.5F, 1F);
			SchedulingUtility.run(new Runnable()
			{
				@Override
				public void run()
				{
					pReciever.getWorld().playSound(pReciever.getLocation(), Sound.CHEST_CLOSE, 0.5F, 1F);
				}
			}
			, 15);
			
			current += 20;
			SchedulingUtility.run(new Runnable()
			{
				@Override
				public void run()
				{
					final Item item = (Item) pReciever.getWorld().dropItem(pReciever.getEyeLocation(), is);
					item.setFireTicks(5000);
					item.setVelocity(pReciever.getEyeLocation().getDirection());
					item.setPickupDelay(5000);
					item.getWorld().playSound(item.getLocation(), Sound.CLICK, 0.5F, 0.5F);
					SchedulingUtility.run(new Runnable()
					{
						@Override
						public void run()
						{
							item.getWorld().playSound(item.getLocation(), Sound.FIZZ, 0.5F, 0.5F);
							Particle.FLAME.spawnParticle(item.getWorld(), item.getLocation(), 0.01F, 0.01F,
									0.01F, 0.01F, 2);
							item.remove();
						}
					}
					, 20);
				}
			}
			, current);
		}
	}

}
