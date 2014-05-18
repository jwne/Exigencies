package com.teaminfinity.exigencies.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Files {

	private static ArrayList<Files> loadedFiles = new ArrayList<Files>();
	
	private File folder;
	private File file;
	private YamlConfiguration config;
	
	public Files(File folder, String fileName)
	{
		this.folder = folder;
		this.file = new File(folder, fileName+".yml");
		this.config = new YamlConfiguration();
	}
	
	public Files(File folder, File file)
	{
		this.folder=folder;
		this.file = file;
		this.config = new YamlConfiguration();
	}
	
	public String getName()
	{
		return file.getName();
	}
		
	public boolean deleteFile()
	{
		file.delete();
		return true;
	}
	
	public Set<String> getKeys()
	{
		return config.getKeys(false);
	}
	
	public boolean createFile()
	{
		if(!(folder.exists()))
		{
			folder.mkdirs();
		}
		if(!(file.exists()))
		{
			try 
			{
				file.createNewFile();
				return true;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			return true;
		}
	}
	
	public boolean fileExists()
	{
		if(file.exists())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean loadFile()
	{
		if(Files.loadedFiles.contains(this))
		{
			return true;
		}
		try
		{
			config.load(file);
			return true;
		} 
		catch (FileNotFoundException e)
		{
			createFile();
			return true;
		}
		catch (IOException e) {
			createFile();
			return false;
		}
		catch (InvalidConfigurationException e) 
		{
			createFile();
			return false;
		}
	}
	
	public boolean saveFile()
	{
		try 
		{
			config.save(file);
			return true;
		} 
		catch (IOException e)
		{
			System.out.println("Failed to save file: "+file.getName());
			return false;
		}
	}
	
	public void set(String path, Object value)
	{
		config.set(path, value);
	}
	
	public int getInt(String path)
	{
		return config.getInt(path);
	}
	
	public String getString(String path)
	{
		return config.getString(path);
	}
	
	public long getLong(String path)
	{
		return config.getLong(path);
	}
	
	public boolean getBoolean(String path)
	{
		return config.getBoolean(path);
	}
	
	public List<String> getStringList(String path)
	{
		return config.getStringList(path);
	}
	
	public List<Integer> getIntList(String path)
	{
		return config.getIntegerList(path);
	}
	
	public float getFloat(String path)
	{
		return (float) getDouble(path);
	}
	
	public List<?> getList(String path)
	{
		return config.getList(path);
	}
	
	public double getDouble(String path)
	{
		return config.getDouble(path);
	}
}
