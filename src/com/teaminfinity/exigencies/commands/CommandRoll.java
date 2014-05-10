package com.teaminfinity.exigencies.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandRoll extends ExigenciesCommand implements CommandExecutor {

	public CommandRoll() {
		super("Roll", Cmd.COMMAND_ROLL);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		int max = 100;
		if(args.length > 0)
		{
			try
			{
				max = Integer.parseInt(args[0]);
			}
			catch(Exception e)
			{
				//Already have preset
			}
		}
		if(max <= 0)
		{
			max = 100;
		}
		
		int score = new Random().nextInt(max) + 1;
		
		Bukkit.broadcastMessage(MessageVal.COMMAND_ROLL_SUCCESS_BROADCAST.getValue()
				.replaceAll("%PLAYER%", PlayerAPI.getName(sender)).replaceAll("%SCORE%", score + "")
				.replaceAll("%MAX%", max + ""));
		
		return false;
	}
	
	

}
