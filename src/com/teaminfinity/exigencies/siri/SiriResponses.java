package com.teaminfinity.exigencies.siri;

import java.util.HashMap;

import org.bukkit.Bukkit;

import com.teaminfinity.exigencies.utils.RandomManager;

public abstract class SiriResponses {

	private transient static HashMap<SiriResponseType, String[]> messages
		= null;
	
	private static void populate()
	{
		messages = new HashMap<SiriResponseType, String[]>();
		messages.put(SiriResponseType.WOOD_CHUCK, new String[]
				{
				"3 woods.",
				"A woodchuck would chuck as much as a woodchuck could chuck"
				+ " if a woodchuck could chuck wood.",
				"What's a woodchuck?",
				}
		);
		messages.put(SiriResponseType.HAVE_SEX_WITH_ME, new String[]
				{
				"NO",
				"Twyzl didn't code me to..",
				"How?",
				"What the hell? No.",
				"You, me; dinner at 8."
				}
		);
		messages.put(SiriResponseType.UNKNOWN, new String[]
				{
				"What did you mean?",
				"Sorry, what was that?",
				"I didn't understand what you said...",
				"I don't know the answer to that.",
				}
		);
		messages.put(SiriResponseType.TOTAL_PLAYERS, new String[]
				{
				"There are %DATA% players online.",
				"%DATA% players happen to be online.",
				"%DATA% players are online.",
				"There are %DATA% players currently online.",
				"%DATA% players currently happen to be online.",
				"%DATA% players are currently online.",
				}
		);
		messages.put(SiriResponseType.PLAYER_GAY, new String[]
				{
				"That's for them to decide.",
				"I think so.",
				"Not sure.",
				"Only the closet will tell.",
				}
		);
	}
	
	public static String getMessage(SiriResponseType type)
	{
		if(messages == null)
		{
			populate();
		}
		String[] vals = messages.get(type);
		if(vals.length == 0)
		{
			return "";
		}
		String message = vals[RandomManager.nextInt(vals.length)];
		if(type == SiriResponseType.TOTAL_PLAYERS)
		{
			message = message.replaceAll("%DATA%", Bukkit.getOnlinePlayers().length + "");
		}
		return message;
	}
	
}
