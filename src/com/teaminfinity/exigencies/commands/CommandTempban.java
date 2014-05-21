package com.teaminfinity.exigencies.commands;

import java.util.Date;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.api.TimeFrameAPI;
import com.teaminfinity.exigencies.enums.Cmd;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.TimeFrame;
import com.teaminfinity.exigencies.objects.F;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.objects.command.TempBanCreator;

public class CommandTempban extends ExigenciesCommand implements CommandExecutor {

	public CommandTempban() 
	{
		super("TempBan", Cmd.COMMAND_TEMPBAN);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) 
	{
		if(!(super.getCommandExample().hasPermission(sender)))
		{
			sender.sendMessage(MessageVal.NO_PERMISSION.getValue());
			return false;
		}
		
		if (args.length > 2)
		{
			Player target = PlayerAPI.getPlayer(args[0]);
			int length = 0;
			try
			{
				length = Integer.parseInt(args[1]);
			}
			catch(Exception e)
			{
				sender.sendMessage(MessageAPI.getReformat(MessageVal.NOT_A_NUMBER, args[1]));
				return false;
			}
			
			TimeFrame frame = TimeFrame.getTimeFrame(args[2]);
			if(frame == null)
			{
				sender.sendMessage(MessageAPI.getReformat(MessageVal.TIME_FRAME_NOT_FOUND, args[2]));
				return false;
			}
			
			String msg = MessageVal.COLOR_SECONDARY.getValue();
			if(args.length > 3)
			{
				for (int i = 3; i < args.length; i++)
				{
					msg = msg + args[i] + " ";
				}
				msg = MessageAPI.addColour(msg);
				msg = MessageAPI.removeLastChar(msg);
				msg += ChatColor.RESET;
			}
			else
			{
				msg = MessageVal.COMMAND_TEMPBAN_DEFAULTMESSAGE.getValue();
			}
			
			long newTime = TimeFrameAPI.getTargetDate(frame, length) + 1000;
			Date date = new Date();
			date.setTime(newTime);
			int timeLeft = (int)TimeFrameAPI.getTimeLeft(newTime);
			
			UUID targetId = null;
			if(target != null)
			{
				targetId = target.getUniqueId();
			}
			else
			{
				//Need to retrieve id.
				//TODO: Fix tempbancreator because you cant ban a uuid atm!
				F database = FileAPI.getUUIDDatabaseFile();
				database.loadFile();
				String found = database.getString(args[0].toLowerCase());
				if(found == null)
				{
					sender.sendMessage(MessageAPI.getReformat(MessageVal.PLAYER_NOT_FOUND,
							args[0]));
					return false;
				}
				targetId = UUID.fromString(found);
			}
			

			F database = FileAPI.getUUIDDatabaseFile();
			database.loadFile();
			String name = database.getString(targetId.toString());
			
			target.kickPlayer(MessageVal.COMMAND_TEMPBAN_MESSAGE.getValue());
			new TempBanCreator(name, msg, sender, date);
			sender.sendMessage(MessageVal.COMMAND_TEMPBAN_SUCCESS_SELF.getValue()
				.replaceAll("%TARGET%", ((target != null) 
						? PlayerAPI.getName(target) : args[0]))
				.replaceAll("%REASON%", msg)
				.replaceAll("%LENGTH%", MessageAPI.getTimeLeftString(timeLeft)));
		}
		else
		{
			sender.sendMessage(super.getCommandExample().usage);
			return false;
		}
		
		return false;
	}

}
