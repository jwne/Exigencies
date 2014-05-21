package com.teaminfinity.exigencies.objects.exception;

@SuppressWarnings("serial")
public class WarpNotFoundException extends Exception {

	public WarpNotFoundException(String name)
	{
		this.name = name;
	}
	
	private transient final String name;
	
	public String getName()
	{
		return name;
	}
	
}
