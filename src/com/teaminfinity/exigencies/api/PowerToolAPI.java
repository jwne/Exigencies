package com.teaminfinity.exigencies.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.teaminfinity.exigencies.objects.F;
import com.teaminfinity.exigencies.objects.command.PowerToolData;

public abstract class PowerToolAPI {

	private transient static HashMap<UUID, ArrayList<PowerToolData>> data = 
			new HashMap<>();
			
	public static void clear(UUID user)
	{
		if(!(data.containsKey(user)))
		{
			load(user);
		}
		F file = FileAPI.getFileForPlayer(user);
		file.set("powertooldata", new ArrayList<String>());
		file.saveFile();
		data.put(user, new ArrayList<PowerToolData>());
	}
	
	public static void remove(UUID user, ItemStack item)
	{
		if(!(data.containsKey(user)))
		{
			load(user);
		}
		PowerToolData remove = null;
		for(PowerToolData powertooldata : data.get(user))
		{
			if(powertooldata.getMaterial().equals(item.getType()))
			{
				if(powertooldata.getDurability() == item.getDurability())
				{
					remove = powertooldata;
				}
			}
		}
		
		if(remove != null)
		{
			data.get(user).remove(remove);
			save(user);
		}
	}
	
	public static void add(UUID user, String cmd, ItemStack item)
	{
		if(!(data.containsKey(user)))
		{
			load(user);
		}
		remove(user, item);
		data.get(user).add(new PowerToolData(item.getType(), item.getDurability(), cmd));
		save(user);
	}
	
	public static void save(UUID user)
	{
		if(!(data.containsKey(user)))
		{
			load(user);
		}
		ArrayList<PowerToolData> tooldata = data.get(user);
		if(tooldata.isEmpty())
		{
			clear(user);
		}

		ArrayList<String> list = new ArrayList<String>();
		for(PowerToolData data : tooldata)
		{
			list.add(data.getMaterial().toString() + "," + data.getDurability() + ","
					+ data.getCommand());
		}
		
		F file = FileAPI.getFileForPlayer(user);
		file.set("powertooldata", list);
		file.saveFile();
	}
			
	public static void unload(UUID user)
	{
		data.remove(user);
	}
	
	public static void load(UUID user)
	{
		load(user, FileAPI.getFileForPlayer(user));
	}
	
	public static void load(UUID user, F file)
	{
		file.loadFile();
		List<String> tooldata = file.getStringList("powertooldata");
		data.put(user, new ArrayList<PowerToolData>());
		if(tooldata.size() == 0)
		{
			return;
		}
		for(String sub : tooldata)
		{
			//3 max just incase cmd contains ,
			String[] subs = sub.split(",", 3);
			data.get(user).add(new PowerToolData(subs[0], Short.valueOf(subs[1]),
					subs[2]));
		}
	}
	
	public static PowerToolData get(UUID user, Material mat)
	{
		if(!(data.containsKey(user)))
		{
			load(user);
		}
		for(PowerToolData data : get(user))
		{
			if(data.getMaterial().equals(mat))
			{
				return data;
			}
		}
		return null;
	}
	
	public static boolean has(UUID user, Material mat)
	{
		return get(user, mat) != null;
	}
	
	public static ArrayList<PowerToolData> get(UUID user)
	{
		if(!(data.containsKey(user)))
		{
			load(user);
		}
		return data.get(user);
	}
	
}
