package com.teaminfinity.exigencies.utils;

import java.util.List;
import java.util.Random;

public class RandomManager {

	public static Random random = new Random();
	
	public static double getRandomPercent()
	{
		return (double)random.nextInt(100);
	}
	
	public static Object chooseFromArray(Object[] array)
	{
		if(array.length == 0)
		{
			return null;
		}
		return array[random.nextInt(array.length)];
	}
	
	public static int nextInt(int max)
	{
		return random.nextInt(max);
	}
	
	public static Object chooseFromList(List<Object> list)
	{
		if(list.isEmpty())
		{
			return null;
		}
		return list.get(random.nextInt(list.size()));
	}
	
	public static int getRandomNumberMax100()
	{
		return (int)getRandomPercent();
	}
	
	public static int getNumberBetweenTwo(int a, int b)
	{
		if(a == 0 && b == 0)
		{
			return 0;
		}
		if(a == b)
		{
			return a;
		}
		int highest = Math.max(a, b);
		int lowest = Math.min(a, b);
		int diff = highest - lowest;
		
		return random.nextInt(diff) + lowest;
	}
	
}
