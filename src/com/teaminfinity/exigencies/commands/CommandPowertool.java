package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandPowertool extends ExigenciesCommand implements CommandExecutor {

	public CommandPowertool() {
		super("Powertool", Cmd.COMMAND_POWERTOOL);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		
		new CommandPt().onCommand(sender, cmd, commandLabel, args);
		return false;
	}
	
	

}
