package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.JailAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.command.JailObject;

public class CommandJail extends ExigenciesCommand implements CommandExecutor {

	public CommandJail() {
		super("Jail", Cmd.COMMAND_JAIL);
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
		
		Player target = PlayerAPI.getPlayer(args[0]);
		if(target == null)
		{
			sender.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, args[0]));
			return false;
		}
		
		JailObject jail = JailAPI.get(args[1]);
		if(jail == null)
		{
			sender.sendMessage(MessageVal.COMMAND_JAIL_NOT_FOUND.getValue());
			return false;
		}
		
		JailAPI.jailUser(target.getUniqueId(), jail.getName());
		target.teleport(jail.getLoc());
		sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_JAIL_SUCCESS_SELF, PlayerAPI.getName(target)));
		target.sendMessage(MessageVal.COMMAND_JAIL_SUCCESS_OTHER.getValue());
		
		return false;
	}
	
	

}
