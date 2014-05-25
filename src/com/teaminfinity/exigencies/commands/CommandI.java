package com.teaminfinity.exigencies.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.Items;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.command.ItemInfo;

public class CommandI extends ExigenciesCommand implements CommandExecutor {

	public CommandI() {
		super("I", Cmd.COMMAND_I);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(sender instanceof Player))
		{
			getCommandExample().sendMustBeIngame(sender);
			return false;
		}
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		if(args.length < 1)
		{
			sender.sendMessage(super.getCommandExample().usage);
			return false;
		}
		
		Player player = (Player) sender;
		ItemInfo iteminfo = Items.itemByName(args[0]);
		if(iteminfo == null)
		{
			player.sendMessage(MessageAPI.getReformat(MessageVal.ITEM_NOT_FOUND, args[0]));
			return false;
		}
		if(iteminfo.getType().equals(Material.AIR))
		{
			player.sendMessage(MessageAPI.getReformat(MessageVal.ITEM_NOT_FOUND, args[0]));
			return false;
		}
		
		int amount = iteminfo.getStackSize();
		
		if(args.length > 1)
		{
			try
			{
				amount = Integer.parseInt(args[1]);
			}
			catch(Exception e)
			{
				//Doesn't really matter because of the preset
			}
		}
		
		player.getInventory().addItem(new ItemStack(iteminfo.getType(), amount, iteminfo.getSubTypeId()));
		player.sendMessage(MessageVal.COMMAND_I_SUCCESS_SELF.getValue().replaceAll("%AMOUNT%", amount + "")
				.replaceAll("%TYPE%", iteminfo.getType().toString().toLowerCase().replaceAll("_", " ")));
		
		return false;
	}
	
	

}
