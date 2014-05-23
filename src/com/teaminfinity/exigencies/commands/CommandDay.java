package com.teaminfinity.exigencies.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandDay extends ExigenciesCommand implements CommandExecutor {

	private transient final int TIME = 1000;
	
	public CommandDay() {
		super("Day", Cmd.COMMAND_DAY);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		for(World world : Bukkit.getWorlds())
		{
			world.setTime(TIME);
		}
		sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_TIME_SUCCESS_SELF,
				TIME + ""));
		
		return false;
	}
	
	

}
