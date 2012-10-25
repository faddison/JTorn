package com.jtorn.bot;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

abstract class Page{
	
	protected String url;
	protected boolean onPage;
	protected HtmlPage htmlPage;
	protected WebClient webClient;

	public Page(WebClient webClient)
	{
		this.webClient = webClient;
		this.url = retrieveUrl();
	}
	
	
	//Get URL for page from properties file
	public String retrieveUrl()
	{
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream("UrlPages.properties");
		String classUrl = "";
		try 
		{
			prop.load(in);
			classUrl = prop.getProperty(this.getClass().getSimpleName());
			if (classUrl == "")
				throw new NullPointerException();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (NullPointerException e)
		{
			System.err.println(this.getClass().getSimpleName() + " Page url not found or was null");
		}
		return classUrl;
	}

	public HtmlPage gotoUrl(){
		try 
		{
			htmlPage = webClient.getPage(url);
			
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return htmlPage;
	}
	
	//Get HtmlElementId for page from properties file
	public boolean verifyHtmlElementIdOnPage(){
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream("htmlElementIDList.properties");
		try {
			prop.load(in);
			HtmlElement htmlElement = (HtmlElement) this.htmlPage.getElementById(prop.getProperty(this.getClass().getSimpleName()));
			if(htmlElement != null){
				onPage = true;
				System.out.println("On expected Html page! " + this.url);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Not on Html page! " +  this.url);
		}
		return onPage;
	}	
		
	public String getUrl() {
		return url;
	}
	public HtmlPage getHtmlPage() {
		return htmlPage;
	}
	
	public boolean isOnPage() {
		return onPage;
	}

	public WebClient getWebClient() {
		return webClient;
	}

	public void setHtmlPage(HtmlPage htmlPage) {
		this.htmlPage = htmlPage;
	}
}