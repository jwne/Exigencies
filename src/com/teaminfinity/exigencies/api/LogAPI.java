package com.teaminfinity.exigencies.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;

import com.teaminfinity.exigencies.enums.LogType;
import com.teaminfinity.exigencies.objects.F;

public class LogAPI {

	private transient static HashMap<LogType, F> instances
		= new HashMap<>();
	
	public static void init()
	{
		for(LogType type : LogType.values())
		{
			instances.put(type, FileAPI.getLogFile(type));
		}
	
		for(Entry<LogType, F> entry : instances.entrySet())
		{
			if(!(entry.getValue().fileExists()))
			{
				entry.getValue().createFile();
			}
			entry.getValue().loadFile();
		}
	}
	
	public static void set(String value, LogType type)
	{
		instances.get(type).set(getTime(), value);
		instances.get(type).saveFile();
	}

    public static String getTime() 
	{
    	Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY:DD:HH:mm:ss");
    	return sdf.format(cal.getTime());
    }
	
}
