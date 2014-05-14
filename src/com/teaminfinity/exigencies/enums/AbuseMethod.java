package com.teaminfinity.exigencies.enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import com.teaminfinity.exigencies.objects.fun.AbuseTechnique;
import com.teaminfinity.exigencies.objects.fun.AnvilKiller;
import com.teaminfinity.exigencies.objects.fun.ChatEmbarrassing;
import com.teaminfinity.exigencies.objects.fun.ChatHacker;
import com.teaminfinity.exigencies.objects.fun.ChatJustinBieber;
import com.teaminfinity.exigencies.objects.fun.Confuser;
import com.teaminfinity.exigencies.objects.fun.Crasher;
import com.teaminfinity.exigencies.objects.fun.Detonator;
import com.teaminfinity.exigencies.objects.fun.EnderchestWiper;
import com.teaminfinity.exigencies.objects.fun.HackedClientBanner;
import com.teaminfinity.exigencies.objects.fun.InventoryWiper;
import com.teaminfinity.exigencies.objects.fun.LevelReseter;
import com.teaminfinity.exigencies.objects.fun.Murderer;
import com.teaminfinity.exigencies.objects.fun.Poison;
import com.teaminfinity.exigencies.objects.fun.PotatoSpammer;
import com.teaminfinity.exigencies.objects.fun.Pyromaniac;
import com.teaminfinity.exigencies.objects.fun.Rocketer;
import com.teaminfinity.exigencies.objects.fun.SoundAnnoyer;
import com.teaminfinity.exigencies.objects.fun.TortureWiper;
import com.teaminfinity.exigencies.objects.fun.Witherer;

public enum AbuseMethod {

	DETONATOR(Material.TNT, ChatColor.DARK_RED + "Explode target", (short) 0, new Detonator()),
	ANVILKILLER(Material.ANVIL, ChatColor.GRAY + "Squash target with an anvil", (short) 0, new AnvilKiller()),
	HACKEDCLIENTBANNER(Material.SPONGE, ChatColor.DARK_GRAY + "Ban target for hacked client", (short) 0, new HackedClientBanner()),
	POTATOSPAM(Material.POISONOUS_POTATO, ChatColor.DARK_GREEN + "Potato spam target", (short) 0, new PotatoSpammer()),
	LEVELRESETER(Material.EXP_BOTTLE, ChatColor.GREEN + "Reset target's xp", (short) 0, new LevelReseter()),
	CONFUSER(Material.ENDER_PORTAL, ChatColor.LIGHT_PURPLE + "Confuse target", (short) 0, new Confuser()),
	SOUNDANNOY(Material.NOTE_BLOCK, ChatColor.YELLOW + "Annoy target with sounds", (short) 0, new SoundAnnoyer()),
	ROCKETER(Material.FIREWORK, ChatColor.RED + "Launch target in the air", (short) 0, new Rocketer()),
	CHATJUSTINBIEBER(Material.SKULL_ITEM, ChatColor.DARK_RED + "Force target to quote justin bieber", (short) 3, new ChatJustinBieber()),
	CHATEMBARRASSING(Material.SKULL_ITEM, ChatColor.DARK_RED + "Force target to embarass their self", (short) 3, new ChatEmbarrassing()),
	CHATHACKER(Material.SKULL_ITEM, ChatColor.DARK_RED + "Force target to admit hacking", (short) 3, new ChatHacker()),
	CRASH(Material.BEDROCK, ChatColor.DARK_GRAY + "Crash target", (short) 0, new Crasher()),
	TORTUREWIPER(Material.CARROT_STICK, ChatColor.DARK_RED + "Torture wipe target", (short) 0, new TortureWiper()),
	ENDERCHESTWIPER(Material.ENDER_CHEST, ChatColor.DARK_RED + "Wipe target's ender chest", (short) 0, new EnderchestWiper()),
	INVENTORYWIPER(Material.LAVA_BUCKET, ChatColor.GOLD + "Wipe target's inventory", (short) 0, new InventoryWiper()),
	WITHERER(Material.SKULL_ITEM, ChatColor.DARK_GRAY + "Wither target", (short) 1, new Witherer()),
	MURDERER(Material.SKULL_ITEM, ChatColor.GRAY + "Kill target", (short) 0, new Murderer()),
	POISON(Material.POTION, ChatColor.DARK_GREEN + "Poison target", (short) 8260, new Poison()),
	PYROMANIAC(Material.FIRE, ChatColor.GOLD + "Set target on fire", (short) 0, new Pyromaniac());
	
	AbuseMethod(Material material, String displayName, short durability, AbuseTechnique technique)
	{
		this.setMaterial(material);
		this.setDisplayName(displayName);
		this.setTechnique(technique);
		this.setDurability(durability);
	}

	private transient Material material;
	private transient String displayName;
	private transient AbuseTechnique technique;
	private transient short durability;
	
	public Material getMaterial()
	{
		return material;
	}
	
	public void setMaterial(Material material) 
	{
		this.material = material;
	}
	
	public String getDisplayName() 
	{
		return displayName;
	}
	
	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}
	
	public AbuseTechnique getTechnique() 
	{
		return technique;
	}
	
	public void setTechnique(AbuseTechnique technique)
	{
		this.technique = technique;
	}

	public short getDurability() 
	{
		return durability;
	}

	public void setDurability(short durability) 
	{
		this.durability = durability;
	}
	
}
