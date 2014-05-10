package com.teaminfinity.exigencies.enums;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import net.minecraft.server.v1_7_R3.PacketPlayOutBlockBreakAnimation;
import net.minecraft.server.v1_7_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_7_R3.PlayerConnection;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public enum Particle
{
	NONE(null),
	LARGE_EXPLOSION("largeexplode"), 
	FIREWORK_SPARK("fireworksSpark"), 
	BUBBLE("bubble"), 
	SUSPENDED("suspended"), 
	DEPTH_SUSPEND("depthsuspend"), 
	TOWN_AURA("townaura"), 
	CRITICAL_HIT("crit"), 
	MAGICAL_CRITICAL_HIT("magicCrit"), 
	SMOKE("smoke"), 
	MOB_SPELL("mobSpell"), 
	MOB_SPELL_AMBIENT("mobSpellAmbient"), 
	SPELL("spell"), 
	INSTANT_SPELL("instantSpell"), 
	WITCH_MAGIC("witchMagic"), 
	NOTE("note"), 
	PORTAL("portal"), 
	ENCHANTMENT_TABLE("enchantmenttable"), 
	EXPLODE("explode"), 
	FLAME("flame"), 
	LAVA("lava"), 
	FOOTSTEP("footstep"), 
	SPLASH("splash"), 
	LARGE_SMOKE("largesmoke"), 
	CLOUD("cloud"), 
	SNOWBALL_POOF("snowballpoof"), 
	DROP_WATER("dripWater"), 
	DROP_LAVA("dripLava"), 
	SNOW_SHOVEL("snowshovel"), 
	SLIME("slime"), 
	HEART("heart"), 
	ANGRY_VILLAGER("angryVillager"), 
	HAPPY_VILLAGER("happyVillager"), 
	COLOURED_DUST("reddust"),
	HUGE_EXPLOSION("hugeexplosion");

	private String id;
	private static int fakeEntityIdIndex = 2147483647;

	private static HashMap<Location, Integer> fakeEntityIds = new HashMap<Location, Integer> ();

	private Particle(String name)
	{
		this.id = name;
	}
	public String getParticleName()
	{
		return this.id;
	}
	
	public Particle[] getParticles()
	{
		return Particle.values();
	}
	
	public Particle getParticle(String input)
	{
		for(Particle p:Particle.values()){
			if(input.equalsIgnoreCase(p.toString())){
				return p;
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	private static Object setPrivateField(String fieldName, Object instance, Object value)
	{
		try
		{
			Field field = null;
			if ((instance instanceof Class))
				field = ((Class)instance).getDeclaredField(fieldName);
			else
				field = instance.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			if ((instance instanceof Class))
			{
				field.set(null, value);
			}
			else
			{
				field.set(instance, value);
			}
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
		return value;
	}

	public void spawnParticle(Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int numberOfParticles)
	{
		if(this.getParticleName() == null)
		{
			return;
		}
		try {
			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
			setPrivateField("a", packet, getParticleName());
			setPrivateField("b", packet, Float.valueOf((float)location.getX()));
			setPrivateField("c", packet, Float.valueOf((float)location.getY()));
			setPrivateField("d", packet, Float.valueOf((float)location.getZ()));
			setPrivateField("e", packet, Float.valueOf(offsetX));
			setPrivateField("f", packet, Float.valueOf(offsetY));
			setPrivateField("g", packet, Float.valueOf(offsetZ));
			setPrivateField("h", packet, Float.valueOf(speed));
			setPrivateField("i", packet, Integer.valueOf(numberOfParticles));
			connection.sendPacket(packet);
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void spawnParticle(World world, Location location, float offsetX, float offsetY, float offsetZ, float speed, int particlesamount){
		if(this.getParticleName() == null)
		{
			return;
		}
		for(Player p:world.getPlayers()){
			spawnParticle(p, location, speed, speed, speed, speed, particlesamount);
		}
	}
	
	public static void spawnTileCrack(Player player, int blockId, int data, Location location, float offsetX, float offsetY, float offsetZ, float speed, int numberOfParticles){
		try { PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
			setPrivateField("a", packet, "tilecrack_" + blockId + "_" + data);
			setPrivateField("b", packet, Float.valueOf((float)location.getX()));
			setPrivateField("c", packet, Float.valueOf((float)location.getY()));
			setPrivateField("d", packet, Float.valueOf((float)location.getZ()));
			setPrivateField("e", packet, Float.valueOf(offsetX));
			setPrivateField("f", packet, Float.valueOf(offsetY));
			setPrivateField("g", packet, Float.valueOf(offsetZ));
			setPrivateField("h", packet, Float.valueOf(speed));
			setPrivateField("i", packet, Integer.valueOf(numberOfParticles));
			connection.sendPacket(packet);
		}catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

	public static void spawnTileCrack(World world, int blockId, int data, Location location, float offsetX, float offsetY, float offsetZ, float speed, int numberOfParticles) {
		try { 
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
			setPrivateField("a", packet, "tilecrack_" + blockId + "_" + data);
			setPrivateField("b", packet, Float.valueOf((float)location.getX()));
			setPrivateField("c", packet, Float.valueOf((float)location.getY()));
			setPrivateField("d", packet, Float.valueOf((float)location.getZ()));
			setPrivateField("e", packet, Float.valueOf(offsetX));
			setPrivateField("f", packet, Float.valueOf(offsetY));
			setPrivateField("g", packet, Float.valueOf(offsetZ));
			setPrivateField("h", packet, Float.valueOf(speed));
			setPrivateField("i", packet, Integer.valueOf(numberOfParticles));
			List<Player> players = world.getPlayers();
			for (int i = 0; i < players.size(); i++) {
				Player player = (Player)players.get(i);
				PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
				connection.sendPacket(packet);
			}
		}catch (Throwable t){
			t.printStackTrace();
		}
	}

	public static void spawnIconCrack(Player player, int itemId, Location location, float offsetX, float offsetY, float offsetZ, float speed, int numberOfParticles) {
		try { 
			PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
			setPrivateField("a", packet, "tilecrack_" + itemId);
			setPrivateField("b", packet, Float.valueOf((float)location.getX()));
			setPrivateField("c", packet, Float.valueOf((float)location.getY()));
			setPrivateField("d", packet, Float.valueOf((float)location.getZ()));
			setPrivateField("e", packet, Float.valueOf(offsetX));
			setPrivateField("f", packet, Float.valueOf(offsetY));
			setPrivateField("g", packet, Float.valueOf(offsetZ));
			setPrivateField("h", packet, Float.valueOf(speed));
			setPrivateField("i", packet, Integer.valueOf(numberOfParticles));
			connection.sendPacket(packet);
		}catch (Throwable t){
			t.printStackTrace();
		}
	}

	public static void spawnIconCrack(World world, int itemId, Location location, float offsetX, float offsetY, float offsetZ, float speed, int numberOfParticles) {
		try { 
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
			setPrivateField("a", packet, "tilecrack_" + itemId);
			setPrivateField("b", packet, Float.valueOf((float)location.getX()));
			setPrivateField("c", packet, Float.valueOf((float)location.getY()));
			setPrivateField("d", packet, Float.valueOf((float)location.getZ()));
			setPrivateField("e", packet, Float.valueOf(offsetX));
			setPrivateField("f", packet, Float.valueOf(offsetY));
			setPrivateField("g", packet, Float.valueOf(offsetZ));
			setPrivateField("h", packet, Float.valueOf(speed));
			setPrivateField("i", packet, Integer.valueOf(numberOfParticles));
			List<Player> players = world.getPlayers();
			for (int i = 0; i < players.size(); i++) {
				Player player = (Player)players.get(i);
				PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
				connection.sendPacket(packet);
			}
		} catch (Throwable t){
			t.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void spawnTileCrack(Block block) {
		spawnTileCrack(block.getWorld(), block.getTypeId(), block.getData(), block.getLocation(), 0.5F, 0.5F, 0.5F, 1.0F, 100);
	}

	public static void showDamage(Block block, int num)
	{
		try{
			PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation();
			if (!fakeEntityIds.containsKey(block.getLocation())){
				fakeEntityIds.put(block.getLocation(), Integer.valueOf(getNextFakeEntityId()));
			}
			setPrivateField("a", packet, fakeEntityIds.get(block.getLocation()));
			setPrivateField("b", packet, Integer.valueOf(block.getX()));
			setPrivateField("c", packet, Integer.valueOf(block.getY()));
			setPrivateField("d", packet, Integer.valueOf(block.getZ()));
			setPrivateField("e", packet, Integer.valueOf(num));
			List<Player> players = block.getWorld().getPlayers();
			for (int i = 0; i < players.size(); i++) {
				Player player = (Player)players.get(i);
				PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
				connection.sendPacket(packet);
			}
		}catch (Throwable t){
			t.printStackTrace();
		}
	}

	private static int getNextFakeEntityId()
	{
		if (fakeEntityIdIndex <= 0)
			fakeEntityIdIndex = 2147483647;
		return fakeEntityIdIndex--;
	}

}