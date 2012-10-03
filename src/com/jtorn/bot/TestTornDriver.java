package com.jtorn.bot;

import java.util.logging.Level;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class TestTornDriver{
	public static void main(String args[]){
		//Logging off for HtmlUnit Warnings
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		
		WebClient wc = new WebClient(BrowserVersion.FIREFOX_10);
		LoginPage pgLogin = new LoginPage(wc);
		
		TornAction tornAction = new TornAction(wc);
		tornAction.login("djfwong", "127910");
		System.out.println(tornAction.getHtmlPage().asText());
	
		wc.closeAllWindows();
	}
}