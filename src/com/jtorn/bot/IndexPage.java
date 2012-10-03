package com.jtorn.bot;

import com.gargoylesoftware.htmlunit.WebClient;


public class IndexPage extends Page{
	
	public IndexPage(WebClient webClient){
		super(webClient);
		gotoUrl();
		verifyHtmlElementIdOnPage();
	}
	
}