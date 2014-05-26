package com.teaminfinity.exigencies.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandRide extends ExigenciesCommand implements CommandExecutor {

	public CommandRide() {
		super("Ride", Cmd.COMMAND_RIDE);
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
		
		Player target = PlayerAPI.getPlayer(args[0]);
		if(target == null)
		{
			player.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND, args[0]));
			return false;
		}
		
		if(target.getUniqueId().equals(player.getUniqueId()))
		{
			player.sendMessage(MessageVal.COMMAND_RIDE_CANT_SELF.getValue());
			return false;
		}
		
		if(player.getPassenger() != null)
		{
			if(player.getPassenger().getUniqueId().equals(target.getUniqueId()))
			{
				player.sendMessage(MessageVal.COMMAND_RIDE_PREVENTCRASH.getValue());
				return false;
			}
		}
		
		player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_RIDE_SUCCESS_SELF,
				PlayerAPI.getName(target)));
		target.setPassenger(player);
		
		return false;
	}
	
	

}
