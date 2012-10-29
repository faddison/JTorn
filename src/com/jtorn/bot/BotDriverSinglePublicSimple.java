package com.jtorn.bot;

import com.jtorn.bot.TornRoutine.RoutineType;


public class BotDriverSinglePublicSimple
{
	public static void main(String[] args) 
	{
		System.out.println("Creating the bot threads...");
		TornUser user = new TornUser(args[0], args[1], args[3], null);
		Thread botThread =  new TornBot(user, RoutineType.SIMPLE, null);
		botThread.start();
	}
}
