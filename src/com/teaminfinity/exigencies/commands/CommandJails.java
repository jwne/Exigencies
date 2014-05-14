package com.teaminfinity.exigencies.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.api.JailAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.command.JailObject;

public class CommandJails extends ExigenciesCommand implements CommandExecutor {

	public CommandJails()
	{
		super("Jails", Cmd.COMMAND_JAILS);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		List<String> jails = new ArrayList<String>();
		for(JailObject jail : JailAPI.getJails())
		{
			jails.add(jail.getName());
		}
		
		if(jails.size() == 0)
		{
			sender.sendMessage(MessageVal.COMMAND_JAILS_EMPTY.getValue());
			return false;
		}
		
		sender.sendMessage(MessageVal.COMMAND_JAILS_PREFIX.getValue() + MessageAPI.toString(jails));
		
		return false;
	}
	
	

}
