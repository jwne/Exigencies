package com.teaminfinity.exigencies.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PersistenceAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.MovementStopperType;
import com.teaminfinity.exigencies.enums.Perm;
import com.teaminfinity.exigencies.objects.MovementStopper;
import com.teaminfinity.exigencies.objects.MovementStopperObject;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandSpawn extends ExigenciesCommand implements CommandExecutor {

	public CommandSpawn() {
		super("Spawn", Cmd.COMMAND_SPAWN);
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

		final Player player = (Player) sender;
		final Location location = (Location) PersistenceAPI.getLocation().fromStringValue(
				ConfigVal.SPAWN_LOCATION.getStringValue());

		if(Perm.COMMAND_SPAWN_INSTANT.hasPermission(player))
		{
			player.teleport(location);
			player.sendMessage(MessageVal.COMMAND_SPAWN_SUCCESS_SELF.getValue());
		}
		else
		{
			player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_SPAWN_SUCCESS_WAITING,
					ConfigVal.SPAWN_DELAY.getIntegerValue() + ""));
			MovementStopper.append(player.getUniqueId(), new MovementStopperObject( 
					new Runnable()
					{
						@Override
						public void run()
						{
							player.teleport(location);
							player.sendMessage(MessageVal.COMMAND_SPAWN_SUCCESS_SELF.getValue());
						}
					}
					, ConfigVal.SPAWN_DELAY.getIntegerValue() * 20, MovementStopperType.TELEPORT));
		}


		return false;
	}



}
