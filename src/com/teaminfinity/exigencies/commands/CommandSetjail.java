package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.JailAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.command.JailObject;

public class CommandSetjail extends ExigenciesCommand implements CommandExecutor {

	public CommandSetjail() {
		super("Setjail", Cmd.COMMAND_SETJAIL);
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
		
		if(JailAPI.exists(args[0]))
		{
			player.sendMessage(MessageVal.COMMAND_SETJAIL_EXISTS.getValue());
			return false;
		}
		
		JailObject jail = new JailObject(args[0], player.getEyeLocation());
		jail.save();
		JailAPI.addJail(jail);
		player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_SETJAIL_SUCCESS, args[0]));
		
		return false;
	}
	
	

}
