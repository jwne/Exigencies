package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.gui.GUIHandler;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandDos extends ExigenciesCommand implements CommandExecutor {

	public CommandDos() {
		super("Dos", Cmd.COMMAND_DOS);
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
		
		if(args.length < 1)
		{
			player.sendMessage(super.getCommandExample().usage);
			return false;
		}
		
		GUIHandler.getDos().show(player, args[0]);
		
		return false;
	}
	
	

}
