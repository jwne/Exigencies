package com.teaminfinity.exigencies.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandAnvil extends ExigenciesCommand implements CommandExecutor {

	public CommandAnvil() {
		super("Anvil", Cmd.COMMAND_ANVIL);
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
		
		Player player = (Player) sender;
		
		player.openInventory(Bukkit.createInventory(player, InventoryType.ANVIL));
		player.sendMessage(MessageVal.COMMAND_ANVIL_SUCCESS_SELF.getValue());
		
		return false;
	}
	
	

}
