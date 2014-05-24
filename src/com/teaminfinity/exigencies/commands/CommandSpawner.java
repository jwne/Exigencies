package com.teaminfinity.exigencies.commands;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandSpawner extends ExigenciesCommand implements CommandExecutor {

	public CommandSpawner() {
		super("Spawner", Cmd.COMMAND_SPAWNER);
	}
	
	@SuppressWarnings("deprecation")
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
		
		EntityType type = EntityType.fromName(args[0]);
		if(type == null)
		{
			sender.sendMessage(MessageVal.COMMAND_SPAWNER_UNKNOWN_TYPE.getValue());
			return false;
		}
		
		try
		{
			CreatureSpawner spawner = (CreatureSpawner) player.getTargetBlock(null, 200).getState();
			
			if(spawner.getSpawnedType().equals(type))
			{
				sender.sendMessage(MessageVal.COMMAND_SPAWNER_ALREADY_THAT.getValue());
				return true;
			}
			
			spawner.setSpawnedType(type);
			spawner.update();
			sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_SPAWNER_SUCCESS_SELF, 
					type.toString().toLowerCase()));
		}
		catch (Throwable ex)
		{
			sender.sendMessage(MessageVal.COMMAND_SPAWNER_UNABLE.getValue());
		}
		
		return false;
	}

}
