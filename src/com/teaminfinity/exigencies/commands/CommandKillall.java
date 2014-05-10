package com.teaminfinity.exigencies.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandKillall extends ExigenciesCommand implements CommandExecutor {

	public CommandKillall() {
		super("Killall", Cmd.COMMAND_KILLALL);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		int count = 0;
		
		for(World world : Bukkit.getWorlds())
		{
			for(Entity entity : world.getEntities())
			{
				if(entity instanceof LivingEntity)
				{
					if(entity.getType() == EntityType.PLAYER)
					{
						continue;
					}
					if(entity.getType().equals(EntityType.ENDER_CRYSTAL))
					{
						continue;
					}
					if(entity.getType().equals(EntityType.ENDER_CRYSTAL))
					{
						continue;
					}
					count++;
					entity.remove();
				}
			}
		}
		
		sender.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_KILLALL_SUCCESS_SELF, count + ""));
		
		return false;
	}
	
	

}
