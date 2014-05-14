package com.teaminfinity.exigencies.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PersistenceAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandSetspawn extends ExigenciesCommand implements CommandExecutor {

	public CommandSetspawn() {
		super("Setspawn", Cmd.COMMAND_SETSPAWN);
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
		Location loc = player.getEyeLocation();
		ConfigVal.SPAWN_LOCATION.setValue(PersistenceAPI.getLocation().toStringValue(loc));
		ConfigVal.SPAWN_LOCATION.saveValue();
		player.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		player.sendMessage(MessageVal.COMMAND_SETSPAWN_SUCCESS_SELF.getValue());
		
		return false;
	}
	
	

}
