package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandExigencies extends ExigenciesCommand implements CommandExecutor {

	public CommandExigencies() {
		super("Exigencies", Cmd.COMMAND_EX);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		
		new CommandEx().onCommand(sender, cmd, commandLabel, args);
		return false;
	}
	
	

}
