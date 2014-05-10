package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandPe extends ExigenciesCommand implements CommandExecutor {

	public CommandPe() {
		super("pe", Cmd.COMMAND_PARTICLEFFECT);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		new CommandParticleeffect().onCommand(sender, cmd, commandLabel, args);
		
		return false;
	}
	
	

}
