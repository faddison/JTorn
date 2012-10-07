package com.jtorn.bot.misc;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.imageio.ImageReader;

import com.DeathByCaptcha.Captcha;
import com.DeathByCaptcha.Client;
import com.DeathByCaptcha.SocketClient;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.jtorn.bot.CaptchaSolver;
import com.jtorn.bot.TornProxy;
import com.jtorn.bot.TornUser;

public class TornBot implements Runnable
{

	private WebClient wc;
	private TornUser user;
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	public TornBot(TornUser user)
	{
		this.user = user;
		TornProxy proxy = user.getProxy();
		
		wc = new WebClient(BrowserVersion.FIREFOX_10, 
						   proxy.getAddress(), 
						   Integer.parseInt(proxy.getPort()));
	    final DefaultCredentialsProvider scp = new DefaultCredentialsProvider();
	    scp.addCredentials(proxy.getUsername(), proxy.getPassword());
	    wc.setCredentialsProvider(scp);
	    // JavaScript throws major errors on site.
		wc.setJavaScriptEnabled(false);
		wc.setThrowExceptionOnScriptError(false);
		wc.setThrowExceptionOnFailingStatusCode(false);
	}
	
	public void run()
	{
		try
		{
			System.out.println(this.user.getUsername()+"-"+"Starting application...");
			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);

		    // Get the first page
		    HtmlPage page = wc.getPage("http://torn.com/index.php");
		    if (page.asText().contains("You are no longer logged in."))
		    	page = login(page, user.getUsername(), user.getPassword());
		    else
		    	System.out.println(this.user.getUsername()+"-"+"Already logged in.");
		    	
		    System.out.println(this.user.getUsername()+"-"+"Index.php loaded.");
	        int loop = 0;
	        int errors = 0;
		    while (true)
		    {

		    	System.out.println(this.user.getUsername()+"-"+"Beginning loop "+loop+"...");
		    	loop++;
		    	try
		    	{
			    	if(inHospital(page) || inJail(page))
			    	{
			    		System.out.println(this.user.getUsername()+"-"+"Sleeping 5 minutes...");	
					    Thread.sleep(1000*5*60);
			    	}
			    	else
			    	{
			    		System.out.println(this.user.getUsername()+"-"+"Loading gym...");
			    		page = wc.getPage("http://torn.com/gym.php");
					    while (onCaptcha(page))
					    {
					    	wc.setJavaScriptEnabled(true);
						    System.out.println(this.user.getUsername()+"-"+"Captcha encountered.");
						    page = solveCaptcha(page);
						    wc.setJavaScriptEnabled(false);
					    }
					    //System.out.println(this.user.getUsername()+"-"+page.getWebResponse().getContentAsString());
					    trainStrength(page, 20);
					    //doCrime(page, 5);
					    System.out.println(this.user.getUsername()+"-"+"Sleeping 30 minutes...");
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MINUTE, 30);
						System.out.println(this.user.getUsername()+"-"+"Waking at " + dateFormat.format(cal.getTime()));
					    Thread.sleep(1000*30*60);
					    
				    }
			    	page = loadIndex();
		    	}
		    	catch (Exception e)
		    	{
		    		System.out.println(this.user.getUsername()+"-"+e.toString());
		    		e.printStackTrace();
		    		errors++;
		    		page = loadIndex();
		    		if (errors > 3)
		    			abort();
		    	}
		    }
		    
		}
		catch (Exception e)
		{
			System.out.println(this.user.getUsername()+"-"+e.toString());
			e.printStackTrace();
			abort();
		}
	}
		
	public HtmlPage loadIndex()
	{
		System.out.println(this.user.getUsername()+"-"+"Loading Index...");
		try 
		{
			return wc.getPage("http://torn.com/index.php");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean inHospital(HtmlPage page)
	{
		HtmlElement e = page.getElementById("icon15");
		if (e == null)
			return false;
	    boolean result = e.getAttribute("title").toLowerCase().contains("hospital");
	    if (result)
	    	System.out.println(this.user.getUsername()+"-"+"In hospital.");
	    return result;
	}
	
	public  boolean inJail(HtmlPage page)
	{
		boolean result = (page.getElementById("icon16") != null);
		if (result)
			System.out.println(this.user.getUsername()+"-"+"In jail.");
		return result;
		
	}
	
	public  boolean onCaptcha(HtmlPage page)
	{
		try
		{
			page.getAnchorByText("Use re-captcha");
			return true;
		}
		catch (ElementNotFoundException e)
		{
			return false;
		}
	}
	
	
	public HtmlPage login(HtmlPage page, String username, String password)
	{
		int attempts = 0;
		boolean loggedIn = false;
		while (!loggedIn)
		{
			try
			{
				if (attempts > 3)
					abort();
				attempts++;
				System.out.println(this.user.getUsername()+"-"+"Logging in... (Attempt "+attempts+")");
				page = wc.getPage("http://torn.com/login");
			    final HtmlForm form = page.getFormByName("login");
			    HtmlSubmitInput button = form.getInputByName("btnLogin");
			    HtmlTextInput textField = form.getInputByName("player");
			    textField.setValueAttribute(username);
			    final HtmlPasswordInput passwordField = form.getInputByName("password");
			    passwordField.setValueAttribute(password);
			    page = button.click();
			    page = wc.getPage("http://torn.com/index.php");
			    loggedIn = true;
		    }
			catch (Exception e)
			{
				System.out.println(this.user.getUsername()+"-"+e.toString());
				loggedIn = false;
			}
		}
		return page;
	    
	}
	
	public HtmlPage trainStrength(HtmlPage page, int amount) throws IOException
	{
		System.out.println(this.user.getUsername()+"-"+"Training "+amount+" strength...");
		HtmlTextInput amountInput = (HtmlTextInput) page.getByXPath("/html/body/div[4]/table/tbody/tr/td[2]/center/div[2]/div/div[2]/div[3]/div[1]/div[1]/div[2]/form/table/tbody/tr/td[1]/input[1]").get(0);
		amountInput.setValueAttribute(Integer.toString(amount));
		HtmlElement e = (HtmlElement) page.getByXPath("/html/body/div[4]/table/tbody/tr/td[2]/center/div[2]/div/div[2]/div[3]/div[1]/div[1]/div[2]/form/table/tbody/tr/td[3]/input").get(0);
		e.click();
		return page;
	}
	
	private HtmlPage solveCaptcha(HtmlPage page) throws Exception
	{
		System.out.println(this.user.getUsername()+"-"+"Solving captcha...");
		String url = page.getUrl().toString();
		page = wc.getPage(url+"?bypass=1");
		
	    HtmlImage captchaImage = (HtmlImage) page.getElementById("recaptcha_image").getFirstChild();
	    captchaImage.saveAs(new File("captcha-image.jpg"));
	    
	    CaptchaSolver captchaSolver = new CaptchaSolver(new SocketClient("trialaccount", "Cappie1!"), "captcha-image.jpg");
	    captchaSolver.run();
	    Captcha captcha = captchaSolver.getCaptcha();
	    
        HtmlTextInput textField = (HtmlTextInput)page.getElementById("recaptcha_response_field");
	    textField.setValueAttribute(captcha.text);
	    HtmlElement element = (HtmlElement)page.getElementByName("submit");
	    page = element.click();
	    System.out.println(this.user.getUsername()+"-"+"Captcha entered.");
	    return wc.getPage(url);
	}
	
	public void abort()
	{
		System.out.println(this.user.getUsername()+"-"+"Too many errors encountered. Aborting all operations");
		wc.closeAllWindows();
		System.exit(1);
	}
}

