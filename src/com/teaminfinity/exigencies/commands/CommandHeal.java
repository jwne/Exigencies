package com.teaminfinity.exigencies.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandHeal extends ExigenciesCommand implements CommandExecutor {

	public CommandHeal()
	{
		super("Heal", Cmd.COMMAND_HEAL);
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
			if(!(sender instanceof Player))
			{
				getCommandExample().sendMustBeIngame(sender);
				return false;
			}
			Player player = (Player) sender;
			heal(player);
			player.sendMessage(MessageVal.COMMAND_HEAL_SUCCESS_RECIEVE.getValue());
		}
		else
		{
			String targetPlayer = args[0];
			Player target = PlayerAPI.getPlayer(targetPlayer);

			if(targetPlayer.equalsIgnoreCase("-a") || targetPlayer.equals("*")
					|| targetPlayer.equalsIgnoreCase("@a"))
			{
				for(Player p:Bukkit.getOnlinePlayers())
				{
					heal(p);
					p.sendMessage(MessageVal.COMMAND_HEAL_SUCCESS_RECIEVE.getValue());
				}

				sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_HEAL_SUCCESS_OTHER, "everybody"));
				return false;
			}

			if(target == null)
			{
				sender.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, targetPlayer));
				return false;
			}
			target.sendMessage(MessageVal.COMMAND_HEAL_SUCCESS_RECIEVE.getValue());
			sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_HEAL_SUCCESS_OTHER, PlayerAPI.getName(target)));
		}


		return false;
	}

	private void heal(Player player)
	{
		player.setHealth(20D);
		player.setFoodLevel(20);
		player.setSaturation(5F);
		player.setFireTicks(0);
	}



}
