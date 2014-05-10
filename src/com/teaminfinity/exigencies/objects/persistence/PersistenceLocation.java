package com.teaminfinity.exigencies.objects.persistence;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PersistenceLocation implements IPersistence {

	@Override
	public String toStringValue(Object obj)
	{
		Location loc = (Location) obj;
		StringBuilder builder = new StringBuilder();
		builder.append(loc.getX() + ",");
		builder.append(loc.getY() + ",");
		builder.append(loc.getZ() + ",");
		builder.append(loc.getWorld().getName() + ",");
		builder.append(loc.getYaw() + ",");
		builder.append(loc.getPitch() + "");
		return builder.toString();
	}

	@Override
	public Object fromStringValue(String str) 
	{
		String[] array = str.split(",");
		double x =  Double.parseDouble(array[0]);
		double y = Double.parseDouble(array[1]);
		double z = Double.parseDouble(array[2]);
		float yaw = Float.parseFloat(array[4]);
		float pitch = Float.parseFloat(array[5]);
		Location location = new Location(Bukkit.getWorld(array[3]), x, y, z);
		location.setYaw(yaw);
		location.setPitch(pitch);
		return location;
	}

}
