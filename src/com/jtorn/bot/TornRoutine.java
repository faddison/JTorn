package com.jtorn.bot;

import java.util.Date;
import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TornRoutine
{
	private TornAction action;
	private WebClient wc;
	private TornUser user;
	private boolean shouldRun = true;
	private int errors = 0;
	
	public TornRoutine(WebClient wc, TornUser user)
	{
		this.wc = wc;
		this.user = user;
		this.action = new TornAction(wc, user);
	}
	
	public TornAction getAction()
	{
		return this.action;
	}
	
	public void flowerRunning()
	{
		int travel_time = TornConstants.time_argentina;
		String country_id = TornConstants.argentina;
		String flower_id = TornConstants.ceibo_flower;
		
		try
		{
			System.out.println(this.user.getUsername()+"-"+"Starting application...");
			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
			HtmlPage page = wc.getPage("http://torn.com");
/*
		    // Get the first page
		    HtmlPage page = wc.getPage("http://torn.com");
		    //System.out.println(page.getWebResponse().getContentAsString());
		    if (!isLoggedIn())
		    	page = login(page, user.getUsername(), user.getPassword());
		    else
		    	System.out.println(this.user.getUsername()+"-"+"Already logged in.");
*/		    	
		    System.out.println(this.user.getUsername()+"-"+"Index.php loaded.");
	        int loop = 0;
		    while (action.isShouldRun() && this.errors < 4)
		    {
		    	loop++;
		    	System.out.println(this.user.getUsername()+"-"+"Beginning loop "+loop+" at "+TornConstants.getTimeDateFormat().format(new Date()));
		    	try
		    	{
		    		//System.out.println(this.user.getUsername()+"-"+page.getWebResponse().getContentAsString());
		    		page = action.loadIndex();
		    		if (!action.isLoggedIn())
				    	page = action.login(page, user.getUsername(), user.getPassword());
		    		//System.out.println(this.user.getUsername()+"-"+page.getWebResponse().getContentAsString());
		    		if (page.asXml().toLowerCase().contains("you are travelling..."))
		    			page = action.loadIndex();
		    		if (action.onCaptcha(page))
		    			page = action.solveCaptcha(page);
		    		else
		    		{
			    		if (page.asText().toLowerCase().contains(TornConstants.travelling))
			    		{	
			    			System.out.println("Currently travelling. Sleeping 5 mins...");
			    			Thread.sleep(1000*60*5);
			    		}
			    		else if (page.asText().toLowerCase().contains(TornConstants.atStoreAbroad))
			    		{
			    			/*
			    			System.out.println("At store abroad.");
			    			page = buyFlowers(page, 21);
			    			page = travelHome(page);
			    			System.out.println("Sleeping 15 minutes...");
			    			Thread.sleep(1000*15*60);
			    			*/
			    			System.out.println("At store abroad.");
			    			page = action.buyAnyFlowers(page, 21,flower_id);
			    			page = action.travelHome(page);
			    			System.out.println("Sleeping "+travel_time+" minutes...");
			    			Thread.sleep(1000*travel_time*60);
			    		}
			    		else if (page.asText().toLowerCase().contains(TornConstants.atHome))
			    		{
			    			action.writeProfile(page.asXml());
			    			action.writeItems(action.loadItems().asXml());
			    			action.trainDefence(page, 20);
			    			System.out.println("Starting new flower run");
			    			//page = runFlowers(page, 21);
			    			page = action.runAnyFlowers(page, 21,country_id,flower_id,travel_time);
			    		}
			    		else
			    		{
			    			System.out.println("ERROR. In unknown state");
			    			System.out.println(page.getWebResponse().getContentAsString());
			    			action.handlePageError(page);
			    			this.errors++;
			    		}
			    			
		    		}
		    		System.out.println("Completed flower run.");
		    	}
		    	catch (Exception e)
		    	{
		    		System.out.println(this.user.getUsername()+"-"+e.toString());
		    		//System.out.println(page.getWebResponse().getContentAsString());
		    		e.printStackTrace();
		    		if (!action.handlePageError(page))
		    			this.errors++;
		    		if (this.errors > 3)
		    			action.abort();
		    	}
		    }
		    System.out.println(this.user.getUsername()+"-"+"Thread terminated!");
		    return;
		    
		}
		catch (Exception e)
		{
			System.out.println(this.user.getUsername()+"-"+e.toString());
			e.printStackTrace();
			action.abort();
		}
		
		
	}
}
