package com.teaminfinity.exigencies.enums;

import org.bukkit.command.CommandSender;

public enum Perm {

	ALL("'*'"),
	
	EXIGENCIES_ALL("exigencies.*"),

	COLOR_SIGN("exigencies.color.sign"),
	COLOR_CHAT("exigencies.color.chat"),
	
	COMMAND_WEATHER("exigencies.weather"),
	COMMAND_POWERTOOL("exigencies.powertool"),
	COMMAND_TIME("exigencies.time"),
	COMMAND_DOS("exigencies.dos"),
	COMMAND_WARPS("exigencies.warps"),
	COMMAND_SHEEPCANNON("exigencies.sheepcannon"),
	COMMAND_SPAWN_INSTANT("exigencies.spawn.instant"),
	COMMAND_SPAWN("exigencies.spawn"),
	COMMAND_SETSPAWN("exigencies.setspawn"),
	COMMAND_JAILS("exigencies.jails"),
	COMMAND_UNJAIL("exigencies.unjail"),
	COMMAND_JAIL("exigencies.jail"),
	COMMAND_DELJAIL("exigencies.deljail"),
	COMMAND_SETJAIL("exigencies.setjail"),
	COMMAND_ECHEST("exigencies.echest"),
	COMMAND_INVSEE("exigencies.invsee"),
	COMMAND_ROLL("exigencies.roll"),
	COMMAND_KILLALL("exigencies.killall"),
	COMMAND_SPAWNMOB("exigencies.spawnmob"),
	COMMAND_ABUSE("exigencies.abuse"),
	COMMAND_LORE("exigencies.lore"),
	COMMAND_RENAME("exigencies.rename"),
	COMMAND_SPAM("exigencies.spam"),
	COMMAND_PARTICLEFFECT("exigencies.particleeffect"),
	COMMAND_HEAL("exigencies.heal"),
	COMMAND_ANVIL("exigencies.anvil"),
	COMMAND_I("exigencies.i"),
	COMMAND_UNMUTE("exigencies.unmute"),
	COMMAND_SIGN("exigencies.sign"),
	COMMAND_TP("exigencies.tp"),
	COMMAND_BANIP("exigencies.banip"),
	COMMAND_WHOIS("exigencies.whois"),
	COMMAND_CHECK("exigencies.check"),
	COMMAND_UNBANIP("exigencies.unbanip"),
	COMMAND_TEMPMUTE("exigencies.tempmute"),
	COMMAND_MUTE("exigencies.mute"),
	COMMAND_FLY("exigencies.fly"),
	COMMAND_R("exigencies.msg.reply"),
	COMMAND_MSG("exigencies.msg"),
	COMMAND_UNBAN("exigencies.unban"),
	COMMAND_GAMEMODE("exigencies.gamemode"),
	COMMAND_DELWARP("exigencies.warp.delete"),
	COMMAND_WARP("exigencies.warp"),
	COMMAND_SETWARP("exigencies.warp.set"),
	COMMAND_LAG("exigencies.lag"),
	COMMAND_CLEARDROPS("exigencies.cleardrops"),
	COMMAND_AFK("exigencies.afk"),
	COMMAND_SPAWNER("exigencies.spawner"),
	COMMAND_BAN("exigencies.ban"),
	COMMAND_TEMPBAN("exigencies.tempban");

	Perm(String node)
	{
		this.node = node;
	}

	public final String node;

	public String getNode()
	{
		return node;
	}

	public boolean hasPermission(CommandSender user)
	{
		if(getNode().equals(""))
		{
			return true;
		}
		if(user.isOp())
		{
			return true;
		}
		if(user.hasPermission(node))
		{
			return true;
		}
		if(user.hasPermission(ALL.getNode()))
		{
			return true;
		}
		if(user.hasPermission(EXIGENCIES_ALL.getNode()))
		{
			return true;
		}
		return false;
	}
	
}
