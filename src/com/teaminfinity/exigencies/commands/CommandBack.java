package com.teaminfinity.exigencies.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.api.PersistenceAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.F;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandBack extends ExigenciesCommand implements CommandExecutor {

	public CommandBack() {
		super("Back", Cmd.COMMAND_BACK);
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
		
		F file = FileAPI.getFileForPlayer(player);
		file.loadFile();
		String strLoc = file.getString("last_tp");
		if(strLoc == null)
		{
			player.sendMessage(MessageVal.COMMAND_BACK_NO_PREVIOUS.getValue());
			return false;
		}
		
		Location loc = (Location) PersistenceAPI.getLocation().fromStringValue(strLoc);
		player.teleport(loc);
		player.sendMessage(MessageVal.COMMAND_BACK_SUCCESS_SELF.getValue());
		
		return false;
	}
	
	

}
