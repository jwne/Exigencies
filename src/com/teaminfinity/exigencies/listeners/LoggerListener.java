package com.teaminfinity.exigencies.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.teaminfinity.exigencies.api.LogAPI;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.LogType;

public class LoggerListener implements Listener {

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e)
	{
		if(ConfigVal.EXIGENCIES_LOGGER_COMMANDS_ENABLED.getBooleanValue())
		{
			LogAPI.set(e.getPlayer().getName() + "(" + e.getPlayer().getUniqueId().toString() + ") "
					+ "ran command: " + e.getMessage(), LogType.COMMAND);
		}
	}
	
}
