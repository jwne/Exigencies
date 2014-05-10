package com.teaminfinity.exigencies.enums;

public enum TempValueType {

	MUTE("tempmute.");
	
	TempValueType(String path)
	{
		this.path = path;
	}
	
	private transient String path;
	
	public String getPath()
	{
		return path;
	}
	
}
