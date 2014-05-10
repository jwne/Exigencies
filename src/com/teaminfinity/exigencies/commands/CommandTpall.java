package com.teaminfinity.exigencies.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;

public class CommandTpall extends ExigenciesCommand implements CommandExecutor {

	public CommandTpall() {
		super("Tpall", Cmd.COMMAND_TPALL);
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
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!(p.getUniqueId().equals(player.getUniqueId())))
			{
				p.teleport(player);
				p.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_TP_SUCCESS_RECIEVE,
						PlayerAPI.getName(player)).replaceAll("%SENDER%", PlayerAPI.getName(sender)));
			}
		}
		
		player.sendMessage(MessageAPI.getReformat(MessageVal.COMMAND_TP_SUCCESS_GIVEN, "everybody")
				.replaceAll("%TARGET2%", PlayerAPI.getName(sender)));
		
		return false;
	}
	
	

}
