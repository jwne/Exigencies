package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandMessage extends ExigenciesCommand implements CommandExecutor {

	public CommandMessage() 
	{
		super("Message", Cmd.COMMAND_MSG);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		
		new CommandMsg().onCommand(sender, cmd, commandLabel, args);
		
		return false;
	}

}
