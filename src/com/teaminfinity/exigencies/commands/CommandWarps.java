package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.WarpAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandWarps extends ExigenciesCommand implements CommandExecutor {

	public CommandWarps() {
		super("Warps", Cmd.COMMAND_WARPS);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		String[] warps = WarpAPI.getWarps();
		if(warps.length == 0)
		{
			sender.sendMessage(MessageVal.COMMAND_WARPS_NONE.getValue());
			return false;
		}
		
		sender.sendMessage(MessageVal.COMMAND_WARPS_PREFIX.getValue() 
				+ MessageAPI.toString(warps).replaceAll(".yml", ""));
		
		return false;
	}
	
	

}
