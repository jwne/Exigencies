package com.teaminfinity.exigencies.objects.command;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.api.IpAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.gui.GUIHandler;
import com.teaminfinity.exigencies.objects.BetterRunnable;
import com.teaminfinity.exigencies.objects.F;
import com.teaminfinity.exigencies.utils.SchedulingUtility;

public class CheckDaemon implements BetterRunnable {

	private final transient int DELAY = 10;
	private final transient int LOOP_THROUGHS = 20;

	private transient String latestIp;
	private transient int taskId;
	private transient List<String> playerfiles;
	private transient UUID sender;
	private transient String targetName;
	private transient CheckResult result;
	private transient int loops = 0;
	
	public CheckDaemon(UUID sender, Player target)
	{
		this.result = new CheckResult();
		this.result.addId(target.getUniqueId().toString());
		this.result.addIp(IpAPI.getIp(target.getAddress()));
		this.latestIp = this.result.getIps().get(0);
		this.result.addName(target.getName().toLowerCase());
		this.sender = sender;
		this.targetName = target.getName();
		this.playerfiles = new ArrayList<>();
		//Using exigencies files rather than offline players
		//this is because the offlineplayer data
		//will not necessarily be perfectly linked to
		//exigencies data
		File playerFileDirectory = FileAPI.getPlayerFileDir();
		for(String str : playerFileDirectory.list())
		{
			playerfiles.add(str);
		}
		
		begin();
	}
	
	@Override
	public void begin()
	{
		this.taskId = SchedulingUtility.beginRepeating(this, DELAY);
	}
	
	@Override
	public void run()
	{
		loops++;
		for(int i = 0; i < LOOP_THROUGHS; i ++)
		{
			if(playerfiles.isEmpty())
			{
				break;
			}
			String fileName = playerfiles.get(0);
			playerfiles.remove(fileName);
			F playerFile = FileAPI.getFileForPlayer(fileName);
			playerFile.loadFile();
			List<String> ips = playerFile.getStringList("ips");
			List<String> usednames = playerFile.getStringList("used_names");
			for(String ip : ips)
			{
				if(result.containsIp(ip))
				{
					for(String str : usednames)
					{
						result.addName(str);
						result.addId(PlayerAPI.getByName(str).toString());
					}
					for(String str : ips)
					{
						result.addIp(str);
					}
				}
			}
		}

		if(playerfiles.isEmpty())
		{
			end();
		}
	}
	
	@Override
	public void end()
	{
		SchedulingUtility.stopRepeating(taskId);
		Player player = PlayerAPI.getPlayer(sender);
		if(player == null)
		{
			//Wow. Player starts this whole process
			//and just logs out.
			return;
		}
		GUIHandler.getCheck().show(player, new CheckData(player, targetName, result, getSecondsTaken(), latestIp));
	}
	
	private double getSecondsTaken()
	{
		double value = ((DELAY / 20) * loops); 
		if(value == 0)
		{
			return DELAY;
		}
		return value;
	}
	
}
