package com.teaminfinity.exigencies.enums;

public enum TimeFrame {
	
	SECOND(1),
	MINUTE(60),
	HOUR(3600),
	DAY(86400),
	MONTH(2419200),
	YEAR(29030400);
	
	private long amount;
	TimeFrame(long amount){
		this.setAmount(amount);
	}
	
	public static TimeFrame getTimeFrame(String input){
		switch(input.toLowerCase()){
		case "s":
			return SECOND;
		case "second":
			return SECOND;
		case "seconds":
			return SECOND;
		case "m":
			return MINUTE;
		case "min":
			return MINUTE;
		case "mins":
			return MINUTE;
		case "minute":
			return MINUTE;
		case "minutes":
			return MINUTE;
		case "h":
			return HOUR;
		case "hour":
			return HOUR;
		case "hours":
			return HOUR;
		case "d":
			return DAY;
		case "days":
			return DAY;
		case "day":
			return DAY;
		case "month":
			return MONTH;
		case "months":
			return MONTH;
		case "mon":
			return MONTH;
		case "y":
			return YEAR;
		case "year":
			return YEAR;
		case "years":
			return YEAR;
		}
		return null;
	}
	
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
}
