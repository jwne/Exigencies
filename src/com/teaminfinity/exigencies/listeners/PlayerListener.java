package com.teaminfinity.exigencies.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import com.teaminfinity.exigencies.Core;
import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.api.IpAPI;
import com.teaminfinity.exigencies.api.MessageAPI;
import com.teaminfinity.exigencies.api.PersistenceAPI;
import com.teaminfinity.exigencies.api.PowerToolAPI;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.enums.MessageVal;
import com.teaminfinity.exigencies.enums.Perm;
import com.teaminfinity.exigencies.enums.UUIDManagementType;
import com.teaminfinity.exigencies.objects.F;
import com.teaminfinity.exigencies.objects.UUIDManager;
import com.teaminfinity.exigencies.objects.command.PowerToolData;
import com.teaminfinity.exigencies.objects.events.PlayerFirstJoinEvent;
import com.teaminfinity.exigencies.objects.events.PlayerLeaveEvent;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerFirstJoin(PlayerFirstJoinEvent e)
	{
		F file = FileAPI.getFileForPlayer(e.getPlayer());
		file.createFile();
		file.loadFile();
		file.set("first_join", System.currentTimeMillis());
		file.set("powertooldata", new ArrayList<String>());
		file.saveFile();
		if(ConfigVal.FIRST_JOIN_MESSAGE_ENABLED.getBooleanValue())
		{
			Bukkit.broadcastMessage(MessageAPI.getReformat(MessageVal.FIRST_LOGIN_MESSAGE, e.getPlayer()));
		}
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerTeleport(PlayerTeleportEvent e)
	{
		if(e.isCancelled())
		{
			return;
		}
		if(e.getCause().equals(TeleportCause.COMMAND) 
				|| e.getCause().equals(TeleportCause.UNKNOWN)
				|| e.getCause().equals(TeleportCause.PLUGIN))
		{
			F file = FileAPI.getFileForPlayer(e.getPlayer());
			file.loadFile();
			file.set("last_tp", PersistenceAPI.getLocation().toStringValue(e.getFrom()));
			file.saveFile();
		}
	}
	
	@EventHandler
	public void onPowerToolUse(PlayerInteractEvent e)
	{
		if(e.getPlayer().getItemInHand() == null)
		{
			return;
		}
		PowerToolData data = PowerToolAPI.get(e.getPlayer().getUniqueId(),
				e.getPlayer().getItemInHand().getType());
		if(data == null)
		{
			return;
		}
		data.run(e.getPlayer());
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerLeaveEvent e)
	{
		PowerToolAPI.unload(e.getPlayer().getUniqueId());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		Bukkit.getPluginManager().callEvent(new PlayerLeaveEvent(e.getPlayer()));
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e)
	{
		Bukkit.getPluginManager().callEvent(new PlayerLeaveEvent(e.getPlayer()));
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		if(Perm.ALL.hasPermission(e.getPlayer()))
		{
			e.getPlayer().sendMessage(MessageAPI.getReformat(MessageVal.JOIN_NOTIFY_MESSAGE,
					Core.instance.getDescription().getVersion()));
		}
		F file = FileAPI.getFileForPlayer(e.getPlayer());
		if(!(file.fileExists()))
		{
			Bukkit.getPluginManager().callEvent(new PlayerFirstJoinEvent(e));
		}

		/**
		 * The reason for this is because 1.7.5 is when the update to 
		 * switch from UUID's came out, therefore anything below this needs to be
		 * cancelled.
		 */
		if(Core.instance.getServer().getVersion().contains("1.7.2"))
		{
			IpAPI.logIp(e.getPlayer().getUniqueId(), IpAPI.getIp(e.getPlayer().getAddress()));
			new UUIDManager(e.getPlayer().getUniqueId(), e.getPlayer().getName(),
					UUIDManagementType.BOTH);
		}
	}
	
	@EventHandler
	public void onPrePlayerLogin(AsyncPlayerPreLoginEvent e)
	{
		 /*
		  * The reason for this is because 1.7.5 is when the update to 
		  * switch from UUID's came out, therefore anything below this 
		  * needs to be cancelled.
		  */
		if(!(Core.instance.getServer().getVersion().contains("1.7.2")))
		{
			IpAPI.logIp(e.getUniqueId(), IpAPI.getIp(e.getAddress()));
			new UUIDManager(e.getUniqueId(), e.getName(),
					UUIDManagementType.BOTH);
		}
	}
	
	public static class ColorListener implements Listener {
		
		@EventHandler
		public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
		{
			if(Perm.COLOR_CHAT.hasPermission(e.getPlayer()))
			{
				e.setMessage(MessageAPI.addColour(e.getMessage()));
			}
		}
		
	    @EventHandler
	    public void onSignChange(SignChangeEvent e)
	    {
	    	if(Perm.COLOR_SIGN.hasPermission(e.getPlayer()))
	    	{
	    		int index = 0;
	    		for(String str : e.getLines())
	    		{
	    			e.setLine(index, MessageAPI.addColour(str));
	    			index++;
	    		}
	    	}
	    }
		
	}
	
	
}
