package com.teaminfinity.exigencies.objects.command;

import org.bukkit.Location;

import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.api.PersistenceAPI;
import com.teaminfinity.exigencies.objects.F;

public class JailObject {

	public JailObject(String name, Location loc)
	{
		this.setName(name);
		this.setLoc(loc);
	}

	private transient String name;
	private transient Location loc;
	
	public void save()
	{
		F file = FileAPI.getJailFile(name);
		if(file.fileExists())
		{
			file.deleteFile();
		}
		file.createFile();
		file.loadFile();
		file.set("name", name);
		file.set("location", PersistenceAPI.getLocation().toStringValue(loc));
		file.saveFile();
	}
	
	public F getFile()
	{
		return FileAPI.getJailFile(name);
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public Location getLoc()
	{
		return loc;
	}

	public void setLoc(Location loc) 
	{
		this.loc = loc;
	}
	
}
