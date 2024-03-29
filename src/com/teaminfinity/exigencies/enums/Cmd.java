package com.teaminfinity.exigencies.enums;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum Cmd {

	/**
	 * Exigencies 'Core' commands
	 */
	COMMAND_ADMINCHAT(Perm.COMMAND_ADMINCHAT, ChatColor.RED + "Please try: '/AdminChat [Message]'"),
	COMMAND_BACK(Perm.COMMAND_BACK, ChatColor.RED + "Please try: '/Back'"),
	COMMAND_RESETCHUNK(Perm.COMMAND_RESETCHUNK, ChatColor.RED + "Please try: '/Resetchunk'"),
	COMMAND_REAPER(Perm.COMMAND_REAPER, ChatColor.RED + "Please try: '/Reaper'"),
	COMMAND_RIDE(Perm.COMMAND_RIDE, ChatColor.RED + "Please try: '/Ride <Player>'"),
	COMMAND_WITHERSKULL(Perm.COMMAND_WITHERSKULL, ChatColor.RED + "Please try: '/Witherskull'"),
	COMMAND_EX(Perm.COMMAND_EX, ChatColor.RED + "Please try: '/Ex Help'"),
	COMMAND_KICK(Perm.COMMAND_KICK, ChatColor.RED + "Please try: '/Kick <Player>'"),
	COMMAND_SUN(Perm.COMMAND_WEATHER, ChatColor.RED + "Please try: '/Sun'"),
	COMMAND_POWERTOOL(Perm.COMMAND_POWERTOOL, ChatColor.RED + "Please try: '/Powertool <Clear:Command:Remove:List>'"),
	COMMAND_NIGHT(Perm.COMMAND_TIME, ChatColor.RED + "Please try: '/Night'"),
	COMMAND_DAY(Perm.COMMAND_TIME, ChatColor.RED + "Please try: '/Day'"),
	COMMAND_DOS(Perm.COMMAND_DOS, ChatColor.RED + "Please try: '/Dos Target'"),
	COMMAND_WARPS(Perm.COMMAND_WARPS, ChatColor.RED + "Please try: '/Warps'"),
	COMMAND_SHEEPCANNON(Perm.COMMAND_SHEEPCANNON, ChatColor.RED + "Please try: '/Sheepcannon'"),
	COMMAND_SPAWN(Perm.COMMAND_SPAWN, ChatColor.RED + "Please try: '/Spawn'"),
	COMMAND_SETSPAWN(Perm.COMMAND_SETSPAWN, ChatColor.RED + "Please try: '/Setspawn'"),
	COMMAND_JAILS(Perm.COMMAND_JAILS, ChatColor.RED + "Please try: '/Jails'"),
	COMMAND_UNJAIL(Perm.COMMAND_UNJAIL, ChatColor.RED + "Please try: '/Unjail <Player>'"),
	COMMAND_JAIL(Perm.COMMAND_JAIL, ChatColor.RED + "Please try: '/Jail <Player> <Jail>'"),
	COMMAND_DELJAIL(Perm.COMMAND_DELJAIL, ChatColor.RED + "Please try: '/Deljail <Name>'"),
	COMMAND_SETJAIL(Perm.COMMAND_SETJAIL, ChatColor.RED + "Please try: '/Setjail <Name>'"),
	COMMAND_TPALL(Perm.COMMAND_TP, ChatColor.RED + "Please try: '/Tpall'"),
	COMMAND_ECHEST(Perm.COMMAND_ECHEST, ChatColor.RED + "Please try: '/Echest <Player>'"),
	COMMAND_INVSEE(Perm.COMMAND_INVSEE, ChatColor.RED + "Please try: '/Invsee <Player>'"),
	COMMAND_ROLL(Perm.COMMAND_ROLL, ChatColor.RED + "Please try: '/Roll [Max]'"),
	COMMAND_KILLALL(Perm.COMMAND_KILLALL, ChatColor.RED + "Please try: '/Killall'"),
	COMMAND_SPAWNMOB(Perm.COMMAND_SPAWNMOB, ChatColor.RED + "Please try: '/Spawnmob <Chain> [Amount]"),
	COMMAND_ABUSE(Perm.COMMAND_ABUSE, ChatColor.RED + "Please try: '/Abuse <Player>'"),
	COMMAND_LORE(Perm.COMMAND_LORE, ChatColor.RED + "Please try: '/Lore <Args>'"),
	COMMAND_RENAME(Perm.COMMAND_RENAME, ChatColor.RED + "Please try: '/Rename <Name>'"),
	COMMAND_SPAM(Perm.COMMAND_SPAM, ChatColor.RED + "Please try: '/Spam <Amount> <Args>'"),
	COMMAND_PARTICLEFFECT(Perm.COMMAND_PARTICLEFFECT, ChatColor.RED + "Please try: '/ParticleEffect <Type> [Name]"),
	COMMAND_HEAL(Perm.COMMAND_HEAL, ChatColor.RED + "Please try: '/Heal [Player]'"),
	COMMAND_ANVIL(Perm.COMMAND_ANVIL, ChatColor.RED + "Please try: '/Anvil'"),
	COMMAND_I(Perm.COMMAND_I, ChatColor.RED + "Please try: '/I <Item> [Amount]'"),
	COMMAND_UNMUTE(Perm.COMMAND_UNMUTE, ChatColor.RED + "Please try: '/Unmute <Player>'"),
	COMMAND_SIGN(Perm.COMMAND_SIGN, ChatColor.RED + "Please try: '/Sign <Line> [Text]'"),
	COMMAND_TP(Perm.COMMAND_TP, ChatColor.RED + "Please try: '/Tp <Player> [Player]'"),
	COMMAND_BANIP(Perm.COMMAND_BANIP, ChatColor.RED + "Please try: '/BanIP <IP>'"),
	COMMAND_WHOIS(Perm.COMMAND_WHOIS, ChatColor.RED + "Please try: '/Whois <Player>'"),
	COMMAND_CHECK(Perm.COMMAND_CHECK, ChatColor.RED + "Please try: '/Check <Player>'"),
	COMMAND_UNBANIP(Perm.COMMAND_UNBANIP, ChatColor.RED + "Please try: '/UnbanIP <IP>'"),
	COMMAND_MUTE(Perm.COMMAND_MUTE, ChatColor.RED + "Please try: '/Mute <Player>'"),
	COMMAND_FLY(Perm.COMMAND_FLY, ChatColor.RED + "Please try: '/Fly [Player]'"),
	COMMAND_R(Perm.COMMAND_R, ChatColor.RED + "Please try: '/R <Message>'"),
	COMMAND_MSG(Perm.COMMAND_MSG, ChatColor.RED + "Please try: '/Msg <Player> <Message>"),
	COMMAND_UNBAN(Perm.COMMAND_UNBAN, ChatColor.RED + "Please try: '/Unban <Player>'"),
	COMMAND_GAMEMODE(Perm.COMMAND_GAMEMODE, ChatColor.RED + "Please try: '/Gamemode [Player] <0:1:2>"),
	COMMAND_DELWARP(Perm.COMMAND_DELWARP, ChatColor.RED + "Please try: '/DelWarp <Warp>"),
	COMMAND_WARP(Perm.COMMAND_WARP, ChatColor.RED + "Please try: '/Warp <Warp>'"),
	COMMAND_SETWARP(Perm.COMMAND_SETWARP, ChatColor.RED + "Please try: '/SetWarp <Warp>'"),
	COMMAND_LAG(Perm.COMMAND_LAG, ChatColor.RED + "Please try: '/Lag'"),
	COMMAND_CLEARDROPS(Perm.COMMAND_CLEARDROPS, ChatColor.RED + "Please try: '/Cleardrops'"),
	COMMAND_AFK(Perm.COMMAND_AFK, ChatColor.RED + "Please try: '/Afk'"),
	COMMAND_SPAWNER(Perm.COMMAND_SPAWNER, ChatColor.RED + "Please try: '/Spawner <MobType>'"),
	COMMAND_BAN(Perm.COMMAND_BAN, ChatColor.RED + "Please try: '/Ban <Player> [Reason]'"),
	COMMAND_TEMPBAN(Perm.COMMAND_TEMPBAN, ChatColor.RED + "Please try: '/TempBan <Player> <Amount> <TimeFrame> [Reason]'");
	
	public final String usage;
	public final Perm permission;
	
	Cmd(Perm permission, String usage)
	{
		this.usage = usage;
		this.permission = permission;
	}
	
	public boolean hasPermission(CommandSender user)
	{
		return permission.hasPermission(user);
	}
	
	public void sendMustBeIngame(CommandSender user)
	{
		user.sendMessage(MessageVal.COMMAND_MUST_BE_INGAME.getValue());
	}
	
}
