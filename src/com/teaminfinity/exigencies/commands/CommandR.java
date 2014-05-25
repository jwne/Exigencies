package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.command.MessageFactory;

public class CommandR extends ExigenciesCommand implements CommandExecutor {

	public CommandR() {
		super("r", Cmd.COMMAND_R);
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

		String message = MessageVal.COLOR_SECONDARY.getValue() + "";
		int current = 0;
		for(String arg : args)
		{
			current++;
			if(args.length != current)
			{
				message += arg + " ";
			}
			else
			{
				message += arg;
			}
		}
		
		CommandSender other = PlayerAPI.getLatestReply(sender);
		if(other == null)
		{
			sender.sendMessage(MessageVal.COMMANDS_R_NO_TARGET.getValue());
			return false;
		}
		
		new MessageFactory(sender, other, message);

		return false;
	}



}
