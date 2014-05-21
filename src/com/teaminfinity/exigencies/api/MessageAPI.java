package com.teaminfinity.exigencies.api;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.enums.MessageVal;

public abstract class MessageAPI {

	public static String getReformat(MessageVal before, Player sender, Player target)
	{
		return before.getValue().replaceAll("%SENDER%", 
				PlayerAPI.getName(sender)).replaceAll("%TARGET%", PlayerAPI.getName(target));
	}
	
	public static String getReformat(MessageVal before, Player player)
	{
		return before.getValue().replaceAll("%PLAYER%", PlayerAPI.getName(player));
	}
	
	public static String getReformat(MessageVal before, String target)
	{
		return before.getValue().replaceAll("%TARGET%", target);
	}
	
	public static String getReformat(MessageVal before, CommandSender sender, Player target)
	{
		return before.getValue().replaceAll("%SENDER%", 
				PlayerAPI.getName(sender)).replaceAll("%TARGET%", PlayerAPI.getName(target));
	}
	
	public static String getReformat(MessageVal before, GameMode gamemode)
	{
		return before.getValue().replaceAll("%GAMEMODE%", gamemode.toString().toLowerCase());
	}	
	
	public static String getReformat(MessageVal before, GameMode gamemode, Player target)
	{
		return before.getValue().replaceAll("%GAMEMODE%", gamemode.toString().toLowerCase())
				.replaceAll("%TARGET%", PlayerAPI.getName(target));
	}
	
	public static String getReformat(MessageVal before, GameMode gamemode, CommandSender sender)
	{
		return before.getValue().replaceAll("%GAMEMODE%", gamemode.toString().toLowerCase())
				.replaceAll("%SENDER%", PlayerAPI.getName(sender));
	}
	
	public static String toString(Location location)
	{
		return location.getBlockX()+"x, " + location.getBlockY() + "y, " + location.getBlockZ() 
				+ "z, " + location.getWorld().getName() + ".";
	}
	
	public static String getTimeLeftString(long timeLeft)
	{
		long minutes = timeLeft/60; //automatically cast to int 
		long seconds = timeLeft-minutes*60; //subtract off whole minutes 
		
		long hours = minutes/60;
		minutes = minutes-hours*60;
		long days = hours/24;
		hours = hours - days*24;
		long months = days/28;
		days = days - months*28;
		long years = months/12;
		months = months - years*12;
		
		
		String stringBuilder = "";
		if(years!=0){if(years!=1){stringBuilder=stringBuilder+years+" years ";}else{stringBuilder=stringBuilder+years+" year ";}}
		if(months!=0){if(months!=1){stringBuilder=stringBuilder+months+" months ";}else{stringBuilder=stringBuilder+months+" month ";}}
		if(days!=0){if(days!=1){stringBuilder=stringBuilder+days+" days ";}else{stringBuilder=stringBuilder+days+" day ";}}
		if(hours!=0){if(hours!=1){stringBuilder=stringBuilder+hours+" hours ";}else{stringBuilder=stringBuilder+hours+" hour ";}}
		if(minutes!=0){if(minutes!=1){stringBuilder=stringBuilder+minutes+" minutes ";}else{stringBuilder=stringBuilder+minutes+" minute ";}}
		if(seconds!=0){if(seconds!=1){stringBuilder=stringBuilder+seconds+" seconds";}else{stringBuilder=stringBuilder+seconds+" second";}}
		return stringBuilder;
	}
	
	public static String toString(String[] strings)
	{
		String str = "";
		for(String string : strings)
		{
			str += MessageVal.COLOR_SECONDARY.getValue() + string +
					MessageVal.COLOR_PRIMARY.getValue() + ", ";
		}
		str = removeLastChar(str);
		str = removeLastChar(str);
		str += MessageVal.COLOR_PRIMARY.getValue() + ".";
		return str;
	}
	
	public static String toString(List<String> strings)
	{
		String str = "";
		for(String string : strings)
		{
			str += MessageVal.COLOR_SECONDARY.getValue() + string +
					MessageVal.COLOR_PRIMARY.getValue() + ", ";
		}
		str = removeLastChar(str);
		str = removeLastChar(str);
		str += MessageVal.COLOR_PRIMARY.getValue() + ".";
		return str;
	}
	
	public static String addColour(String str)
	{
		return ChatColor.translateAlternateColorCodes('&', str);
	}
	
	public static String removeFirstChar(String s) 
	{
		return s.substring(1);
	}
	
	public static String removeLastChar(String s)
	{
		return s.substring(0, s.length() - 1);
	}
	
}
