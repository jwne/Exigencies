package com.teaminfinity.exigencies.api;

import com.teaminfinity.exigencies.objects.persistence.PersistenceLocation;

public abstract class PersistenceAPI {

	public static PersistenceLocation getLocation()
	{
		return new PersistenceLocation();
	}
	
}
