package com.jtorn.bot;


public class BotDriver3
{
	public static void main(String[] args) 
	{
		System.out.println("Creating the bot threads...");
		
		TornProxy proxy = null;
		TornUser user = new TornUser("trialaccount", "trial1!", "fastslip@gmail.com", proxy);
		Thread botThread =  new TornBot(user);
		botThread.start();
//		TornBot4 bot = new TornBot4(user);
//		bot.run();
		
	}
}
