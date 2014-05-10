package com.teaminfinity.exigencies.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandSpam extends ExigenciesCommand implements CommandExecutor {

	public CommandSpam() {
		super("Spam", Cmd.COMMAND_SPAM);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		if(args.length < 2)
		{
			sender.sendMessage(super.getCommandExample().usage);
			return false;
		}
		
		int times = 1;
		try
		{
			times = Integer.parseInt(args[0]);
		}
		catch(Exception e)
		{
			sender.sendMessage(MessageAPI.getReformat(MessageVal.NOT_A_NUMBER, args[0]));
			return false;
		}
		
		String message = MessageVal.COLOR_SECONDARY.getValue() + "";
		int current = 0;
		for(String arg : args)
		{
			current++;
			if(current > 1)
			{
				if(args.length != current)
				{
					message += arg + " ";
				}
				else
				{
					message += arg;
				}
			}
		}
		
		for(int i = 0; i < times; i++)
		{
			Bukkit.broadcastMessage(MessageAPI.addColour(message));
		}
		
		return false;
	}
	
	

}
