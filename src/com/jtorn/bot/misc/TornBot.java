package com.jtorn.bot.misc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.WriteAbortedException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
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

public class TornBot extends Thread {

	private boolean shouldRun = true;
	private WebClient wc;
	private TornUser user;
	DateFormat timeDateFormat = new SimpleDateFormat("HH-mm-ss");
	DateFormat dayDateFormat = new SimpleDateFormat("yy-MM-dd");
	DateFormat fullDateFormat = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
	private int errors = 0;
	
	HashMap<String, String> countryMap;
	HashMap<String, String> flowerMap;
	
	private String travelling = "you are currently on the plane flying";
	private String atStoreAbroad = "travel home";
	private String atHome = "hallow town";
	
	private String mexico="2";
	private String hawaii="3";
	private String united_kingdom="10";
	private String argentina="7";
	private String japan="5";
	private String china="6";
	private String cayman_islands="12";
	private String canada="9";
	private String dubai="11";
	private String south_africa="4";
	private String switzerland="8";
	private String dahlia="260";
	private String cherry_blossom="277";
	private String ceibo_flower="271";
	private String african_violet="282";
	private String edelweiss="272";
	private String tribulus_omanese="385";
	private String peony="276";
	private String orchid="264";
	private String heather="267";
	private String banana_orchid="617";
	private String crocus="263";
	private int time_mexico=15;
	private int time_hawaii=86;
	private int time_united_kingdom=108;
	private int time_argentina=134;
	private int time_japan=143;
	private int time_china=154;
	private int time_cayman_islands=41;
	private int time_canada=27;
	private int time_dubai=182;
	private int time_south_africa=218;
	private int time_switzerland=119;
	
	// doRefresh('south');"
	
	public TornBot(TornUser user) 
	{
		//loadProperties();
		
		this.user = user;
		TornProxy proxy = user.getProxy();
		if (proxy == null)
			wc = new WebClient(BrowserVersion.FIREFOX_10);
		else {
			wc = new WebClient(BrowserVersion.FIREFOX_10, proxy.getAddress(),
					Integer.parseInt(proxy.getPort()));
			final DefaultCredentialsProvider scp = new DefaultCredentialsProvider();
			scp.addCredentials(proxy.getUsername(), proxy.getPassword());
			wc.setCredentialsProvider(scp);
		}
		// JavaScript throws major errors on site.
		wc.setJavaScriptEnabled(false);
		wc.setThrowExceptionOnScriptError(false);
		wc.setThrowExceptionOnFailingStatusCode(false);
		// Cookies may be unnecessary
		CookieManager cookieManager = new CookieManager();
		cookieManager.setCookiesEnabled(true);
		wc.setCookieManager(cookieManager);
		
		
		
	}
	
	public void loadProperties()
	{
		Properties flowerProperties = new Properties();
		Properties countryProperties = new Properties();
		Properties countryFlowerProperties = new Properties();
		
		try
		{
			flowerProperties.load(new FileInputStream("files/java-properties/item-id.properties"));
			countryProperties.load(new FileInputStream("files/java-properties/country-id.properties"));
			countryFlowerProperties.load(new FileInputStream("files/java-properties/country-flower.properties"));
			
			Map<String, String> map = new HashMap<String, String>((Map) countryProperties);
			
			//this.countryMap = new HashMap<String, String>((Map) countryProperties));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private HashMap<String, String> propertiesToMap(Properties properties)
	{
		HashMap<String, String> hashMap = new HashMap<String, String>();
		Map<String, String> map = new HashMap<String, String>((Map) properties);
		
		Set propertySet = map.entrySet();
        for (Object o : propertySet) 
        {
            Map.Entry entry = (Map.Entry) o;
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            hashMap.put(key,value);
        }
        return hashMap;
	}
	
	public enum Country 
	{
		MEXICO, CANADA, SWITZERLAND, CAYMAN_ISLANDS, DUBAI, HAWAII, UNITED_KINGDOM,
		ARGENTINA, JAPAN, CHINA, SOUTH_AFRICA
	}
	
	public enum Flower
	{
		DAHLIA, CROCUS, ORCHID, HEATHER, CEIBO_FLOWER, EDELWEISS, CHERRY_BLOSSOM,
		PEONY, AFRICAN_VIOLET, TRIBULUS_OMANESE
	}

	public HtmlPage travelToCanada(HtmlPage page) throws Exception {
		System.out.println("Traveling to Canada...");
		page = wc.getPage("http://www.torn.com/travelagency.php");
		if (onCaptcha(page))
		{
			solveCaptcha(page);
			page = wc.getPage("http://www.torn.com/travelagency.php");
		}
		page = wc.getPage("http://www.torn.com/holder.php?case=travelAgency&ID=9&action=confirm&jsoff=none&ID2=2");
		
		page = wc.getPage("http://www.torn.com/holder.php?case=travelAgency&ID=9&action=travel&jsoff=none&ID2=2&value=");
		return page;
	}
	
	public HtmlPage travelToMexico(HtmlPage page) throws Exception {
		System.out.println("Traveling to Mexico...");
		page = wc.getPage("http://www.torn.com/travelagency.php");
		if (onCaptcha(page))
		{
			solveCaptcha(page);
			page = wc.getPage("http://www.torn.com/travelagency.php");
		}
		page = wc.getPage("http://www.torn.com/holder.php?case=travelAgency&ID=2&action=confirm&jsoff=none&ID2=2");
		
		page = wc.getPage("http://www.torn.com/holder.php?case=travelAgency&ID=2&action=travel&jsoff=none&ID2=2&value=");
		return page;
	}
	
	public HtmlPage travelAbroad(HtmlPage page, String countryId) throws Exception {
		System.out.println("Traveling to Canada...");
		page = wc.getPage("http://www.torn.com/travelagency.php");
		//System.out.println(page.asXml());
		if (onCaptcha(page))
		{
			solveCaptcha(page);
			page = wc.getPage("http://www.torn.com/travelagency.php");
		}
		page = wc.getPage("http://www.torn.com/holder.php?case=travelAgency&ID="+countryId+"&action=confirm&jsoff=none&ID2=2");
		
		page = wc.getPage("http://www.torn.com/holder.php?case=travelAgency&ID="+countryId+"&action=travel&jsoff=none&ID2=2&value=");
		return page;
	}
	
	public HtmlPage buyFlowersCanada(HtmlPage page, int amount) throws Exception 
	{
		System.out.println("Buying flowers...");
		if (!isLoggedIn())
			login(page, user.getUsername(), user.getPassword());
		page = loadIndex();
		if (onCaptcha(page))
			solveCaptcha(page);
		page = wc
				.getPage("http://www.torn.com/holder.php?case=buyItem&ID=263&action=confirm&jsoff=none&ID2=");
		page = wc
				.getPage("http://www.torn.com/holder.php?case=buyItem&ID=263&action=confirm2&jsoff=none&ID2=&value=");
		// page =
		// wc.getPage("http://www.torn.com/holder.php?case=buyItem&ID=260&action=confirm3&jsoff=none&ID2=938&amount260=19");
		HtmlTextInput input = page.getElementByName("amount263");
		input.setValueAttribute(Integer.toString(amount));
		HtmlSubmitInput submit = (HtmlSubmitInput) page.getByXPath(
				"/html/body/div[3]/table/tbody/tr/td/div/center/div/input")
				.get(0);
		page = submit.click();
		return page;
	}
	
	public HtmlPage buyAnyFlowers(HtmlPage page, int amount, String flowerId) throws Exception 
	{
		System.out.println("Buying flowers...");
		if (!isLoggedIn())
			login(page, user.getUsername(), user.getPassword());
		page = loadIndex();
		if (onCaptcha(page))
			solveCaptcha(page);
		writeAbroad(page.asXml());
		page = wc
				.getPage("http://www.torn.com/holder.php?case=buyItem&ID="+flowerId+"&action=confirm&jsoff=none&ID2=");
		page = wc
				.getPage("http://www.torn.com/holder.php?case=buyItem&ID="+flowerId+"&action=confirm2&jsoff=none&ID2=&value=");
		// page =
		// wc.getPage("http://www.torn.com/holder.php?case=buyItem&ID=260&action=confirm3&jsoff=none&ID2=938&amount260=19");
		HtmlTextInput input = page.getElementByName("amount"+flowerId);
		input.setValueAttribute(Integer.toString(amount));
		HtmlSubmitInput submit = (HtmlSubmitInput) page.getByXPath(
				"/html/body/div[3]/table/tbody/tr/td/div/center/div/input")
				.get(0);
		page = submit.click();
		return page;
	}

	public HtmlPage buyFlowers(HtmlPage page, int amount) throws Exception 
	{
		System.out.println("Buying flowers...");
		if (!isLoggedIn())
			login(page, user.getUsername(), user.getPassword());
		page = loadIndex();
		if (onCaptcha(page))
			solveCaptcha(page);
		page = wc
				.getPage("http://www.torn.com/holder.php?case=buyItem&ID=260&action=confirm&jsoff=none&ID2=");
		page = wc
				.getPage("http://www.torn.com/holder.php?case=buyItem&ID=260&action=confirm2&jsoff=none&ID2=&value=");
		// page =
		// wc.getPage("http://www.torn.com/holder.php?case=buyItem&ID=260&action=confirm3&jsoff=none&ID2=938&amount260=19");
		HtmlTextInput input = page.getElementByName("amount260");
		input.setValueAttribute(Integer.toString(amount));
		HtmlSubmitInput submit = (HtmlSubmitInput) page.getByXPath(
				"/html/body/div[3]/table/tbody/tr/td/div/center/div/input")
				.get(0);
		page = submit.click();
		return page;
	}

	public HtmlPage travelHome(HtmlPage page) throws Exception {
		page = loadIndex();
		System.out.println("Traveling Home...");
		page = wc.getPage("http://www.torn.com/holder.php?case=travelAgency&ID=63&action=travelback&jsoff=none&ID2=");
		//page = wc.getPage("http://www.torn.com/holder.php?case=travelAgency&ID=2&action=travelback&jsoff=none&ID2=0&value=");
		page = wc.getPage("http://www.torn.com/holder.php?case=travelAgency&ID=2&action=travelback2&jsoff=none&ID2=0&value=");
		
		return page;
	}

	public HtmlPage runFlowers(HtmlPage page, int amount) throws Exception
	{
		page = travelToMexico(page);
		System.out.println("Sleeping 15 minutes...");
		Thread.sleep(1000*15*60);
		page = buyFlowers(page, 21);
		page = travelHome(page);
		System.out.println("Sleeping 15 minutes...");
		Thread.sleep(1000*15*60);

		return page;
	}
	
	public HtmlPage runAnyFlowers(HtmlPage page, int amount, String countryId, String flowerId, int time) throws Exception
	{
		page = travelAbroad(page, countryId);
		System.out.println("Sleeping "+time+" minutes...");
		Thread.sleep(1000*time*60);
		page = buyAnyFlowers(page, 21, flowerId);
		page = travelHome(page);
		System.out.println("Sleeping "+time+" minutes...");
		Thread.sleep(1000*time*60);

		return page;
	}
	
	public HtmlPage runFlowersCanada(HtmlPage page, int amount) throws Exception
	{
		page = travelToCanada(page);
		System.out.println("Sleeping 27 minutes...");
		Thread.sleep(1000*27*60);
		page = buyFlowersCanada(page, amount);
		page = travelHome(page);
		System.out.println("Sleeping 27 minutes...");
		Thread.sleep(1000*27*60);

		return page;
	}

	public boolean isLoggedIn() {
		return !loadIndex().asText().toLowerCase()
				.contains("you are no longer logged in.");
	}

	public void run()
	{
		int travel_time = this.time_argentina;
		String country_id = this.argentina;
		String flower_id = this.ceibo_flower;
		
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
		    while (shouldRun && this.errors < 4)
		    {
		    	loop++;
		    	System.out.println(this.user.getUsername()+"-"+"Beginning loop "+loop+" at "+timeDateFormat.format(new Date()));
		    	try
		    	{
	    		/*
		    		System.out.println(this.user.getUsername()+"-"+"Loading gym...");
		    		page = wc.getPage("http://torn.com/gym.php");
				    //System.out.println(this.user.getUsername()+"-"+page.getWebResponse().getContentAsString());
				    trainStrength(page, 20);
				    //doCrime(page, 5);
				    System.out.println(this.user.getUsername()+"-"+"Sleeping 30 minutes...");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.MINUTE, 30);
					System.out.println(this.user.getUsername()+"-"+"Waking at " + dateFormat.format(cal.getTime()));
				    this.errors = 0;
				    this.shouldRun = false;
					//Thread.sleep(1000*30*60);
				*/

		    		
		    		//System.out.println(this.user.getUsername()+"-"+page.getWebResponse().getContentAsString());
		    		page = loadIndex();
		    		if (!isLoggedIn())
				    	page = login(page, user.getUsername(), user.getPassword());
		    		//System.out.println(this.user.getUsername()+"-"+page.getWebResponse().getContentAsString());
		    		if (page.asXml().toLowerCase().contains("you are travelling..."))
		    			page = loadIndex();
		    		if (onCaptcha(page))
		    		{
		    			wc.setJavaScriptEnabled(true);
		    			page = loadIndex();
		    			page = solveCaptcha(page);
		    			wc.setJavaScriptEnabled(false);
		    		}
		    		else
		    		{
			    		if (page.asText().toLowerCase().contains(this.travelling))
			    		{	
			    			System.out.println("Currently travelling. Sleeping 5 mins...");
			    			Thread.sleep(1000*60*5);
			    		}
			    		else if (page.asText().toLowerCase().contains(this.atStoreAbroad))
			    		{
			    			/*
			    			System.out.println("At store abroad.");
			    			page = buyFlowers(page, 21);
			    			page = travelHome(page);
			    			System.out.println("Sleeping 15 minutes...");
			    			Thread.sleep(1000*15*60);
			    			*/
			    			System.out.println("At store abroad.");
			    			page = buyAnyFlowers(page, 21,flower_id);
			    			page = travelHome(page);
			    			System.out.println("Sleeping "+travel_time+" minutes...");
			    			Thread.sleep(1000*travel_time*60);
			    		}
			    		else if (page.asText().toLowerCase().contains(this.atHome))
			    		{
			    			writeProfile(page.asXml());
			    			writeItems(loadItems().asXml());
			    			trainDefence(page, 20);
			    			System.out.println("Starting new flower run");
			    			//page = runFlowers(page, 21);
			    			page = runAnyFlowers(page, 21,country_id,flower_id,travel_time);
			    		}
			    		else
			    		{
			    			System.out.println("ERROR. In unknown state");
			    			System.out.println(page.getWebResponse().getContentAsString());
			    			handlePageError(page);
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
		    		if (!handlePageError(page))
		    			this.errors++;
		    		if (this.errors > 3)
		    			abort();
		    	}
		    }
		    System.out.println(this.user.getUsername()+"-"+"Thread terminated!");
		    return;
		    
		}
		catch (Exception e)
		{
			System.out.println(this.user.getUsername()+"-"+e.toString());
			e.printStackTrace();
			abort();
		}
	}
	
	public HtmlPage loadItems() throws Exception
	{
		return wc.getPage("http://www.torn.com/item.php");
	}
	
	public void writeItems(String contents)
	{
		writePage("files/page-logs/items", contents);
	}
	
	public void writeAbroad(String contents)
	{
		writePage("files/page-logs/abroad", contents);
	}
	public void writeProfile(String contents)
	{
		writePage("files/page-logs/profile/", contents);
	}
	
	public void writePage(String dir, String contents)
	{
		String filename = dir+"/"+this.user.getUsername()+"/"+dayDateFormat.format(new Date())+"/"+timeDateFormat.format(new Date())+".html";
		writeFile(filename, contents);
	}
	
	public void writeFile(String filename, String contents)
	{
		try
		{
			// Create file
			File file = new File(filename);
			File dir = file.getParentFile();
			// Create the directory if it doesnt already exist
			dir.mkdirs();
			file.createNewFile();
			FileWriter fstream = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(contents);
			//Close the output stream
			out.close();
		}
		catch (Exception e)
		{
			//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public boolean handlePageError(HtmlPage page) throws Exception 
	{
		System.out.println("Attemping to determine and rectify state...");
		if (!isLoggedIn()) {
			login(page, user.getUsername(), user.getPassword());
			return true;
		} else if (inHospital(page)) {
			while (inHospital(page)) {
				System.out.println("You are in the hospital.");
				Thread.sleep(1000 * 60 * 5);
			}
			return true;
		} else if (inJail(page)) {
			while (inJail(page)) {
				System.out.println("You are in jail.");
			}
			return true;
		} else if (onCaptcha(page)) {
			while (onCaptcha(page)) {
				wc.setJavaScriptEnabled(true);
				System.out.println(this.user.getUsername() + "-"
						+ "Captcha encountered.");
				page = solveCaptcha(page);
				wc.setJavaScriptEnabled(false);
			}
			return true;
		} else
			return false;
	}

	public HtmlPage loadIndex() {
		System.out.println(this.user.getUsername() + "-" + "Loading Index...");
		try {
			return wc.getPage("http://torn.com/index.php");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean inHospital(HtmlPage page) {
		HtmlElement e = page.getElementById("icon15");
		if (e == null)
			return false;
		boolean result = e.getAttribute("title").toLowerCase()
				.contains("hospital");
		if (result)
			System.out.println(this.user.getUsername() + "-" + "In hospital.");
		return result;
	}

	public boolean inJail(HtmlPage page) {
		boolean result = (page.getElementById("icon16") != null);
		if (result)
			System.out.println(this.user.getUsername() + "-" + "In jail.");
		return result;

	}

	public boolean onCaptcha(HtmlPage page) 
	{
		return page.asXml().toLowerCase().contains("please validate"); 
	}

	public HtmlPage login(HtmlPage page, String username, String password) {
		// wc.setJavaScriptEnabled(true);
		int attempts = 0;
		boolean loggedIn = false;
		while (!loggedIn && shouldRun) {
			try {
				if (attempts > 3 || this.errors > 3)
					abort();
				attempts++;
				System.out.println(this.user.getUsername() + "-"
						+ "Logging in... (Attempt " + attempts + ")");
				page = wc.getPage("http://torn.com/login");
				// System.out.println(page.getWebResponse().getContentAsString());
				final HtmlForm form = page.getFormByName("login");
				HtmlSubmitInput button = form.getInputByName("btnLogin");
				HtmlTextInput textField = form.getInputByName("player");
				textField.setValueAttribute(username);
				final HtmlPasswordInput passwordField = form
						.getInputByName("password");
				passwordField.setValueAttribute(password);
				page = button.click();
				// System.out.println(page.getWebResponse().getContentAsString());
				if (isLoggedIn())
					loggedIn = true;
			} catch (Exception e) {
				System.out
						.println(this.user.getUsername() + "-" + e.toString());
				loggedIn = false;
			}
		}
		// wc.setJavaScriptEnabled(false);
		return page;

	}

	public HtmlPage trainStrength(HtmlPage page, int amount) throws IOException {
		System.out.println("Training " + amount + " strength...");
		// System.out.println(page.getWebResponse().getContentAsString());
		HtmlTextInput textInput = null;
		HtmlSubmitInput submitInput = null;
		for (DomNode n : page.getElementById("divStrength").getDescendants()) {
			// System.out.println(n.toString());
			if (n.toString().contains("id=\"t\" name=\"t\">]")) {
				textInput = (HtmlTextInput) n;
				textInput.setValueAttribute(Integer.toString(amount));
				// System.out.println(textInput.toString());
			} else if (n.toString().contains(
					"HtmlSubmitInput[<input type=\"submit\" value=\"Train\">]")) {
				submitInput = (HtmlSubmitInput) n;
				page = submitInput.click();
				// System.out.println(submitInput.toString());
			}
		}
		if (textInput == null)
			throw new ElementNotFoundException("trainStrength", "textInput",
					"HtmlTextInput[<input type=\"text\" value=\"1\" id=\"t\" name=\"t\">]");
		if (submitInput == null)
			throw new ElementNotFoundException("trainStrength", "submitInput",
					"HtmlSubmitInput[<input type=\"submit\" value=\"Train\">]");

		return page;
	}

	public HtmlPage trainDefence(HtmlPage page, int amount) throws Exception {
		System.out.println("Training " + amount + " defence...");
		page = wc.getPage("http://torn.com/gym.php");
		if (onCaptcha(page))
			page = solveCaptcha(page);
		// System.out.println(page.getWebResponse().getContentAsString());
		HtmlTextInput textInput = null;
		HtmlSubmitInput submitInput = null;
		for (DomNode n : page.getElementById("divDefence").getDescendants()) {
			// System.out.println(n.toString());
			if (n.toString().contains("id=\"t\" name=\"t\">]")) {
				textInput = (HtmlTextInput) n;
				textInput.setValueAttribute(Integer.toString(amount));
				// System.out.println(textInput.toString());
			} else if (n.toString().contains(
					"HtmlSubmitInput[<input type=\"submit\" value=\"Train\">]")) {
				submitInput = (HtmlSubmitInput) n;
				page = submitInput.click();
				// System.out.println(submitInput.toString());
			}
		}
		if (textInput == null)
			throw new ElementNotFoundException("trainDefence", "textInput",
					"HtmlTextInput[<input type=\"text\" value=\"1\" id=\"t\" name=\"t\">]");
		if (submitInput == null)
			throw new ElementNotFoundException("trainDefence", "submitInput",
					"HtmlSubmitInput[<input type=\"submit\" value=\"Train\">]");

		return page;
	}
	
	private HtmlPage solveCaptcha(HtmlPage page) throws Exception {
		wc.setJavaScriptEnabled(true);
		System.out.println(this.user.getUsername() + "-" + "Solving captcha...");
		String url = page.getUrl().toString();
		page = wc.getPage(url + "?bypass=1");
		//System.out.println(page.getWebResponse().getContentAsString());

		HtmlImage captchaImage = (HtmlImage) page.getElementById("recaptcha_image").getFirstChild();
		captchaImage.saveAs(new File("captcha-image.jpg"));

		
		CaptchaSolver captchaSolver = new CaptchaSolver(new SocketClient(
				"trialaccount", "Cappie1!"), "captcha-image.jpg");
		captchaSolver.run();
		Captcha captcha = captchaSolver.getCaptcha();

		HtmlTextInput textField = (HtmlTextInput) page
				.getElementById("recaptcha_response_field");
		textField.setValueAttribute(captcha.text);
		HtmlElement element = (HtmlElement) page.getElementByName("submit");
		page = element.click();
		System.out.println(this.user.getUsername() + "-" + "Captcha entered.");
		wc.setJavaScriptEnabled(false);
		return wc.getPage(url);
	}

	public void abort() {
		System.out.println(this.user.getUsername() + "-"
				+ "Too many errors encountered. Aborting all operations");
		wc.closeAllWindows();
		this.shouldRun = false;
	}
}
