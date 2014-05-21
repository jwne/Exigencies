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

import com.teaminfinity.exigencies.api.LaggAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.ItemStackCreator;
import com.teaminfinity.exigencies.tasks.GeneralTask;

public class LagGUI implements ExigenciesGUI {

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
			
			String displayName = item.getItemMeta().getDisplayName().toLowerCase();
			if(displayName.contains("refresh"))
			{
				refresh();
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
		
		invent.addItem(new ItemStackCreator(ChatColor.WHITE + "Total Entities: " +  sec
				+ LaggAPI.totalEntities(), new String[]
					{
					ChatColor.GRAY + "This is the total amount",
					ChatColor.GRAY + "of entities on this server."
					}
				, Material.COMPASS, 1).getItem());
			invent.addItem(new ItemStackCreator(ChatColor.WHITE + "Loaded Chunks: " + sec
					+ LaggAPI.loadedChunks(), new String[]
						{
						ChatColor.GRAY + "This is the total amount",
						ChatColor.GRAY + "of loaded chunks on this",
						ChatColor.GRAY + "server."
						}
					, Material.GRASS, 1).getItem());
			invent.setItem(invent.getSize() - 1,
					new ItemStackCreator(ChatColor.WHITE + "Refresh", new String[]
							{
							ChatColor.GRAY + "This will refresh this GUI.",
							}
						, Material.PAPER, 1).getItem());
			
		    Runtime runtime = Runtime.getRuntime();
		    long freeMemory = (runtime.totalMemory()-runtime.freeMemory())/1048576L;
		    long usedMemory = runtime.totalMemory()/1048576L;
			invent.addItem(new ItemStackCreator(ChatColor.WHITE + "RAM Usage: " + sec
					+ freeMemory + "MB / " + usedMemory + "MB", new String[]
						{
						ChatColor.GRAY + "This is the total amount",
						ChatColor.GRAY + "of loaded chunks on this",
						ChatColor.GRAY + "server."
						}
					, Material.CHEST, 1).getItem());

			invent.addItem(new ItemStackCreator(ChatColor.WHITE + "Uptime: " + sec +
					MessageAPI.getTimeLeftString( (long) GeneralTask.getSeconds()), new String[]
							{
							ChatColor.GRAY + "This is the uptime of the server.",
							}
						, Material.WATCH, 1).getItem());
	}

}
