package com.teaminfinity.exigencies.objects.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PowerToolData {

	public PowerToolData(Material material, short durability, String command)
	{
		this.setMaterial(material);
		this.setDurability(durability);
		this.setCommand(command);
	}

	public PowerToolData(String material, short durability, String command)
	{
		this.setMaterial(Material.valueOf(material));
		this.setDurability(durability);
		this.setCommand(command);
	}

	private transient Material material;
	private transient short durability;
	private transient String command;
	
	public void run(Player player)
	{
		Bukkit.dispatchCommand(player, command.replaceFirst("/", ""));
	}
	
	public Material getMaterial()
	{
		return material;
	}
	
	public void setMaterial(Material material) 
	{
		this.material = material;
	}
	
	public short getDurability()
	{
		return durability;
	}
	
	public void setDurability(short durability) 
	{
		this.durability = durability;
	}
	
	public String getCommand() 
	{
		return command;
	}
	
	public void setCommand(String command) 
	{
		this.command = command;
	}
	
}
