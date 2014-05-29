package com.teaminfinity.exigencies.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.AdminAlerter;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandAdminchat extends ExigenciesCommand implements CommandExecutor, Listener {

	private transient ArrayList<CommandSender> inChat = new ArrayList<>();
	public transient static CommandAdminchat inst;
	
	public CommandAdminchat() {
		super("AdminChat", Cmd.COMMAND_ADMINCHAT);
		CommandAdminchat.inst = this;
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		if(args.length == 0)
		{
			if(inChat.contains(sender))
			{
				inChat.remove(sender);
			}
			else
			{
				inChat.add(sender);
			}
			sender.sendMessage(MessageVal.COMMAND_ADMINCHAT_SUCCESS_SELF.getValue());
		}
		else
		{
			String message = MessageVal.COLOR_SECONDARY.getValue();
			for(int i = 0; i < args.length; i ++)
			{
				message += args[i] + " ";
			}
			message = MessageAPI.removeLastChar(message);
			chatAsAdmin(sender, message);
		}
		
		return false;
	}
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
	{
		if(!(inChat.contains(e.getPlayer())))
		{
			return;
		}
		e.setCancelled(true);
		chatAsAdmin(e.getPlayer(), e.getMessage());
	}
	
	private void chatAsAdmin(CommandSender sender, String message)
	{
		new AdminAlerter(MessageVal.COMMAND_ADMINCHAT_PREFIX.getValue() 
				+ PlayerAPI.getName(sender) + ChatColor.WHITE + ": " +
				MessageVal.COLOR_SECONDARY.getValue() + message);
	}

}
