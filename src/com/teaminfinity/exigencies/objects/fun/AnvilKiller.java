package com.teaminfinity.exigencies.objects.fun;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;

public class AnvilKiller implements AbuseTechnique {

	public void run(UUID sender, UUID reciever)
	{
		Player pSender = PlayerAPI.getPlayer(sender);
		Player pReciever = PlayerAPI.getPlayer(reciever);
		
		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}

		pReciever.getLocation().add(0, 30, 0).getBlock().setType(Material.ANVIL);
	}
	
}
