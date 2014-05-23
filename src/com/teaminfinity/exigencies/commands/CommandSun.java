package com.teaminfinity.exigencies.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandSun extends ExigenciesCommand implements CommandExecutor {

	public CommandSun() {
		super("Sun", Cmd.COMMAND_SUN);
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
			world.setStorm(false);
			world.setThundering(false);
			world.setWeatherDuration(ConfigVal.WEATHER_CHANGE_DURATION.getIntegerValue());
		}
		
		sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_WEATHER_SUCCESS_SELF, 
				super.getAlias().toLowerCase()));
		
		return false;
	}
	
	

}
