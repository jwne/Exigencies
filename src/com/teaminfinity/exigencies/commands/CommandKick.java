package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandKick extends ExigenciesCommand implements CommandExecutor {

	public CommandKick() {
		super("Kick", Cmd.COMMAND_KICK);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
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
		
		Player target = PlayerAPI.getPlayer(args[0]);
		if(target == null)
		{
			player.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, args[0]));
			return false;
		}
		
		String reason = MessageVal.COMMAND_KICK_DEFAULT_REASON.getValue();
		
		if(args.length > 1)
		{
			reason = MessageVal.COLOR_SECONDARY.getValue();
			for(int i = 1; i < args.length; i ++)
			{
				reason += args[i] + " ";
			}
			reason = MessageAPI.removeLastChar(reason);
		}
		
		target.kickPlayer(reason);
		sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_KICK_SUCCESS_SELF, 
				PlayerAPI.getName(target)).replaceAll("%REASON%", reason));
		
		return false;
	}
	
	

}
