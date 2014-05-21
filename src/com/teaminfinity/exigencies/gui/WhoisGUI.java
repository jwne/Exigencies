package com.teaminfinity.exigencies.gui;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import com.teaminfinity.exigencies.api.IpAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.ItemStackCreator;

public class WhoisGUI implements ExigenciesGUI {

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
	public void show(Player player, Player target)
	{
		Inventory inventory = Bukkit.createInventory(null, 9, 
				MessageAPI.getReformat(MessageVal.COMMAND_WHOIS_GUI_TITLE,
						PlayerAPI.getName(target)));
		player.openInventory(inventory);
		
		inventory.addItem(new ItemStackCreator(ChatColor.WHITE + "Opped: " + 
				((target.isOp()) ? ChatColor.GREEN + "true" : ChatColor.RED + "false"), 
				new String[]
						{
						}
				, Material.GOLDEN_APPLE, 1).getItem());
		
		inventory.addItem(new ItemStackCreator(ChatColor.WHITE + "UUID: " + 
				MessageVal.COLOR_SECONDARY.getValue() + target.getUniqueId().toString(), 
				new String[]
						{
						}
				, Material.SKULL_ITEM, 1, (short) 3).getItem());
		
		inventory.addItem(new ItemStackCreator(ChatColor.WHITE + "Gamemode: " + 
				MessageVal.COLOR_SECONDARY.getValue() + target.getGameMode().toString().toLowerCase(), 
				new String[]
						{
						}
				, Material.SPONGE, 1).getItem());
		
		inventory.addItem(new ItemStackCreator(ChatColor.WHITE + "Food: " + 
				MessageVal.COLOR_SECONDARY.getValue() + target.getFoodLevel(), 
				new String[]
						{
						ChatColor.WHITE + "Saturation: " + MessageVal.COLOR_SECONDARY.getValue() 
							+ target.getSaturation()
						}
				, Material.CAKE, 1).getItem());
		
		inventory.addItem(new ItemStackCreator(ChatColor.WHITE + "Health: " + 
				MessageVal.COLOR_SECONDARY.getValue() + (int) ((Damageable) target).getHealth(), 
				new String[]
						{
						}
				, Material.POTION, 1, (short) 16421).getItem());
		
		inventory.addItem(new ItemStackCreator(ChatColor.WHITE + "Ip Address: " + 
				MessageVal.COLOR_SECONDARY.getValue() + IpAPI.getIp(target.getAddress()), 
				new String[]
						{
						}
				, Material.NAME_TAG, 1).getItem());
		
		inventory.addItem(new ItemStackCreator(ChatColor.WHITE + "Location: " + 
				MessageVal.COLOR_SECONDARY.getValue() + MessageAPI.toString(target.getLocation()), 
				new String[]
						{
						}
				, Material.COMPASS, 1).getItem());
		
		viewers.add(player.getUniqueId());
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
