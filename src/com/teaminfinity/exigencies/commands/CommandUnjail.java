package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.JailAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandUnjail extends ExigenciesCommand implements CommandExecutor {

	public CommandUnjail() {
		super("Unjail", Cmd.COMMAND_UNJAIL);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		if(args.length < 1)
		{
			sender.sendMessage(super.getCommandExample().usage);
			return false;
		}
		
		Player target = PlayerAPI.getPlayer(args[0]);
		if(target == null)
		{
			sender.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, args[0]));
			return false;
		}
		
		if(!(JailAPI.isJailed(target.getUniqueId())))
		{
			sender.sendMessage(MessageVal.COMMAND_UNJAIL_UNJAILED.getValue());
			return false;
		}
		
		JailAPI.unjail(target.getUniqueId());
		target.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_UNJAIL_SUCCESS_OTHER,
				PlayerAPI.getName(sender)));
		sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_UNJAIL_SUCCESS_SELF, 
				PlayerAPI.getName(target)));
		
		return false;
	}
	
	

}
