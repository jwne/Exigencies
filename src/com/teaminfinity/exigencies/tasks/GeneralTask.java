package com.teaminfinity.exigencies.tasks;

import com.teaminfinity.exigencies.api.TipAPI;
import com.teaminfinity.exigencies.objects.MovementStopper;

public class GeneralTask implements Runnable {

	public GeneralTask(int delay)
	{
		GeneralTask.setDelay(delay);
	}
	
	private static transient int seconds = 0;
	private static transient int delay = -1;
	
	@Override
	public void run() 
	{
		seconds++;
		TipAPI.cycle();
		MovementStopper.cycle(delay);
	}
	
	public static int getSeconds()
	{
		return seconds;
	}

	public static int getDelay()
	{
		return delay;
	}

	public static void setDelay(int delay)
	{
		GeneralTask.delay = delay;
	}

}
