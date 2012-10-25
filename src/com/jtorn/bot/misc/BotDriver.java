package com.jtorn.bot.misc;

import java.util.Collections;
import java.util.Stack;
import java.util.concurrent.Executor;

import com.jtorn.bot.TornProxy;
import com.jtorn.bot.TornUser;

public class BotDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println("Creating the bot threads...");
		
		// login routine not robust enough
		
		
		TornProxy proxy1 = new TornProxy("173.208.130.242", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy2 = new TornProxy("173.208.145.65", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy4 = new TornProxy("205.164.13.246", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy5 = new TornProxy("205.164.57.51", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy6 = new TornProxy("216.172.139.227", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy7 = new TornProxy("216.172.141.251", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy8 = new TornProxy("216.172.141.65", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy9 = new TornProxy("216.172.141.68", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy10 = new TornProxy("216.172.145.107", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy11 = new TornProxy("38.87.65.58", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy13 = new TornProxy("69.46.81.117", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy14 = new TornProxy("69.46.77.137", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy15 = new TornProxy("69.46.77.100", "3131", "754d539bc6ade1ad", "454585f105747a76");
		TornProxy proxy16 = new TornProxy("50.117.71.195", "3131", "754d539bc6ade1ad", "454585f105747a76");
		
		TornUser user1 = new TornUser("gouldsberryc", "kicaxa34", "gouldsberryc@yahoo.com", proxy1);
		TornUser user2 = new TornUser("MVanbebber", "elikit95", "malcolm.vanbebber@yahoo.com", proxy2);
		TornUser user4 = new TornUser("jbricker", "pokeca50", "larson.roland@yahoo.com", proxy4);
		TornUser user5 = new TornUser("piccardo", "bihupi73", "tyrone.piccard@yahoo.com", proxy5);
		TornUser user6 = new TornUser("pilonASH", "xataze27", "ashleepilon@yahoo.com", proxy6);
		TornUser user7 = new TornUser("helwig", "ejuzoh64", "jessie_helwig@yahoo.com", proxy7);
		TornUser user8 = new TornUser("prestonBolich", "wenaqa24", "prestonbolich@yahoo.com", proxy8);
		TornUser user9 = new TornUser("schristian", "ixefev00", "schueler_christian@yahoo.com", proxy9);
		TornUser user10 = new TornUser("Lokietek", "qedaqi44", "danielalokietek@yahoo.com", proxy10);
		TornUser user11 = new TornUser("TiaPowerex", "exudon45", "denvertia@yahoo.com ", proxy11);
		TornUser user13 = new TornUser("wackoChick", "poyuso76", "dayhoffverda@yahoo.com ", proxy13);
		TornUser user14 = new TornUser("DarkDom", "xoreru17", "nascimentodom@yahoo.com", proxy14);
		TornUser user15 = new TornUser("MistressB", "vexele54", "reina.brinsfield@yahoo.com", proxy15);
		TornUser user16 = new TornUser("JYOLO", "edasen30", "jcollelo@yahoo.com", proxy16);
		
		TornBot botThread1 = new TornBot(user1);
		TornBot botThread2 = new TornBot(user2);
		TornBot botThread4 = new TornBot(user4);
		TornBot botThread5 = new TornBot(user5);
		TornBot botThread6 = new TornBot(user6);
		TornBot botThread7 = new TornBot(user7);
		TornBot botThread8 = new TornBot(user8);
		TornBot botThread9 = new TornBot(user9);
		TornBot botThread10 = new TornBot(user10);
		TornBot botThread11 = new TornBot(user11);
		TornBot botThread13 = new TornBot(user13);
		TornBot botThread14 = new TornBot(user14);
		TornBot botThread15 = new TornBot(user15);
		TornBot botThread16 = new TornBot(user16);
				
        Stack<TornBot> botStack = new Stack<TornBot>();
        
        botStack.push(botThread1);
        botStack.push(botThread2);
        botStack.push(botThread4);
        botStack.push(botThread5);
        botStack.push(botThread6);
        botStack.push(botThread7);
        botStack.push(botThread8);
        botStack.push(botThread9);
        botStack.push(botThread10);
        botStack.push(botThread11);
        botStack.push(botThread13);
        botStack.push(botThread14);
        botStack.push(botThread15);
        botStack.push(botThread16);        
		
		int size = botStack.size();
		Collections.shuffle(botStack);
		
		System.out.println("Starting the bot threads...");
		
		try 
		{
			for (int i = 0; i < size; i++)
			{
				TornBot botThread = botStack.pop();
				botThread.start();
				System.out.println((i+1)+"# Bot thread started. Waiting 1 minute until next startup...");
				Thread.sleep(1000*60);
			}
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
	}

}
