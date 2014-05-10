package com.teaminfinity.exigencies.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import com.teaminfinity.exigencies.enums.ConfigVal;

public class TipAPI {

	private transient static int INTERVAL = 60;
	private transient static boolean ENABLED = false;
	private transient static List<String> TIPS = new ArrayList<>();
	
	private transient static int buffer = 0;
	private transient static int tipsBuffer = 0;
	
	public static void init()
	{
		INTERVAL = ConfigVal.TIPS_DELAY.getIntegerValue();
		ENABLED = ConfigVal.TIPS_ENABLED.getBooleanValue();
		TIPS = ConfigVal.TIPS_MESSAGES.getListStringValue();
	}
	
	public static void cycle()
	{
		if(!(ENABLED))
		{
			return;
		}
		buffer++;
		if(buffer >= INTERVAL)
		{
			buffer = 0;
			getNext();
			if(tipsBuffer == -1)
			{
				ENABLED = false;
				return;
			}
			broadcast();
		}
	}
	
	public static void broadcast()
	{
		if(ENABLED)
		{
			Bukkit.broadcastMessage(MessageAPI.addColour(TIPS.get(tipsBuffer)));
		}
	}
	
	public static int getNext()
	{
		if(TIPS.size() == 0)
		{
			return -1;
		}
		tipsBuffer++;
		if(tipsBuffer >= TIPS.size())
		{
			tipsBuffer = 0;
		}
		return tipsBuffer;
	}
	
}
