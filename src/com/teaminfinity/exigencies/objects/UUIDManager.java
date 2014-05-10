package com.teaminfinity.exigencies.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.teaminfinity.exigencies.api.FileAPI;
import com.teaminfinity.exigencies.enums.UUIDManagementType;
import com.teaminfinity.exigencies.utils.Files;

public class UUIDManager {

	public UUIDManager(UUID id, String name, UUIDManagementType type)
	{
		if(type.equals(UUIDManagementType.PLAYER))
		{
			Files file = FileAPI.getFileForPlayer(id);
			file.loadFile();
			List<String> accounts = file.getStringList("used_names");
			if(accounts == null)
			{
				accounts = new ArrayList<String>();
			}
			if(accounts.contains(name.toLowerCase()))
			{
				return;
			}
			accounts.add(name.toLowerCase());
			file.set("used_names", accounts);
			file.saveFile();
		}
		else if(type.equals(UUIDManagementType.DATABASE))
		{
			Files database = FileAPI.getUUIDDatabaseFile();
			if(!(database.fileExists()))
			{
				database.createFile();
			}
			database.loadFile();
			database.set(name.toLowerCase(), id.toString());
			database.set(id.toString(), name);
			database.saveFile();
		}
		else if(type.equals(UUIDManagementType.BOTH))
		{
			Files database = FileAPI.getUUIDDatabaseFile();
			if(!(database.fileExists()))
			{
				database.createFile();
			}
			database.loadFile();
			database.set(name.toLowerCase(), id.toString());
			database.set(id.toString(), name);
			database.saveFile();
			Files file = FileAPI.getFileForPlayer(id);
			file.loadFile();
			List<String> accounts = file.getStringList("used_names");
			if(accounts == null)
			{
				accounts = new ArrayList<String>();
			}
			if(accounts.contains(name.toLowerCase()))
			{
				return;
			}
			accounts.add(name.toLowerCase());
			file.set("used_names", accounts);
			file.saveFile();
		}
	}
	
}
