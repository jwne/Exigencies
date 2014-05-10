package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandTp extends ExigenciesCommand implements CommandExecutor {

	public CommandTp() {
		super("Tp", Cmd.COMMAND_TP);
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
		
		if(args.length == 1)
		{
			if(!(sender instanceof Player))
			{
				getCommandExample().sendMustBeIngame(sender);
				return false;
			}
			
			Player player = (Player) sender;
			Player target = PlayerAPI.getPlayer(args[0]);
			if(target == null)
			{
				player.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, args[0]));
				return false;
			}
			
			player.teleport(target);
			player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_TP_SUCCESS_SELF, PlayerAPI.getName(target)));
		}
		else
		{
			Player target = PlayerAPI.getPlayer(args[0]);
			Player target2 = PlayerAPI.getPlayer(args[1]);
			if(target == null)
			{
				sender.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, args[0]));
				return false;
			}
			if(target2 == null)
			{
				sender.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, args[1]));
				return false;
			}
			
			target.teleport(target2);
			target.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_TP_SUCCESS_RECIEVE,
					PlayerAPI.getName(target2)).replaceAll("%SENDER%", PlayerAPI.getName(sender)));
			sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_TP_SUCCESS_GIVEN,
					PlayerAPI.getName(target)).replaceAll("%TARGET2%", PlayerAPI.getName(target2)));
		}
		
		return false;
	}
	
	

}
