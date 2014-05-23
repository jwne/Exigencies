package com.teaminfinity.exigencies.gui;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.teaminfinity.exigencies.objects.ItemStackCreator;
import com.teaminfinity.exigencies.objects.fun.SocketExploiter;

public class DosGUI implements ExigenciesGUI {

	private transient ArrayList<UUID> viewers = new ArrayList<>();
	private transient HashMap<UUID, String> data = new HashMap<UUID, String>();
	
	/**
	 * Definitely don't want to do this,
	 * as there would be no *simple* way
	 * of determining who to 'packetize'.
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
			
			Player player = (Player) e.getWhoClicked();
			if(e.getCurrentItem().getDurability() == 5)
			{
				new SocketExploiter(data.get(player.getUniqueId()), player.getUniqueId());
				player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_DOS_SUCCESS_SELF, 
						data.get(player.getUniqueId())));
				player.closeInventory();
			}
			if(e.getCurrentItem().getDurability() == 14)
			{
				player.closeInventory();
			}
		}
	}

	/**
	 * Generate inventory
	 */
	public void show(Player player, String target)
	{
		Inventory inventory = Bukkit.createInventory(null, 9, 
				MessageVal.COMMAND_DOS_GUI_TITLE.getValue());
		player.openInventory(inventory);
		
		populate(inventory);
		
		viewers.add(player.getUniqueId());
		data.put(player.getUniqueId(), target);
	}
	
	private void populate(Inventory invent)
	{
		invent.addItem(new ItemStackCreator(MessageVal.COLOR_SECONDARY.getValue() + "No"
				, new String[]
							{
					MessageVal.COLOR_TERTIARY.getValue() + "- This is HIGHLY illegal",
					MessageVal.COLOR_TERTIARY.getValue() + "- You can be prosecuted for this.",
					MessageVal.COLOR_TERTIARY.getValue() + "- It goes against the Computer Misuse Act",
					MessageVal.COLOR_TERTIARY.getValue() + "- I strongly reccomend you not to use this.",
					MessageVal.COLOR_TERTIARY.getValue() + "- This will cause severe lag."
							}
			, Material.WOOL, 1, (short) 14).getItem());
		invent.addItem(new ItemStackCreator(MessageVal.COLOR_SECONDARY.getValue() + "Yes"
				, new String[]
							{
					MessageVal.COLOR_TERTIARY.getValue() + "- Use with caution",
							}
			, Material.WOOL, 1, (short) 5).getItem());
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
