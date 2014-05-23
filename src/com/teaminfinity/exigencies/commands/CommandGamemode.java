package com.teaminfinity.exigencies.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandGamemode extends ExigenciesCommand implements CommandExecutor {

	public CommandGamemode() 
	{
		super("Gamemode", Cmd.COMMAND_GAMEMODE);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(Cmd.COMMAND_GAMEMODE.hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}

		if(!(sender instanceof Player))
		{
			getCommandExample().sendMustBeIngame(sender);
			return false;
		}

		if(args.length == 0)
		{
			sender.sendMessage(getCommandExample().usage);
			return false;
		}

		if(args.length == 1)
		{
			if(!(sender instanceof Player))
			{
				sender.sendMessage(MessageVal.COMMAND_MUST_BE_INGAME.getValue());
				return false;
			}
			Player player = (Player) sender;

			GameMode gamemode = getGameMode(args[0]);
			if(gamemode == null)
			{
				player.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, args[0]));
				return false;
			}
			player.setGameMode(gamemode);
			player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_GAMEMODE_SUCCESS_SELF, gamemode));
		}
		else
		{
			GameMode gamemode = getGameMode(args[0]);
			if(gamemode == null)
			{
				sender.sendMessage(MessageAPI.getReformat(MessageVal.GAMEMODE_NOT_FOUND, args[0]));
				return false;
			}


			String targetPlayer = args[1];

			if(targetPlayer.equalsIgnoreCase("-a") || targetPlayer.equals("*") || targetPlayer.equalsIgnoreCase("@a"))
			{
				for(Player player:Bukkit.getOnlinePlayers())
				{
					player.setGameMode(gamemode);
					player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_GAMEMODE_SUCCESS_RECIEVE, gamemode, sender));
				}
				sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_GAMEMODE_SUCCESS_EVERYBODY, gamemode));
				return true;
			}

			Player target = PlayerAPI.getPlayer(targetPlayer);
			if(target == null)
			{
				sender.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, targetPlayer));
				return false;
			}

			target.setGameMode(gamemode);
			target.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_GAMEMODE_SUCCESS_RECIEVE, gamemode, sender));
			sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_GAMEMODE_SUCCESS_OTHER, gamemode, target));
		}

		return false;
	}
	
	protected GameMode getGameMode(String input)
	{
		GameMode gm = null;
		switch(input.toLowerCase()){
		case "0":
		case "s":
		case "survival":
			gm=GameMode.SURVIVAL;
			break;
		case "1":
		case "c":
		case "creative":
			gm = GameMode.CREATIVE;
			break;
		case "2":
		case "a":
		case "adventure":
			gm=GameMode.ADVENTURE;
			break;
		}
		return gm;
	}

}
