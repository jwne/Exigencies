package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.gui.GUIHandler;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandParticleeffect extends ExigenciesCommand implements CommandExecutor {

	public CommandParticleeffect() {
		super("Particleeffect", Cmd.COMMAND_PARTICLEFFECT);
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
		GUIHandler.getParticleEffect().show(player);
		
		return false;
	}
	
	

}
