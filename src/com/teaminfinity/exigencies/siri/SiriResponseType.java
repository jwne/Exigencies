package com.teaminfinity.exigencies.siri;

public enum SiriResponseType {
	
	HAVE_SEX_WITH_ME,
	PLAYER_GAY,
	TOTAL_PLAYERS,
	UNKNOWN;
	
	public String getMessage()
	{
		return SiriResponses.getMessage(this);
	}
	
}
