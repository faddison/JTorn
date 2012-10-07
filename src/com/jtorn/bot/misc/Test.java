package com.jtorn.bot.misc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, URISyntaxException
	{
		WebClient wc = new WebClient(BrowserVersion.FIREFOX_3_6);
		wc.setJavaScriptEnabled(false);
		
	    // Get the first page
	    HtmlPage page = wc.getPage("file://D:/torncity/offline-gym.html");
//	    for (HtmlElement e: page.getElementById("divStrength").getChildElements())
//		{
//			System.out.println(e.toString());
//			HtmlElement e2 = e.getElementById("t");
//			System.out.println(e.toString());
//		}
	    
	    for (DomNode n: page.getElementById("divStrength").getDescendants())
		{
			//System.out.println(n.toString());
			if (n.toString().contains("HtmlTextInput[<input type=\"text\" value=\"1\" id=\"t\" name=\"t\">]"))
			System.out.println(n.getCanonicalXPath());
			
		}

	}

}
