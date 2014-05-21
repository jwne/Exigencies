package com.teaminfinity.exigencies.api;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;

import com.teaminfinity.exigencies.objects.F;
import com.teaminfinity.exigencies.objects.command.JailObject;

public abstract class JailAPI {

	private transient static List<JailObject> jails = new ArrayList<>();
	private transient static HashMap<UUID, String> jailedUsers = new HashMap<UUID, String>();
	
	public static Set<Entry<UUID, String>> getJailedPlayers()
	{
		return jailedUsers.entrySet();
	}
	
	public static void unjailAll(String name)
	{
		F file = FileAPI.getJailedUserFile();
		file.loadFile();
		List<String> remove = new ArrayList<>();
		List<String> jails = file.getStringList("list");
		for(String str : jails)
		{
			if(str.toLowerCase().contains(name.toLowerCase()))
			{
				remove.add(str);
			}
		}
		
		for(String str : remove)
		{
			jails.remove(str);
			jailedUsers.remove(UUID.fromString(str.split(":")[0]));
			file.set("list", jails);
			file.saveFile();
		}
	}
		
	public static void unjail(UUID user)
	{
		F file = FileAPI.getJailedUserFile();
		file.loadFile();
		String toRemove = null;
		List<String> jails = file.getStringList("list");
		for(String str : jails)
		{
			if(str.contains(user.toString()))
			{
				toRemove = str;
			}
		}
		if(toRemove != null)
		{
			jails.remove(toRemove);
			file.set("list", jails);
			file.saveFile();
		}
		jailedUsers.remove(user);
	}
	
	public static void jailUser(UUID user, String name)
	{
		jailedUsers.put(user, name);
		F file = FileAPI.getJailedUserFile();
		file.loadFile();
		List<String> jails = file.getStringList("list");
		jails.add(user.toString() + ":" + name);
		file.set("list", jails);
		file.saveFile();
	}
	
	public static JailObject getJail(UUID uuid)
	{
		String jailName = jailedUsers.get(uuid);
		for(JailObject jail : jails)
		{
			if(jail.getName().equalsIgnoreCase(jailName))
			{
				return jail;
			}
		}
		return null;
	}
	
	public static List<JailObject> getJails()
	{
		return jails;
	}
	
	public static boolean isJailed(UUID user)
	{
		return jailedUsers.containsKey(user);
	}
	
	public static void init()
	{
		initUsers();
		File dir = FileAPI.getJailFileDir();
		dir.mkdirs();
		String[] fileNames = dir.list();
		if(fileNames.length == 0)
		{
			return;
		}
		for(String fileName : fileNames)
		{
			F jailFile = FileAPI.getJailFile(fileName.replaceAll(".yml", ""));
			jailFile.loadFile();
			
			addJail(new JailObject(jailFile.getString("name"), 
					(Location) PersistenceAPI.getLocation()
					.fromStringValue(jailFile.getString("location"))));
		}
	}
	
	private static void initUsers()
	{
		F file = FileAPI.getJailedUserFile();
		if(!(file.fileExists()))
		{
			file.createFile();
			file.loadFile();
			file.set("list", new ArrayList<String>());
			file.saveFile();
		}
		file.loadFile();
		
		for(String user : file.getStringList("list"))
		{
			String[] data = user.split(":");
			UUID id = UUID.fromString(data[0]);
			jailedUsers.put(id, data[1]);
		}
	}
	
	public static JailObject get(String name)
	{
		for(JailObject jail : jails)
		{
			if(name.equalsIgnoreCase(jail.getName()))
			{
				return jail;
			}
		}
		return null;
	}
	
	public static void unload(String name)
	{
		JailObject remove = null;
		for(JailObject jail : jails)
		{
			if(name.equalsIgnoreCase(jail.getName()))
			{
				remove = jail;
			}
		}
		if(remove != null)
		{
			jails.remove(remove);
		}
	}
	
	public static boolean exists(String name)
	{
		for(JailObject jail : jails)
		{
			if(name.equalsIgnoreCase(jail.getName()))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void addJail(JailObject jail)
	{
		jails.add(jail);
	}
	
}
