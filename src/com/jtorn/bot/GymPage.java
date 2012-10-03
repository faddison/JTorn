package com.jtorn.bot;

import com.gargoylesoftware.htmlunit.WebClient;

public class GymPage extends Page{

	public GymPage(WebClient webClient)
	{
		super(webClient);
		verifyHtmlElementIdOnPage();
	}
	
}