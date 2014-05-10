package com.teaminfinity.exigencies.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public interface ExigenciesGUI extends Listener {

	public void show(Player player);
	public boolean isViewing(Player player);
	public void remove(Player player);
	
}
