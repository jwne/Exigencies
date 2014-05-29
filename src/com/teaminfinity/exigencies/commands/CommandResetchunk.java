package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandResetchunk extends ExigenciesCommand implements CommandExecutor {

	public CommandResetchunk() {
		super("Resetchunk", Cmd.COMMAND_ABUSE);
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
		
		player.getWorld().regenerateChunk(player.getLocation().getChunk().getX(),
				player.getLocation().getChunk().getZ());
		player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_RESETCHUNK_SUCCESS_SELF, 
				player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ()));
		
		return false;
	}
	
	

}
