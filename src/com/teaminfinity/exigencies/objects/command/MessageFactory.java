package com.teaminfinity.exigencies.objects.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.objects.events.UserMessageEvent;

public class MessageFactory {

	public MessageFactory(CommandSender sender, CommandSender recipient, String message)
	{
		Bukkit.getPluginManager().callEvent(new UserMessageEvent(sender, recipient, message));
	}
	
	
}
