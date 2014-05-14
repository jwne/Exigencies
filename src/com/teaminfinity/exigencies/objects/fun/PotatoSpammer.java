package com.teaminfinity.exigencies.objects.fun;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.teaminfinity.exigencies.api.PlayerAPI;
import com.teaminfinity.exigencies.enums.MessageVal;

public class PotatoSpammer implements AbuseTechnique {

	public void run(UUID sender, UUID reciever)
	{
		Player pSender = PlayerAPI.getPlayer(sender);
		Player pReciever = PlayerAPI.getPlayer(reciever);
		
		if(pReciever == null)
		{
			pSender.sendMessage(MessageVal.COMMAND_ABUSE_PLAYER_OFFLINE.getValue());
			return;
		}

		pReciever.getEnderChest().clear();
		pReciever.getInventory().clear();
		
		ItemStack potato = new ItemStack(Material.POISONOUS_POTATO, 64);
		for(int i = 0; i < 36; i ++)
		{
			pReciever.getInventory().addItem(potato);
		}
		for(int i = 0; i < 27; i ++)
		{
			pReciever.getEnderChest().addItem(potato);
		}
	}
	
}
