package com.teaminfinity.exigencies.gui;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.teaminfinity.exigencies.api.ParticleEffectAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.Particle;
import com.teaminfinity.exigencies.objects.ItemStackCreator;

public class ParticleEffectGUI implements ExigenciesGUI {

	private transient ArrayList<UUID> viewers = new ArrayList<>();
	private transient Inventory invent;

	/**
	 * Definitely don't want to do this,
	 * as there would be no *simple* way
	 * of determining whos data to show.
	 */
	@Override
	public void show(Player player) 
	{
		if(invent == null)
		{
			generate();
		}
		player.openInventory(invent);
		viewers.add(player.getUniqueId());
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e)
	{
		viewers.remove(e.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if(viewers.contains(e.getWhoClicked().getUniqueId()))
		{
			e.setCancelled(true);
			ItemStack item = e.getCurrentItem();
			if(item == null)
			{
				return;
			}
			if(!(item.hasItemMeta()))
			{
				return;
			}
			if(!(item.getItemMeta().hasDisplayName()))
			{
				return;
			}
			String displayName = ChatColor.stripColor(item.getItemMeta().getDisplayName().toUpperCase());
			Particle particle = null;

			for(Particle p : Particle.values())
			{
				if(p.toString().contains(displayName))
				{
					particle = p;
					break;
				}
			}

			if(particle == null)
			{
				return;
			}
			Player player = (Player) e.getWhoClicked();
			ParticleEffectAPI.setForPlayer(player, particle);
			player.sendMessage(MessageVal.COMMAND_PARTICLEEFFECT_SUCCESS.getValue()
					.replaceAll("%PARTICLE%", particle.toString().toLowerCase())
					.replaceAll("_", " "));
		}
	}

	@Override
	public boolean isViewing(Player player)
	{
		return false;
	}

	@Override
	public void remove(Player player)
	{
	}

	private void generate()
	{
		invent = Bukkit.createInventory(null, 36, MessageVal.COMMAND_PARTICLEEFFECT_GUI_TITLE.getValue());
		refresh();
	}

	private void refresh()
	{
		for(Particle particle : Particle.values())
		{
			invent.addItem(new ItemStackCreator(MessageVal.COLOR_TERTIARY.getValue() + 
					particle.toString(), new String[]
							{
							}
			, Material.PAPER, 1).getItem());
		}
	}

}
