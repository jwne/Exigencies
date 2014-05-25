package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandReply extends ExigenciesCommand implements CommandExecutor {

	public CommandReply() 
	{
		super("Reply", Cmd.COMMAND_R);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		
		new CommandR().onCommand(sender, cmd, commandLabel, args);
		
		return false;
	}

}
