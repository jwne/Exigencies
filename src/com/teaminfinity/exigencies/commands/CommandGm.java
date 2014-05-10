package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandGm extends ExigenciesCommand implements CommandExecutor {

	public CommandGm() 
	{
		super("Gm", Cmd.COMMAND_GAMEMODE);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		
		new CommandGamemode().onCommand(sender, cmd, commandLabel, args);
		
		return false;
	}

}
