package com.teaminfinity.exigencies.gui;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.Updater;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.ItemStackCreator;

public class ExGUI implements ExigenciesGUI {

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
		viewers.add(player.getUniqueId());
		if(invent == null)
		{
			generate();
		}
		refresh();
		player.openInventory(invent);
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
			if(e.getCurrentItem() == null)
			{
				return;
			}
			ItemStack item = e.getCurrentItem();
			
			if(!(item.hasItemMeta()))
			{
				return;
			}
			if(!(item.getItemMeta().hasDisplayName()))
			{
				return;
			}
			
			Player player = (Player) e.getWhoClicked();
			
			String displayName = item.getItemMeta().getDisplayName().toLowerCase();
			if(displayName.contains("refresh"))
			{
				refresh();
			}
			else if(displayName.contains("update available"))
			{
				if(Core.exigencies.canUpdate())
				{
					if(Core.exigencies.getUpdateResult().equals(Updater.UpdateResult.SUCCESS))
					{
						player.sendMessage(MessageVal.COMMAND_EX_UPDATE_DONE.getValue());
						return;
					}
					Core.exigencies.setUpdater(new Updater(Core.instance, "exigencies",
							Core.instance.getFile(), Updater.UpdateType.DEFAULT, false));
					player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_EX_UPDATE_STARTED, 
							Core.exigencies.getLatestVersion()));
				}
			}
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
		invent = Bukkit.createInventory(null, 9, MessageVal.COMMAND_LAG_GUI_TITLE.getValue());
		refresh();
	}
	
	private void refresh()
	{
		for(int i = 0; i < invent.getSize(); i++)
		{
			invent.setItem(i, null);
		}
		String sec = MessageVal.COLOR_SECONDARY.getValue();
		String pri = MessageVal.COLOR_PRIMARY.getValue();
		String ter = MessageVal.COLOR_TERTIARY.getValue();
		
		invent.addItem(new ItemStackCreator(pri + "Update Available: " +  sec
				+ Core.exigencies.canUpdate(), new String[]
					{
					ter + "Latest Version: " + Core.exigencies.getLatestVersion(),
					(Core.exigencies.canUpdate() ? ter + "Click to update!" : ""),
					}
				, ((Core.exigencies.canUpdate() ? Material.EYE_OF_ENDER : Material.ENDER_PEARL))
				, 1).getItem());
		
		invent.addItem(new ItemStackCreator(pri + "NMS Code: " +  sec
				+ Core.exigencies.isNmsCompatible(), new String[]
					{
					}
				, ((Core.exigencies.isNmsCompatible() ? Material.EYE_OF_ENDER : Material.ENDER_PEARL))
				, 1).getItem());
	}

}
