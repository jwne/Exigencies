package com.teaminfinity.exigencies.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandLore extends ExigenciesCommand implements CommandExecutor {

	public CommandLore() 
	{
		super("Lore", Cmd.COMMAND_LORE);
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
			player.sendMessage(MessageVal.COMMAND_LORE_NEED_ITEM.getValue());
			return false;
		}
		
		String lore = MessageVal.COLOR_SECONDARY.getValue() + "";
		int current = 0;
		for(String arg : args)
		{
			current++;
			if(args.length != current)
			{
				lore += arg + " ";
			}
			else
			{
				lore += arg;
			}
		}
		
		ItemMeta meta = player.getItemInHand().getItemMeta();
		List<String> listLore = meta.getLore();
		if(listLore == null)
		{
			listLore = new ArrayList<String>();
		}
		listLore.add(MessageAPI.addColour(lore));
		meta.setLore(listLore);
		player.getItemInHand().setItemMeta(meta);
		player.sendMessage(MessageVal.COMMAND_LORE_SUCCESS.getValue());

		return false;
	}



}
