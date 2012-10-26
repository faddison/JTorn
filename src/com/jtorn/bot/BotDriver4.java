package com.jtorn.bot;

import java.util.ArrayList;


public class BotDriver4
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.out.println("Starting application...");
		TornProxy proxy18 = new TornProxy("38.87.65.185", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy19 = new TornProxy("199.116.84.44", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy20 = new TornProxy("216.172.139.50", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy21 = new TornProxy("50.117.69.148", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy22 = new TornProxy("173.232.88.5 ", "3131", "754d539bc6ade1ad", "454585f105747a76");
		
		TornUser user18 = new TornUser("BurningBridges", "SmbcrtXm", "BairdC_987678@hotmail.com", proxy18);
		TornUser user19 = new TornUser("Dentyne", "w9eqtHRb", "BH_639_Bxter_67_Hulda@hotmail.com", proxy19);
		TornUser user20 = new TornUser("Gio749", "aHcjWYAB", "Reilly_Wilbur_890458@hotmail.com", proxy20);
		TornUser user21 = new TornUser("Aiden983433", "MQE4Vh3Z", "AMoses_89_343@hotmail.com", proxy21);
		TornUser user22 = new TornUser("Joette", "csaJKQ6s", "FlynnJoette_455676878@hotmail.com", proxy22);
		
		ArrayList<TornUser> users = new ArrayList<TornUser>();
		users.add(user18);
		users.add(user19);
		users.add(user20);
		users.add(user21);
		users.add(user22);
		
		while (true)
		{
			for (int i = 0; i < users.size(); i++)
			{
				System.out.println("Running bot...");
				TornBot4 bot = new TornBot4(users.get(i));
				bot.run();
			}
			System.out.println("Cycle complete. Sleeping 60 mins...");
			Thread.sleep(1000*60*60);
		}
	}
}
