package com.teaminfinity.exigencies.commands;

import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PowerToolAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.command.PowerToolData;

public class CommandPt extends ExigenciesCommand implements CommandExecutor {

	public CommandPt() {
		super("Pt", Cmd.COMMAND_POWERTOOL);
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
		
		if(args[0].equalsIgnoreCase("clear"))
		{
			PowerToolAPI.clear(player.getUniqueId());
			player.sendMessage(MessageVal.COMMAND_POWERTOOL_CLEAR_SUCCESS.getValue());
			return true;
		}
		if(args[0].equalsIgnoreCase("remove"))
		{
			PowerToolAPI.remove(player.getUniqueId(), player.getItemInHand());
			player.sendMessage(MessageVal.COMMAND_POWERTOOL_REMOVE_SUCCESS.getValue());
			return true;
		}
		if(args[0].equalsIgnoreCase("list"))
		{
			List<PowerToolData> data = PowerToolAPI.get(player.getUniqueId());
			if(data == null || data.isEmpty())
			{
				player.sendMessage(MessageVal.COMMAND_POWERTOOL_EMPTY.getValue());
				return false;
			}
			
			for(PowerToolData sub : data)
			{
				player.sendMessage(MessageVal.COLOR_PRIMARY.getValue() + sub.getMaterial().toString().toLowerCase()
						.replaceAll("_", " " ) + " (" + sub.getDurability() + "):");
				player.sendMessage(MessageVal.COLOR_SECONDARY.getValue() + "   " + sub.getCommand());
			}
			return true;
		}
		
		String dat = "";
		for(int i = 0; i < args.length; i++)
		{
			dat += args[i] + " ";
		}
		dat = MessageAPI.removeLastChar(dat);
		
		PowerToolAPI.add(player.getUniqueId(), dat, player.getItemInHand());
		player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_POWERTOOL_SET_SUCCESS,
				dat));
		
		return false;
	}
	
	

}
