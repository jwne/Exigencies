package com.teaminfinity.exigencies;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import com.teaminfinity.exigencies.api.JailAPI;
import com.teaminfinity.exigencies.api.ParticleEffectAPI;
import com.teaminfinity.exigencies.api.TemporaryAPI;
import com.teaminfinity.exigencies.api.TipAPI;
import com.teaminfinity.exigencies.commands.CommandAbuse;
import com.teaminfinity.exigencies.commands.CommandAnvil;
import com.teaminfinity.exigencies.commands.CommandBan;
import com.teaminfinity.exigencies.commands.CommandCheck;
import com.teaminfinity.exigencies.commands.CommandDeljail;
import com.teaminfinity.exigencies.commands.CommandEchest;
import com.teaminfinity.exigencies.commands.CommandGamemode;
import com.teaminfinity.exigencies.commands.CommandGm;
import com.teaminfinity.exigencies.commands.CommandHeal;
import com.teaminfinity.exigencies.commands.CommandI;
import com.teaminfinity.exigencies.commands.CommandInvsee;
import com.teaminfinity.exigencies.commands.CommandJail;
import com.teaminfinity.exigencies.commands.CommandJails;
import com.teaminfinity.exigencies.commands.CommandKillall;
import com.teaminfinity.exigencies.commands.CommandLag;
import com.teaminfinity.exigencies.commands.CommandLore;
import com.teaminfinity.exigencies.commands.CommandParticleeffect;
import com.teaminfinity.exigencies.commands.CommandPe;
import com.teaminfinity.exigencies.commands.CommandRename;
import com.teaminfinity.exigencies.commands.CommandRoll;
import com.teaminfinity.exigencies.commands.CommandSetjail;
import com.teaminfinity.exigencies.commands.CommandSetspawn;
import com.teaminfinity.exigencies.commands.CommandSpam;
import com.teaminfinity.exigencies.commands.CommandSpawn;
import com.teaminfinity.exigencies.commands.CommandSpawnmob;
import com.teaminfinity.exigencies.commands.CommandTp;
import com.teaminfinity.exigencies.commands.CommandTpall;
import com.teaminfinity.exigencies.commands.CommandUnjail;
import com.teaminfinity.exigencies.commands.CommandWhois;
import com.teaminfinity.exigencies.enums.ConfigVal;
import com.teaminfinity.exigencies.gui.ExigenciesGUI;
import com.teaminfinity.exigencies.gui.GUIHandler;
import com.teaminfinity.exigencies.listeners.ChatListener;
import com.teaminfinity.exigencies.listeners.JailListener;
import com.teaminfinity.exigencies.listeners.MotdListener;
import com.teaminfinity.exigencies.listeners.PlayerListener;
import com.teaminfinity.exigencies.objects.MovementStopper;
import com.teaminfinity.exigencies.objects.command.ExigenciesCommand;
import com.teaminfinity.exigencies.tasks.GeneralTask;
import com.teaminfinity.exigencies.tasks.ParticleEffectTask;
import com.teaminfinity.exigencies.utils.ConfigHandler;
import com.teaminfinity.exigencies.utils.MessageHandler;
import com.teaminfinity.exigencies.utils.SchedulingUtility;

public class Registry {

	public Listener[] listeners = new Listener[]
			{
			new PlayerListener(),
			new MotdListener(),
			new ChatListener(),
			new JailListener(),
			new MovementStopper()
			}
	;
	
	public ExigenciesCommand[] commands = new ExigenciesCommand[]
			{
			new CommandGm(),
			new CommandGamemode(),
			new CommandAnvil(),
			new CommandHeal(),
			new CommandSpam(),
			new CommandRename(),
			new CommandLore(),
			new CommandWhois(),
			new CommandLag(),
			new CommandAbuse(),
			new CommandSpawnmob(),
			new CommandParticleeffect(),
			new CommandPe(),
			new CommandI(),
			new CommandKillall(),
			new CommandRoll(),
			new CommandCheck(),
			new CommandInvsee(),
			new CommandEchest(),
			new CommandBan(),
			new CommandTp(),
			new CommandTpall(),
			new CommandSetjail(),
			new CommandDeljail(),
			new CommandJail(),
			new CommandUnjail(),
			new CommandJails(),
			new CommandSetspawn(),
			new CommandSpawn()
			}
	;
	
	public Registry()
	{
		new ConfigHandler().loadAllValues();;
		new MessageHandler();
		
		PluginManager pm = Bukkit.getPluginManager();
		
		for(Listener listenerClass:listeners)
		{
			pm.registerEvents(listenerClass, Core.instance);
		}
		for(ExigenciesGUI gui : GUIHandler.getAll())
		{

			pm.registerEvents(gui, Core.instance);
		}
		
		try 
		{
			Metrics metrics = new Metrics(Core.instance);
			metrics.start();
		} 
		catch (IOException localIOException)
		{
			/**
			 * Metrics didn't enable.
			 * Oops.
			 */
		}
		
		for(ExigenciesCommand cmd : commands)
		{
			Core.instance.getCommand(cmd.getAlias()).setExecutor((CommandExecutor) cmd);
			if(cmd instanceof Listener)
			{
				pm.registerEvents((Listener) cmd, Core.instance);
			}
		}
		
		
		if(ConfigVal.PARTICLE_EFFECT_TASK_ENABLED.getBooleanValue())
		{
			ParticleEffectTask.init();
			ParticleEffectAPI.init();
			SchedulingUtility.beginRepeating(new ParticleEffectTask(), ConfigVal.PARTICLE_EFFECT_TASK_DELAY
					.getIntegerValue());
		}
		
		SchedulingUtility.beginRepeating(new GeneralTask(20), 20);
		TemporaryAPI.init();
		TipAPI.init();
		JailAPI.init();
	}
	
}
