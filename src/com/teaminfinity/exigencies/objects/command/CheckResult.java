package com.teaminfinity.exigencies.objects.command;

import java.util.ArrayList;
import java.util.List;

public class CheckResult {

	private transient List<String> ips = new ArrayList<String>();
	private transient List<String> ids = new ArrayList<String>();
	private transient List<String> names = new ArrayList<String>();
	
	public List<String> getIds()
	{
		return ids;
	}
	
	public List<String> getIps()
	{
		return ips;
	}
	
	public List<String> getNames()
	{
		return names;
	}
	
	public void addIp(String ip)
	{
		if(ips.contains(ip))
		{
			return;
		}
		ips.add(ip);
	}
	
	public void addId(String id)
	{
		if(ids.contains(id))
		{
			return;
		}
		ids.add(id);
	}
	
	public void addName(String name)
	{
		if(names.contains(name))
		{
			return;
		}
		names.add(name);
	}
	
	public boolean containsIp(String ip)
	{
		return ips.contains(ip);
	}
	
	public boolean containsId(String id)
	{
		return ids.contains(id);
	}
	
	public boolean containsName(String name)
	{
		return names.contains(name);
	}
	
}
