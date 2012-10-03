package com.jtorn.bot;

import com.gargoylesoftware.htmlunit.WebClient;

public class LoginPage extends Page{

	public LoginPage(WebClient webClient){
		super(webClient);
		gotoUrl();
		verifyHtmlElementIdOnPage();
	}		
}