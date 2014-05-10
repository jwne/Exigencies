package com.teaminfinity.exigencies.tasks;

import com.teaminfinity.exigencies.api.TipAPI;

public class GeneralTask implements Runnable {

	private static transient int seconds = 0;
	
	@Override
	public void run() 
	{
		seconds++;
		TipAPI.cycle();
	}
	
	public static int getSeconds()
	{
		return seconds;
	}

}
