package com.teaminfinity.exigencies.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.api.LogAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.api.TemporaryAPI;
import com.teaminfinity.exigencies.commands.CommandMsg;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.LogType;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.TempValueType;
import com.teaminfinity.exigencies.objects.AdminAlerter;
import com.teaminfinity.exigencies.objects.F;
import com.teaminfinity.exigencies.objects.command.TempResult;
import com.teaminfinity.exigencies.objects.events.UserMessageEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
	{
		TempResult result = TemporaryAPI.get(e.getPlayer(), TempValueType.MUTE);
		if(result.isActive())
		{
			e.getPlayer().sendMessage(MessageAPI.getReformat(MessageVal
					.COMMAND_TEMPMUTE_CANCEL_CHAT, result.getStrTimeLeft(true)));
			e.setCancelled(true);
		}
		else
		{
			F playerFile = FileAPI.getFileForPlayer(e.getPlayer());
			playerFile.loadFile();
			if(playerFile.getBoolean("muted"))
			{
				e.setCancelled(true);
				e.getPlayer().sendMessage(MessageVal.COMMAND_MUTE_MUTED_STOPCHAT.getValue());
			}
		}
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onAsyncPlayerChat2(AsyncPlayerChatEvent e)
	{
		if(ConfigVal.EXIGENCIES_CHAT_ENABLED.getBooleanValue())
		{
			e.setFormat(ConfigVal.EXIGENCIES_CHAT_FORMAT.getStringValue()
					.replaceAll("%PLAYER%", PlayerAPI.getName(e.getPlayer()))
					.replaceAll("%MESSAGE%", e.getMessage()));
		}
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onUserMessage(UserMessageEvent e)
	{
		String format = MessageVal.COMMAND_MSG_FORMAT.getValue().replaceAll("%MESSAGE%", e.getMessage());
		if(e.isSpy())
		{
			String spyFormat = format.replaceAll("%TARGET%", PlayerAPI.getName(e.getSender()))
					.replaceAll("%TARGET2%", PlayerAPI.getName(e.getReciever()));
			spyFormat = ChatColor.stripColor(spyFormat);
			spyFormat = MessageVal.COLOR_TERTIARY.getValue() + ChatColor.ITALIC + spyFormat;
			new AdminAlerter(spyFormat);
			LogAPI.set(ChatColor.stripColor(spyFormat), LogType.MESSAGE);
		}

		if(e.isCancelled())
		{
			return;
		}
		PlayerAPI.addLatestReply(e.getSender(), e.getReciever());
		PlayerAPI.addLatestReply(e.getReciever(), e.getSender());
		String recieverFormat = format.replaceAll("%TARGET%", 
				PlayerAPI.getName(e.getSender()))
				.replaceAll("%TARGET2%", MessageVal.COMMAND_MSG_WORD_YOU.getValue());
		String senderFormat = format.replaceAll("%TARGET2%", 
				PlayerAPI.getName(e.getReciever()))
				.replaceAll("%TARGET%", MessageVal.COMMAND_MSG_WORD_YOU.getValue());
		e.getSender().sendMessage(senderFormat);
		e.getReciever().sendMessage(recieverFormat);
	}

	@EventHandler
	public void onLazyMessage(AsyncPlayerChatEvent e)
	{
		if(e.isCancelled())
		{
			return;
		}
		if(!(e.getMessage().startsWith("@")))
		{
			return;
		}
		String[] data = e.getMessage().split(" ");
		data[0] = data[0].replaceAll("@", "");
		new CommandMsg().onCommand(e.getPlayer(), Core.instance.getCommand("msg"),
				"msg", data);
		e.setCancelled(true);
	}

}
