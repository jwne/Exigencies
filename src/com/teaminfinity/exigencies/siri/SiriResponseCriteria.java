package com.teaminfinity.exigencies.siri;

import org.bukkit.ChatColor;

public class SiriResponseCriteria {

	private transient final int MATCHES_NEEDED = 2;
	
	public SiriResponseCriteria(String[][] data)
	{
		this.data = data;
	}

	private transient final String[][] data;
	
	public String[][] getData()
	{
		return data;
	}
	
	public boolean isMatch(String str)
	{
		String lower = str.toLowerCase();
		lower = ChatColor.stripColor(lower);
		for(String[] attributes : data)
		{
			int buffer = 0;
			for(String attribute : attributes)
			{
				if(lower.contains(attribute))
				{
					buffer++;
					if(buffer >= MATCHES_NEEDED)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
