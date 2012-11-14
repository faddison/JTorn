package com.jtorn.bot;

import com.jtorn.bot.TornRoutine.RoutineType;


public class BotDriverSingle
{
	public static void main(String[] args) 
	{
		System.out.println("Creating the bot threads...");
		TornProxy proxy = null;
		TornUser user = new TornUser("trialaccount", "trial1!", "fastslip@gmail.com", proxy);
		String[] botArgs = {TornConstants.argentina, 
						 TornConstants.ceibo_flower, 
						 Integer.toString(TornConstants.time_argentina)};
		Thread botThread =  new TornBot(user, RoutineType.FLOWERS, botArgs);
	while (true)
	{
		try 
		{
			botThread.start();
			botThread.join();
			//System.out.println("Sleeping 60 minutes...");
			//Thread.sleep(1000*60*60);
		} catch (Exception e) 
		{
			e.printStackTrace();
			botThread =  new TornBot(user, RoutineType.FLOWERS, botArgs);}
			System.out.println("Building new bot thread...");
		}
	}
}
