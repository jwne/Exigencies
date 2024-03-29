package com.teaminfinity.exigencies.commands;

import java.util.UUID;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.F;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandMute extends ExigenciesCommand implements CommandExecutor {

	public CommandMute() {
		super("Mute", Cmd.COMMAND_MUTE);
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
		
		if(target != null)
		{
			F playerFile = FileAPI.getFileForPlayer(target.getUniqueId());
			playerFile.loadFile();
			playerFile.set("muted", true);
			playerFile.saveFile();
			target.sendMessage(MessageVal.COMMAND_MUTE_SUCCESS_OTHER.getValue());
			sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_MUTE_SUCCESS_SELF,
					PlayerAPI.getName(target)));
		}
		else
		{
			UUID id = PlayerAPI.getByName(args[0]);
			if(id == null)
			{
				player.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, args[0]));
				return false;
			}
			F playerFile = FileAPI.getFileForPlayer(id);
			playerFile.loadFile();
			playerFile.set("muted", true);
			playerFile.saveFile();
			sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_MUTE_SUCCESS_SELF,
					args[0]));
		}
		
		
		return false;
	}
	
	

}
