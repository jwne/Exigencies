package com.teaminfinity.exigencies.commands;

import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.command.MobChain;

public class CommandSpawnmob extends ExigenciesCommand implements CommandExecutor {

	public CommandSpawnmob()
	{
		super("SpawnMob", Cmd.COMMAND_SPAWNMOB);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}

		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		if(args.length == 1 && args[0].equalsIgnoreCase("list"))
		{
			String prefix = MessageVal.COMMAND_SPAWNMOB_LIST.getValue();
			String message = "";
			int buffer = 0;
			int max = EntityType.values().length;
			for(EntityType type : EntityType.values())
			{
				buffer++;
				if(buffer == max)
				{
					message += MessageVal.COLOR_SECONDARY.getValue() + type.toString().toLowerCase()
							+ MessageVal.COLOR_PRIMARY.getValue() + ".";
				}
				else
				{
					message += MessageVal.COLOR_SECONDARY.getValue() + type.toString().toLowerCase()
							+ MessageVal.COLOR_PRIMARY.getValue() + ", ";
				}
			}
			message = ChatColor.stripColor(message);
			message = MessageAPI.removeLastChar(message);
			prefix = prefix.replaceAll("%MOBS%", message);
			sender.sendMessage(prefix);
			return true;
		}

		if(args.length < 2)
		{
			sender.sendMessage(super.getCommandExample().usage);
			return false;
		}
		
		int amount = 1;
		try
		{
			amount = Integer.parseInt(args[1]);
		}
		catch(Exception e)
		{
			sender.sendMessage(MessageAPI.getReformat(MessageVal.NOT_A_NUMBER, args[1]));
			return false;
		}
		
		if(amount > ConfigVal.SPAWNMOB_MAX.getIntegerValue())
		{
			amount = ConfigVal.SPAWNMOB_MAX.getIntegerValue();
		}
		
		Player player = (Player) sender;	

		MobChain chain = new MobChain(args[0], amount);
		if(chain.isEmpty())
		{
			player.sendMessage(MessageVal.COMMAND_SPAWNMOB_EMPTY.getValue());
			
			String unknown = MessageVal.COMMAND_SPAWNMOB_UNKNOWNTYPES.getValue();
			String addition = ChatColor.stripColor(MessageAPI.toString(chain.getNotFound()));
			player.sendMessage(unknown + addition);
			return false;
		}
		chain.spawn(player.getTargetBlock(null, 200).getRelative(BlockFace.UP).getLocation());
		player.sendMessage(MessageVal.COMMAND_SPAWNMOB_SUCCESS.getValue().replaceAll("%AMOUNT%", amount + "")
				.replaceAll("%CHAIN%", args[0].toLowerCase()));
		
		return false;
	}

}
