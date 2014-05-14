package com.teaminfinity.exigencies.objects;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.MovementStopperType;

public class MovementStopperObject {

	public MovementStopperObject(Runnable task, int ticks, MovementStopperType type)
	{
		this.task = task;
		this.ticks = ticks;
		this.type = type;
	}
	
	private transient MovementStopperType type;
	private transient int ticks;
	private transient Runnable task;
	
	public void run()
	{
		task.run();
	}
	
	public void decrease(int amount, UUID user)
	{
		ticks -= amount;
		Player player = PlayerAPI.getPlayer(user);
		if(player == null)
		{
			return;
		}
		player.sendMessage(MessageAPI.getReformat(MessageVal.MOVEMENT_STOPPER_TELEPORT_UPDATE,
				((int) ticks / 20) + 1 + ""));
	}
	
	public boolean ready()
	{
		return ticks <= 0;
	}
	
	public int getTicks()
	{
		return ticks;
	}
	
	public Runnable getTask()
	{
		return task;
	}
	
	public MovementStopperType getType()
	{
		return type;
	}
	
}
