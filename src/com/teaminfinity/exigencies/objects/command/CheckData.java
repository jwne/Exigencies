package com.teaminfinity.exigencies.objects.command;

import org.bukkit.entity.Player;

public class CheckData {

	public CheckData(Player sender, String target, CheckResult result, double secondsTaken, String latestIp,
			long lastJoin)
	{
		this.lastJoin = lastJoin;
		this.latestIp = latestIp;
		this.sender = sender;
		this.target = target;
		this.result = result;
		this.secondsTaken = secondsTaken;
	}
	
	private transient String latestIp;
	private transient Player sender;
	private transient String target;
	private transient CheckResult result;
	private transient long lastJoin;
	private transient double secondsTaken;
	
	public String getLatestIp()
	{
		return latestIp;
	}
	
	public Player getSender()
	{
		return sender;
	}
	
	public String getTarget()
	{
		return target;
	}
	
	public CheckResult getResult()
	{
		return result;
	}
	
	public double getSecondsTaken()
	{
		return secondsTaken;
	}
	
	public long getLastJoin()
	{
		return lastJoin;
	}
	
}
