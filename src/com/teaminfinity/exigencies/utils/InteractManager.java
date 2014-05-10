package com.teaminfinity.exigencies.utils;

import org.bukkit.event.block.Action;

public class InteractManager {

	public static boolean isRightClick(Action action)
	{
		if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isLeftClick(Action action)
	{
		if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
