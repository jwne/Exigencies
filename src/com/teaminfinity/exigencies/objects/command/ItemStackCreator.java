package com.teaminfinity.exigencies.objects.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackCreator {
	
	public ItemStackCreator(String name, List<String> lore, Material material, int amount, short dura)
	{
		this.item = new ItemStack(material, amount, dura);
		ItemMeta meta = item.getItemMeta();
		if(name != null)
		{
			meta.setDisplayName(name);
		}
		if(lore != null)
		{
			meta.setLore(lore);
		}
		item.setItemMeta(meta);
	}

	public ItemStackCreator(String name, List<String> lore, Material material, int amount)
	{
		this.item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		if(name != null)
		{
			meta.setDisplayName(name);
		}
		if(lore != null)
		{
			meta.setLore(lore);
		}
		item.setItemMeta(meta);
	}

	public ItemStackCreator(String name, String[] lore, Material material, int amount)
	{
		this.item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		if(name != null)
		{
			meta.setDisplayName(name);
		}
		if(lore != null)
		{

			meta.setLore(Arrays.asList(lore));
		}
		item.setItemMeta(meta);
	}
	
	public ItemStackCreator(String name, String[] lore, Material material, int amount, short dura)
	{
		this.item = new ItemStack(material, amount, dura);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
	}
	
	private transient ItemStack item;
	
	public ItemStack getItem()
	{
		return item;
	}
	
}
