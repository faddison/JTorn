package com.jtorn.bot.misc;

import java.util.concurrent.Executor;

import com.jtorn.bot.TornProxy;
import com.jtorn.bot.TornUser;

public class BotDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		TornProxy proxy = new TornProxy("216.172.141.68", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornUser user = new TornUser("schristian", "ixefev00", "scheuler_christian@yahoo.com", proxy);
		
		TornProxy proxy2 = new TornProxy("205.164.13.246", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornUser user2 = new TornUser("jbricker", "pokeca50", "larson.roland@yahoo.com", proxy2);
		
		(new Thread(new TornBot(user))).start();
		(new Thread(new TornBot(user2))).start();
	}

}
