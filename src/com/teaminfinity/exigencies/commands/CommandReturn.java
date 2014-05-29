package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandReturn extends ExigenciesCommand implements CommandExecutor {

	public CommandReturn() {
		super("Return", Cmd.COMMAND_BACK);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		new CommandBack().onCommand(sender, cmd, commandLabel, args);
		
		return false;
	}
	
	

}
