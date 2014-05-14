package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.fun.SheepCannon;

public class CommandSheepcannon extends ExigenciesCommand implements CommandExecutor {

	public CommandSheepcannon() {
		super("SheepCannon", Cmd.COMMAND_SHEEPCANNON);
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
		
		new SheepCannon(((Player)sender).getEyeLocation());
		
		return false;
	}
	
	

}
