package com.teaminfinity.exigencies.api;

import com.teaminfinity.exigencies.enums.TimeFrame;

public abstract class TimeFrameAPI {

	public static long getCurrentTime(){
		return System.currentTimeMillis();
	}
	
	public static String getTimeLeftString(int timeLeft)
	{
		long minutes = timeLeft/60; //automatically cast to int 
		long seconds = timeLeft-minutes*60; //subtract off whole minutes 
		
		long hours = minutes/60;
		minutes = minutes-hours*60;
		long days = hours/24;
		hours = hours - days*24;
		long months = days/28;
		days = days - months*28;
		long years = months/12;
		months = months - years*12;
		
		
		String stringBuilder = "";
		if(years!=0)
		{
			if(years!=1)
			{
				stringBuilder=stringBuilder+years+" years ";
			}
			else
			{
				stringBuilder=stringBuilder+years+" year ";
			}
		}
		if(months!=0)
		{
			if(months!=1)
			{
				stringBuilder=stringBuilder+months+" months ";
			}
			else
			{
				stringBuilder=stringBuilder+months+" month ";
			}
		}
		if(days!=0)
		{
			if(days!=1)
			{
				stringBuilder=stringBuilder+days+" days ";
			}
			else
			{
				stringBuilder=stringBuilder+days+" day ";
			}
		}
		if(hours!=0)
		{
			if(hours!=1)
			{
				stringBuilder=stringBuilder+hours+" hours ";
			}
			else
			{
				stringBuilder=stringBuilder+hours+" hour ";
			}
		}
		if(minutes!=0)
		{
			if(minutes!=1)
			{
				stringBuilder=stringBuilder+minutes+" minutes ";
			}
			else
			{
				stringBuilder=stringBuilder+minutes+" minute ";
			}
		}
		if(seconds!=0)
		{
			if(seconds!=1)
			{
			stringBuilder=stringBuilder+seconds+" seconds";
			}
			else
			{
				stringBuilder=stringBuilder+seconds+" second";
			}
		}
		return stringBuilder;
	}
	
	public static long getTargetDate(int seconds)
	{
		long current = getCurrentTime();
		long diff = seconds*1000;
		long newTime = current+diff;
		return newTime;
	}
	
	public static long getTargetDate(TimeFrame tf, int amount)
	{
		long current = getCurrentTime();
		long diff = tf.getAmount()*amount;
		diff *=1000;
		long newTime = current+diff;
		System.out.println(current+"");
		return newTime;
	}
	
	public static long getTimeLeft(long targetDate){
		long msLeft = targetDate-getCurrentTime();
		long secondsLeft = msLeft/1000;
		return secondsLeft; 
	}
	
}
