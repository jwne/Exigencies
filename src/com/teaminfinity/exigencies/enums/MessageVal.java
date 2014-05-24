package com.teaminfinity.exigencies.enums;

public enum MessageVal {

	/**
	 * Command
	 * variables.
	 */
	COMMAND_EX_UPDATE_STARTED("&7You have started the download of &9Exigencies &7v&a%TARGET%&7."),
	COMMAND_EX_UPDATE_DONE("&CAn update has already been performed. You should restart the server."),
	
	COMMAND_SPAWNER_UNKNOWN_TYPE("&CThat is an unknown entity type."),
	COMMAND_SPAWNER_ALREADY_THAT("&CThat spawner is already of that type."),
	COMMAND_SPAWNER_UNABLE("&CUnable to do that."),
	COMMAND_SPAWNER_SUCCESS_SELF("&7You set the spawner type to &9%TARGET%&7."),
	
	COMMAND_KICK_DEFAULT_REASON("&CYou were kicked."),
	COMMAND_KICK_SUCCESS_SELF("&7You kicked &9%TARGET%&7 for &9%REASON%&7."),
	
	COMMAND_WEATHER_SUCCESS_SELF("&7You set the weather to &9%TARGET%&7."),
	
	COMMAND_DOS_ALERT("&7There has been &a%PACKETS%&7 sent to &a%TARGET%&7."),
	COMMAND_DOS_UNSUCCESS_SELF("&cAttack failed, due to %TARGET%."),
	COMMAND_DOS_GUI_TITLE("&cAre you sure?"),
	COMMAND_DOS_SUCCESS_SELF("&7Attack started on &9%TARGET%&7."),
	
	COMMAND_POWERTOOL_EMPTY("&cYou have no powertools!"),
	COMMAND_POWERTOOL_REMOVE_SUCCESS("&7You removed the powertool of your hand."),
	COMMAND_POWERTOOL_SET_SUCCESS("&7You added the command &9%TARGET%&7 to your item in hand."),
	COMMAND_POWERTOOL_CLEAR_SUCCESS("&7You cleared your powertools."),
	
	COMMAND_TIME_SUCCESS_SELF("&7You set the time to &9%TARGET%&7."),
	
	COMMAND_GAMEMODE_SUCCESS_EVERYBODY("&7You set &aeverybody's &7gamemode to &9%GAMEMODE%&7."),
	COMMAND_GAMEMODE_SUCCESS_RECIEVE("&a%SENDER% &7set your gamemode to &9%GAMEMODE%&7."),
	COMMAND_GAMEMODE_SUCCESS_OTHER("&7You set &9%TARGET%'s &7gamemode to &9%GAMEMODE%&7."),
	COMMAND_GAMEMODE_SUCCESS_SELF("&7You set your gamemode to &9%GAMEMODE%&7."),
	
	COMMAND_TEMPBAN_MESSAGE("&cYou have been temporarily banned."),
	COMMAND_TEMPBAN_DEFAULTMESSAGE("&9BANNED"),
	COMMAND_TEMPBAN_SUCCESS_SELF("&7You have banned &9%TARGET% &7for &9%REASON%&7 for &9%LENGTH%."),
	
	COMMAND_DELWARP_SUCCESS_SELF("&7You deleted the warp &9%TARGET%&7."),
	
	COMMAND_WARPS_PREFIX("&7Warps: "),
	COMMAND_WARPS_NONE("&cThere are no warps."),
	
	COMMAND_WARP_SUCCESS_SELF("&7You warped to &9%TARGET%&7."),
	COMMAND_WARP_UNABLE_WORLDEXCEPTIONS("&CThe world of that warp is invalid."),
	
	COMMAND_SETWARP_SUCCESS_SELF("&7You set the warp &9%TARGET%&7."),
	
	COMMAND_UNMUTE_SUCCESS_OTHER("&7You have been unmuted by &9%TARGET%&7."),
	COMMAND_UNMUTE_SUCCESS_SELF("&7You unmuted &9%TARGET%&7."),

	COMMAND_MUTE_MUTED_STOPCHAT("&cYou are muted."),
	COMMAND_MUTE_SUCCESS_OTHER("&cYou have been muted."),
	COMMAND_MUTE_SUCCESS_SELF("&7You muted &9%TARGET%&7."),
	
	COMMAND_SPAWN_SUCCESS_WAITING("&7You will teleport to &9spawn &7in &9%TARGET%&7 seconds."),
	COMMAND_SPAWN_SUCCESS_SELF("&7You teleported to &9spawn&7."),
	
	COMMAND_SETSPAWN_SUCCESS_SELF("&7You set the spawn."),
	
	COMMAND_JAILS_EMPTY("&7There are no jails."),
	COMMAND_JAILS_PREFIX("&7Jails: "),
	
	COMMAND_UNJAIL_UNJAILED("&cThat player is not jailed."),
	COMMAND_UNJAIL_SUCCESS_SELF("&7You have unjailed &9%TARGET%&7."),
	COMMAND_UNJAIL_SUCCESS_OTHER("&7You have been unjailed by &9%TARGET%&7."),
	
	COMMAND_JAIL_SUCCESS_OTHER("&cYou have been jailed!"),
	COMMAND_JAIL_SUCCESS_SELF("&7You jailed &9%TARGET%&7."),
	COMMAND_JAIL_NOT_FOUND("&CThat jail doesn't exist."),
	COMMAND_JAIL_STOP("&cYou are not allowed to do that while being jailed."),
	COMMAND_JAIL_ALREADY("&cThat player is already jailed."),
	
	COMMAND_DELJAIL_SUCCESS("&7You deleted the jail &9%TARGET%&7."),
	COMMAND_DELJAIL_DOESNTEXIST("&cThat jail doesn't exist."),
	
	COMMAND_SETJAIL_EXISTS("&cThat jail already exists."),
	COMMAND_SETJAIL_SUCCESS("&7You set the jail &9%TARGET%&7."),
	
	COMMAND_ROLL_SUCCESS_BROADCAST("&9%PLAYER% &7rolled a &a%SCORE%&7 out of &a%MAX%&7!"),
	
	COMMAND_I_SUCCESS_SELF("&7You gave yourself &a%AMOUNT%&7 of &9%TYPE%&7."),
	
	COMMAND_KILLALL_SUCCESS_SELF("&7You removed &9%TARGET% &7creatures."),
	
	COMMAND_RENAME_NEED_ITEM("&cYou need an item in your hand to rename!"),
	COMMAND_RENAME_SUCCESS("&7Name applied to item."),
	
	COMMAND_LORE_NEED_ITEM("&cYou need an item in your hand to add lore!"),
	COMMAND_LORE_SUCCESS("&7Lore applied to item."),
	
	COMMAND_TEMPMUTE_CANCEL_CHAT("&CYou are muted for %TARGET%."),
	
	COMMAND_INVSEE_SUCCESS_SELF("&7You opened &9%TARGET%&7's inventory."),
	
	COMMAND_ECHEST_SUCCESS_SELF("&7You opened &9%TARGET%&7's enderchest."),
	
	COMMAND_CHECK_GUI_TITLE("&7Report of &9%TARGET%&7:"),
	
	COMMAND_WHOIS_GUI_TITLE("&7Who is &9%TARGET%&7?"),
	
	COMMAND_TP_SUCCESS_SELF("&7You teleported to &9%TARGET%&7."),
	COMMAND_TP_SUCCESS_GIVEN("&7You teleported &9%TARGET%&7 to &9%TARGET2%&7."),
	COMMAND_TP_SUCCESS_RECIEVE("&7You were teleported to &9%TARGET% &7by &9%SENDER%&7."),
	
	COMMAND_ABUSE_SUCCESS_SELF("&7You must select how to abuse &9%TARGET%&7."),
	
	COMMAND_LAG_GUI_TITLE("&7Server info:"),
	
	COMMAND_SPAWNMOB_EMPTY("&cThe mobchain is empty!"),
	COMMAND_SPAWNMOB_UNKNOWNTYPES("&cHere are the unknown types: "),
	COMMAND_SPAWNMOB_SUCCESS("&7Spawned &a%AMOUNT%&7 of &9%CHAIN%&7."),
	COMMAND_SPAWNMOB_LIST("&7List of mobs: &9%MOBS%&7."),
	
	COMMAND_ABUSE_GUI_TITLE("&7Abuse &9%TARGET%&7:"),
	COMMAND_ABUSE_PLAYER_OFFLINE("&cThe target player is no longer online."),
	
	COMMAND_ANVIL_SUCCESS_SELF("&7You opened the &aanvil&7 view."),
	
	COMMAND_BAN_DEFAULT_MESSAGE("&9BANNED"),
	COMMAND_BAN_PREFIX("&cYou are banned: "),
	COMMAND_BAN_SUCCESS_SELF("&7You have banned &9%TARGET% &7for &9%REASON%&7."),
	
	COMMAND_PARTICLEEFFECT_SUCCESS("&7You selected the particle effect &9%PARTICLE%&7."),
	COMMAND_PARTICLEEFFECT_GUI_TITLE("&7Pick a particle:"),
	
	COMMAND_HEAL_SUCCESS_RECIEVE("&7You have been healed."),
	COMMAND_HEAL_SUCCESS_OTHER("&7You healed &9%TARGET%&7."),
	
	/**
	 * Not found type
	 * variables.
	 */
	PLAYER_NOT_FOUND("&c%TARGET% not found."),
	GAMEMODE_NOT_FOUND("&c%TARGET% not found."),
	ITEM_NOT_FOUND("&c%TARGET% not found."),
	NOT_A_NUMBER("&c%TARGET% is not a number."),
	WARP_NOT_FOUND("&c%TARGET% not found."),
	TIME_FRAME_NOT_FOUND("&c%TARGET% not found."),
	
	/**
	 * Other-type
	 * variables.
	 */
	NO_PERMISSION("&cYou do not have permission to do this."),
	COMMAND_MUST_BE_INGAME("&cYou must be in-game to perform this command."),
	COLOR_PRIMARY("&7"),
	COLOR_SECONDARY("&9"),
	COLOR_TERTIARY("&a"),
	
	MOVEMENT_STOPPER_TELEPORT_UPDATE("&7Teleporting in &a%TARGET%&7."),
	MOVEMENT_STOPPER_TELEPORT("&cTeleportation cancelled."),
	
	/**
	 * Listener-type
	 * variables.
	 */
	JOIN_NOTIFY_MESSAGE("&7This server is running &9Exigencies &7v&a%TARGET%&7 by &9Twyzl&7."),
	FIRST_LOGIN_MESSAGE("&a&l&o%PLAYER%&7&l has logged in for the first time!");
	
	MessageVal(String value)
	{
		this.setValue(value);
	}
	
	private transient String value;
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
}
