package com.jtorn.bot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.jtorn.bot.TornRoutine.RoutineType;
import com.jtorn.util.TornUtil;


public class BotDriverMulti
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.out.println("Starting application...");
		// 142.54.170.231 not working
		
		// slow
		TornProxy proxy18 = new TornProxy("23.29.57.31", "3131", "754d539bc6ade1ad", "454585f105747a76");
		// really slow
		TornProxy proxy19 = new TornProxy("199.116.84.44", "3131", "754d539bc6ade1ad", "454585f105747a76");
		// fast
		TornProxy proxy20 = new TornProxy("216.172.139.50", "3131", "754d539bc6ade1ad", "454585f105747a76");
		// fast
		TornProxy proxy21 = new TornProxy("50.117.69.148", "3131", "754d539bc6ade1ad", "454585f105747a76");
		// really slow
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
		
		Collections.shuffle(users);
		// minutes
		int sleepTime = 60;
		// seconds
		int threadTimeout = 180;
		
		while (true)
		{
			for (int i = 0; i < users.size(); i++)
			{
				System.out.println("Running bot...");
				TornBot botThread = new TornBot(users.get(i), RoutineType.SIMPLE, null);
				botThread.start();
				botThread.join(threadTimeout * 1000);
			}
			
			System.out.println("Cycle completed at "+ TornUtil.getTime(TornConstants.timeDateFormat));
			System.out.println("Sleeping for "+sleepTime+" minutes.");
			System.out.println("Waking at "+ TornUtil.getTime(TornConstants.timeDateFormat, sleepTime));
			Thread.sleep(sleepTime*1000*60);
		}
	}
}
