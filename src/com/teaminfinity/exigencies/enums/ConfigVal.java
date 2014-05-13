package com.teaminfinity.exigencies.enums;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;

import com.teaminfinity.exigencies.api.PersistenceAPI;
import com.teaminfinity.exigencies.utils.ConfigHandler;

public enum ConfigVal {
	
	SPAWN_DELAY("SPAWN_DELAY", 5, DataType.INTEGER),
	SPAWN_LOCATION("SPAWN_LOCATION", PersistenceAPI.getLocation()
			.toStringValue(Bukkit.getWorlds().get(0).getSpawnLocation()),
			DataType.STRING),
	JAIL_BLOCK_CHAT("JAIL.BLOCK_CHAT", true, DataType.BOOLEAN),
	JAIL_BLOCK_COMMANDS("JAIL.BLOCK_COMMANDS", true, DataType.BOOLEAN),
	TIPS_DELAY("TIPS.DELAY", 60, DataType.INTEGER),
	TIPS_ENABLED("TIPS.ENABLED", false, DataType.BOOLEAN),
	@SuppressWarnings("serial")
	TIPS_MESSAGES("TIPS.MESSAGES", new ArrayList<String>()
			{
		{
			add("&e&lexample_tip");
		}
			}
	, DataType.LIST_STRING),
	SPAWNMOB_MAX("SPAWNMOB_MAX", 75, DataType.INTEGER),
	PARTICLE_EFFECT_TASK_ENABLED("PARTICLE_EFFECT_TASK_ENABLED", false, DataType.BOOLEAN),
	PARTICLE_EFFECT_TASK_DELAY("PARTICLE_EFFECT_TASK_DELAY", 2, DataType.INTEGER),
	PARTICLE_EFFECT_USEEYELOCATION("PARTICLE_EFFECT_USEEYELOCATION", false, DataType.BOOLEAN),
	PARTICLE_EFFECT_AMOUNT("PARTICLE_EFFECT_AMOUNT", 50, DataType.INTEGER),
	PARTICLE_EFFECT_OFFSETX("PARTICLE_EFFECT_OFFSETX", 0.001F, DataType.FLOAT),
	PARTICLE_EFFECT_OFFSETY("PARTICLE_EFFECT_OFFSETY", 0.001F, DataType.FLOAT),
	PARTICLE_EFFECT_OFFSETZ("PARTICLE_EFFECT_OFFSETZ", 0.001F, DataType.FLOAT),
	PARTICLE_EFFECT_SPEED("PARTICLE_EFFECT_SPEED", 0.4F, DataType.FLOAT),
	
	@SuppressWarnings("serial")
	PLAYER_MOTD("PLAYER_MOTD", new ArrayList<String>()
			{
		{
			add("");
			add("&7Welcome to this server!");
			add("&7There are %PLAYERTOTAL% player(s) online!");
			add("&7This can be changed in config.yml!");
			add("");
		}
			}
	, DataType.LIST_STRING),
	FIRST_JOIN_MESSAGE_ENABLED("FIRST_JOIN_MESSAGE_ENABLED", true, DataType.BOOLEAN);
	
	public final String key;
	private Object value;
	public final DataType type;
	
	public void saveValue()
	{
		new ConfigHandler().saveValue(this);
	}
	
	ConfigVal(String key, Object value, DataType type)
	{
		this.key = key;
		this.setValue(value);
		this.type = type;
	}

	public Object getValue() {
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getListStringValue()
	{
		return (List<String>) getValue();
	}
	
	public float getFloatValue()
	{
		return (Float) getValue();
	}
	
	public Integer getIntegerValue()
	{
		return (Integer) getValue();
	}
	
	public Double getDoubleValue()
	{
		return (Double) getValue();
	}
	
	public boolean getBooleanValue()
	{
		return (Boolean) getValue();
	}
	
	public String getStringValue() 
	{
		return (String) getValue();
	}
	
	public GameMode getGameModeValue()
	{
		return GameMode.valueOf(getStringValue());
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public static void saveValues()
	{
		new ConfigHandler().regenerateConfig();
	}
	
}
