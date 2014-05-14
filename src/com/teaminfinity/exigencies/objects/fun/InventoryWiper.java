package com.teaminfinity.exigencies.objects.fun;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;

public class InventoryWiper implements AbuseTechnique {

	public void run(UUID sender, UUID reciever)
	{
		Player pSender = PlayerAPI.getPlayer(sender);
		Player pReciever = PlayerAPI.getPlayer(reciever);
		
		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}
		
		pReciever.getInventory().clear();
		pReciever.getEquipment().setArmorContents(new ItemStack[]
				{
				null,
				null,
				null,
				null
				}
		);
	}
	
}
