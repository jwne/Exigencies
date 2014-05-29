package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandAc extends ExigenciesCommand implements CommandExecutor {

	public CommandAc() {
		super("AC", Cmd.COMMAND_ADMINCHAT);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		CommandAdminchat.inst.onCommand(sender, cmd, commandLabel, args);
		return false;
	}

}
