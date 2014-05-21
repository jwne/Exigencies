package com.teaminfinity.exigencies.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.AbuseMethod;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.ItemStackCreator;

public class AbuseGUI implements ExigenciesGUI {

	private transient ArrayList<UUID> viewers = new ArrayList<>();
	private transient HashMap<UUID, UUID> data = new HashMap<UUID, UUID>();
	
	/**
	 * Definitely don't want to do this,
	 * as there would be no *simple* way
	 * of determining who to abuse.
	 */
	@Deprecated
	@Override
	public void show(Player player) 
	{
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e)
	{
		data.remove(e.getPlayer().getUniqueId());
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
			
			if(!(e.getCurrentItem().hasItemMeta()))
			{
				return;
			}
			
			if(!(e.getCurrentItem().getItemMeta().hasDisplayName()))
			{
				return;
			}
			
			String displayName = e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase();
			
			Player player = (Player) e.getWhoClicked();
			
			for(AbuseMethod method : AbuseMethod.values())
			{
				if(method.getDisplayName().toLowerCase().equalsIgnoreCase(displayName))
				{
					method.getTechnique().run(player.getUniqueId(), data.get(player.getUniqueId()));
					player.closeInventory();
					break;
				}
			}
		}
	}

	/**
	 * Generate inventory
	 */
	public void show(Player player, Player target)
	{
		Inventory inventory = Bukkit.createInventory(null, 27, 
				MessageAPI.getReformat(MessageVal.COMMAND_ABUSE_GUI_TITLE,
						PlayerAPI.getName(target)));
		player.openInventory(inventory);
		
		for(AbuseMethod method : AbuseMethod.values())
		{
			inventory.addItem(new ItemStackCreator(method.getDisplayName(),
					new String[]
							{
							}
					, method.getMaterial(), 1, method.getDurability()).getItem());
		}
		
		
		viewers.add(player.getUniqueId());
		data.put(player.getUniqueId(), target.getUniqueId());
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
