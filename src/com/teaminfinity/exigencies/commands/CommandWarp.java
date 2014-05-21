package com.teaminfinity.exigencies.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.WarpAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.exception.WarpNotFoundException;
import com.teaminfinity.exigencies.objects.exception.WorldNotFoundException;

public class CommandWarp extends ExigenciesCommand implements CommandExecutor {

	public CommandWarp() {
		super("Warp", Cmd.COMMAND_WARP);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(sender instanceof Player))
		{
			getCommandExample().sendMustBeIngame(sender);
			return false;
		}
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		Player player = (Player) sender;
		
		if(args.length < 1)
		{
			player.sendMessage(super.getCommandExample().usage);
			return false;
		}
		
		Location warp = null;
		
		try 
		{
			warp = WarpAPI.getWarp(args[0]);
		} 
		catch (WorldNotFoundException e)
		{
			player.sendMessage(MessageVal.COMMAND_WARP_UNABLE_WORLDEXCEPTIONS.getValue());
			return false;
		}
		catch (WarpNotFoundException e)
		{
			sender.sendMessage(MessageAPI.getReformat(MessageVal.WARP_NOT_FOUND, e.getName()));
			return false;
		}
		
		player.teleport(warp);
		player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_WARP_SUCCESS_SELF, 
				args[0]));
		
		return false;
	}
	
	

}
