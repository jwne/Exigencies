package com.teaminfinity.exigencies.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandUnban extends ExigenciesCommand implements CommandExecutor {

	public CommandUnban() {
		super("Unban", Cmd.COMMAND_UNBAN);
	}

	@SuppressWarnings("deprecation")
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
			sender.sendMessage(super.getCommandExample().usage);
			return false;
		}
		
		UUID targetId = PlayerAPI.getByName(args[0]);
		if(targetId == null)
		{
			sender.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, 
					args[0]));
			return false;
		}
		
		OfflinePlayer target = Bukkit.getOfflinePlayer(targetId);
		if(target == null)
		{
			sender.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, 
					args[0]));
			return false;
		}
		
		if(!(target.isBanned()))
		{
			sender.sendMessage(MessageVal.COMMAND_UNBAN_NOT_BANNED.getValue());
			return false;
		}
		target.setBanned(false);
		sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_UNBAN_SUCCESS_SELF, 
				args[0]));
		
		return false;
	}
	
	

}
