package com.teaminfinity.exigencies.api;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.teaminfinity.exigencies.utils.Files;

public abstract class IpAPI {

	public static void logIp(UUID id, String ip)
	{
		Files file = FileAPI.getFileForPlayer(id);
		file.loadFile();
		List<String> ips = file.getStringList("ips");
		if(ips == null)
		{
			ips = new ArrayList<String>();
		}
		if(ips.contains(ip))
		{
			return;
		}
		ips.add(ip);
		file.set("ips", ips);
		file.saveFile();
	}
	
	public static String getIp(InetAddress address)
	{
		return address.getHostAddress().toString();
	}
	
	public static String getIp(InetSocketAddress address)
	{
		return getIp(address.getAddress());
	}
	
}
