package com.teaminfinity.exigencies.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandRename extends ExigenciesCommand implements CommandExecutor {

	public CommandRename() 
	{
		super("Rename", Cmd.COMMAND_RENAME);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
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

		Player player = (Player) sender;	

		if(args.length < 1)
		{
			sender.sendMessage(super.getCommandExample().usage);
			return false;
		}
		
		if(player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR)
		{
			player.sendMessage(MessageVal.COMMAND_RENAME_NEED_ITEM.getValue());
			return false;
		}
		
		String name = MessageVal.COLOR_SECONDARY.getValue() + "";
		int current = 0;
		for(String arg : args)
		{
			current++;
			if(args.length != current)
			{
				name += arg + " ";
			}
			else
			{
				name += arg;
			}
		}
		
		ItemMeta meta = player.getItemInHand().getItemMeta();
		meta.setDisplayName(MessageAPI.addColour(name));
		player.getItemInHand().setItemMeta(meta);
		player.sendMessage(MessageVal.COMMAND_RENAME_SUCCESS.getValue());

		return false;
	}



}
