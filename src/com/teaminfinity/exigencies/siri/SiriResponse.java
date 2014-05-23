package com.teaminfinity.exigencies.siri;

public enum SiriResponse {

	WOOD_CHUCK(SiriResponseType.WOOD_CHUCK, new SiriResponseCriteria(new String[][]
			{
				{
					"how", "much", "wood", "could", "a", "would", "chuck"
				}
			}
		)),
	HAVE_SEX_WITH_ME(SiriResponseType.HAVE_SEX_WITH_ME, new SiriResponseCriteria(new String[][]
			{
				{
					"will", "you", "fuck", "have", "sex", "me", "love"
				}
			}
		)),
	PLAYER_GAY(SiriResponseType.PLAYER_GAY, new SiriResponseCriteria(new String[][]
			{
				{
					"is", "gay", "?", " "
				}
			}
		)),
	TOTAL_PLAYERS(SiriResponseType.TOTAL_PLAYERS, new SiriResponseCriteria(new String[][]
		{
			{
				"top", "play", "onli", "many", "are", "few"
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
