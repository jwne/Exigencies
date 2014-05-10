package com.teaminfinity.exigencies.objects.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class MobChain {

	public MobChain(String args, int amount)
	{
		this.amount = amount;
		String[] strMobs = args.split(",");
		List<EntityType> types = new ArrayList<EntityType>();
		for(String str : strMobs)
		{
			EntityType type = valueOf(str);
			if(type == null)
			{
				notFound.add(str);
				continue;
			}
			types.add(type);
		}
		isEmpty = types.isEmpty();
		entities = new EntityType[types.size()];
		int buffer = 0;
		for(EntityType type : types)
		{
			entities[buffer] = type;
			buffer++;
		}
	}
	
	private transient int amount;
	private transient EntityType[] entities;
	private transient List<String> notFound = new ArrayList<String>();
	private transient boolean isEmpty = false;
	
	public void spawn(Location location)
	{
		for(int i = 0; i < amount; i ++)
		{
			spawnChain(location);
		}
	}
	
	private void spawnChain(Location location)
	{
		LivingEntity latest = null;
		for(EntityType type : entities)
		{
			try
			{
				Entity entityNew = location.getWorld().spawnEntity(location, type);
				if(!(entityNew instanceof LivingEntity))
				{
					continue;
				}
				LivingEntity lentityNew = (LivingEntity) entityNew;
				if(latest != null)
				{
					latest.setPassenger(lentityNew);
				}
				latest = lentityNew;
			}
			catch(IllegalArgumentException e)
			{
			}
		}
	}
	
	private EntityType valueOf(String str)
	{
		for(EntityType type : EntityType.values())
		{
			if(type.toString().equalsIgnoreCase(str))
			{
				return type;
			}
			if(type.toString().replaceAll("_", "").equalsIgnoreCase(str))
			{
				return type;
			}
		}
		return null;
	}
	
	public List<String> getNotFound()
	{
		return notFound;
	}
	
	public boolean isEmpty()
	{
		return isEmpty;
	}
	
}
