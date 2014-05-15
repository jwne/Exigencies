package com.teaminfinity.exigencies.siri;

public enum SiriResponse {

	TOTAL_PLAYERS(SiriResponseType.TOTAL_PLAYERS, new SiriResponseCriteria(new String[][]
		{
			{
				"top", "play", "onli", "many"
			}
		}
	)),
	UNKNOWN(SiriResponseType.UNKNOWN, new SiriResponseCriteria(new String[][]{{}}));
	
	SiriResponse(SiriResponseType type, SiriResponseCriteria criteria)
	{
		this.type = type;
		this.criteria = criteria;
	}

	private transient final SiriResponseType type;
	private transient final SiriResponseCriteria criteria;
	
	public SiriResponseType getType() 
	{
		return type;
	}

	public SiriResponseCriteria getCriteria() 
	{
		return criteria;
	}
	
}
