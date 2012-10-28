package com.jtorn.bot;

import com.jtorn.bot.TornRoutine.RoutineType;


public class BotDriver3
{
	public static void main(String[] args) 
	{
		System.out.println("Creating the bot threads...");
		TornProxy proxy = null;
		TornUser user = new TornUser("trialaccount", "trial1!", "fastslip@gmail.com", proxy);
		String[] botArgs = {TornConstants.hawaii, 
						 TornConstants.orchid, 
						 Integer.toString(TornConstants.time_hawaii)};
		Thread botThread =  new TornBot(user, RoutineType.FLOWERS, botArgs);
		botThread.start();
		
	}
}
