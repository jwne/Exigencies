package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.Perm;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.command.MessageFactory;

public class CommandMsg extends ExigenciesCommand implements CommandExecutor {

	public CommandMsg() {
		super("Msg", Cmd.COMMAND_MSG);
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

		String msg = MessageVal.COLOR_SECONDARY.getValue();
		for(int i = 1; i < args.length; i ++)
		{
			msg += args[i] + " ";
		}
		msg = MessageAPI.removeLastChar(msg);
		
		if(Perm.COLOR_CHAT.hasPermission(sender))
		{
			msg = MessageAPI.addColour(msg);
		}

		new MessageFactory(sender, target, msg);

		return false;
	}



}
