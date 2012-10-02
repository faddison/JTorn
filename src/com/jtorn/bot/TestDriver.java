package com.jtorn.bot;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

import javax.imageio.ImageReader;

import com.DeathByCaptcha.Captcha;
import com.DeathByCaptcha.Client;
import com.DeathByCaptcha.SocketClient;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

public class TestDriver 
{

	public static void main(String[] args)
	{
		try
		{
			HtmlElement element;
			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
			final WebClient wc = new WebClient(BrowserVersion.FIREFOX_3_6);
			wc.setJavaScriptEnabled(true);
			
		    // Get the first page
		    HtmlPage page = wc.getPage("http://torn.com/login");
		    System.out.println(page.asText());
		    System.out.println("-----------------------------------------------------");
	
		    // Get the form that we are dealing with and within that form, 
		    // find the submit button and the field that we want to change.
		    final HtmlForm form = page.getFormByName("login");
	
		    HtmlSubmitInput button = form.getInputByName("btnLogin");
		    
		    HtmlTextInput textField = form.getInputByName("player");
	
		    // Change the value of the text field
		    textField.setValueAttribute("trialaccount");
		    
		    final HtmlPasswordInput passwordField = form.getInputByName("password");
			
		    // Change the value of the text field
		    passwordField.setValueAttribute("");
	
		    // Now submit the form by clicking the button and get back the second page.
		    page = button.click();
		    
		    page = wc.getPage("http://torn.com/index.php");
		    System.out.println(page.asText());
		    System.out.println("-----------------------------------------------------");
		    
		    page = wc.getPage("http://torn.com/crimes.php");
		    page = wc.getPage("http://torn.com/crimes.php?bypass=1");

		    HtmlImage captchaImage = (HtmlImage) page.getElementById("recaptcha_image").getFirstChild();
		    captchaImage.saveAs(new File("captcha-image.jpg"));
		    
		    CaptchaSolver captchaSolver = new CaptchaSolver(new SocketClient("trialaccount", "Cappie1!"), "captcha-image.jpg");
		    captchaSolver.run();
		    Captcha captcha = captchaSolver.getCaptcha();
		    
            textField = (HtmlTextInput)page.getElementById("recaptcha_response_field");
		    textField.setValueAttribute(captcha.text);
		    element = (HtmlElement)page.getElementByName("submit");
		    page = element.click();
		    
		    page = wc.getPage("http://torn.com/crimes.php");
		    
		    System.out.println(page.asText());		    
		    System.out.println("-----------------------------------------------------");
		    
		    
		    wc.closeAllWindows();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
