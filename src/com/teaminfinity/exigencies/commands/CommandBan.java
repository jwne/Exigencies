package com.teaminfinity.exigencies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.command.PermBanCreator;

public class CommandBan extends ExigenciesCommand implements CommandExecutor {

	public CommandBan() {
		super("Ban", Cmd.COMMAND_BAN);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		if(args.length == 0)
		{
			sender.sendMessage(super.getCommandExample().usage);
			return false;
		}
		
		String reason = null;
		String target = null;
		boolean online = false;
		Player pTarget = null;
		

		pTarget = PlayerAPI.getPlayer(args[0]);
		if(pTarget != null)
		{
			target = pTarget.getName();
			online = true;
		}
		else
		{
			target = args[0];
		}
		
		if(args.length == 1)
		{
			reason = MessageVal.COMMAND_BAN_DEFAULT_MESSAGE.getValue();
		}
		else
		{
			reason = MessageVal.COLOR_SECONDARY.getValue();
			for(int i = 0; i < args.length - 1; i ++)
			{
				reason += args[i + 1] + " ";
			}
			reason = MessageAPI.removeLastChar(reason);
			reason += ChatColor.RESET;
		}

		reason = MessageAPI.addColour(reason);
		if(online)
		{
			pTarget.kickPlayer(MessageVal.COMMAND_BAN_PREFIX.getValue() +
					reason);
		}
		new PermBanCreator(target, reason, sender);
		sender.sendMessage(MessageVal.COMMAND_BAN_SUCCESS_SELF.getValue()
				.replaceAll("%TARGET%", ((pTarget != null) 
						? PlayerAPI.getName(pTarget) : target))
				.replaceAll("%REASON%", reason));
		
		return false;
	}
	
	

}
