package com.teaminfinity.exigencies.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.minecraft.server.v1_7_R3.EntityPlayer;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utilities {
	
	public static int getPing(Player p)
	{
		CraftPlayer cp = (CraftPlayer) p;
		EntityPlayer ep = cp.getHandle();
		return ep.ping;	
	}
	
	public static String getTime()
	{
		java.util.Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String time = format.format(now);
		time.replaceAll(" ", "-");	
		return time;
	}
	
	public static String getName(CommandSender sender)
	{
		if(sender instanceof Player){
			Player p = (Player)sender;
			String name="";
			name=p.getName();
			if(p.getDisplayName()!=null){
				name=p.getDisplayName();
			}
			return name;
		}else{
			return sender.getName();
		}
	}
	
	public static GameMode getGameMode(String input){
		GameMode gm = null;
		switch(input.toLowerCase()){
		case "0":
			gm=GameMode.SURVIVAL;
			break;
		case "1":
			gm = GameMode.CREATIVE;
			break;
		case "2":
			gm=GameMode.ADVENTURE;
			break;
		case "s":
			gm=GameMode.SURVIVAL;
			break;
		case "c":
			gm=GameMode.CREATIVE;
			break;
		case "a":
			gm=GameMode.ADVENTURE;
			break;
		case "survival":
			gm=GameMode.SURVIVAL;
			break;
		case "creative":
			gm=GameMode.CREATIVE;
			break;
		case "adventure":
			gm=GameMode.ADVENTURE;
			break;
		}
		return gm;
	}

}
