package com.teaminfinity.exigencies.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.CheckData;
import com.teaminfinity.exigencies.objects.command.ItemStackCreator;

public class CheckGUI implements ExigenciesGUI {

	private transient ArrayList<UUID> viewers = new ArrayList<>();
	
	/**
	 * Definitely don't want to do this,
	 * as there would be no *simple* way
	 * of determining whos data to show.
	 */
	@Deprecated
	@Override
	public void show(Player player) 
	{
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
		}
	}

	/**
	 * Generate inventory
	 */
	public void show(Player player, CheckData data)
	{
		Inventory inventory = Bukkit.createInventory(null, 9, 
				MessageAPI.getReformat(MessageVal.COMMAND_CHECK_GUI_TITLE,
						data.getTarget()));
		populate(inventory, data);
		player.openInventory(inventory);
		viewers.add(player.getUniqueId());
	}

	private void populate(Inventory invent, CheckData data)
	{
		String pri = MessageVal.COLOR_PRIMARY.getValue();
		String sec = MessageVal.COLOR_SECONDARY.getValue();
		List<String> names = new ArrayList<>();
		for(String str : data.getResult().getNames())
		{
			names.add(sec + str);
		}
		invent.addItem(new ItemStackCreator(pri + "Associated Accounts", names, 
				Material.BOOK, 1).getItem());
		List<String> ips = new ArrayList<>();
		for(String str : data.getResult().getIps())
		{
			ips.add(sec + str);
		}
		invent.addItem(new ItemStackCreator(pri + "Associated IP's", ips, 
				Material.BOOK, 1).getItem());
		List<String> ids = new ArrayList<>();
		for(String str : data.getResult().getIds())
		{
			ids.add(sec + str);
		}
		invent.addItem(new ItemStackCreator(pri + "Associated ID's", ids, 
				Material.BOOK, 1).getItem());
		invent.addItem(new ItemStackCreator(pri + "Latest IP", new String[]
				{
				sec + data.getLatestIp()
				}
				, Material.NAME_TAG, 1).getItem());
		invent.addItem(new ItemStackCreator(pri + "Ticks Taken", new String[]
				{
				sec + (long) ((data.getSecondsTaken() == 0) ? 10 : data.getSecondsTaken())
				}
				, Material.WATCH, 1).getItem());
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

}
