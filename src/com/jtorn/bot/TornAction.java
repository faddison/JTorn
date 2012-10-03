package com.jtorn.bot;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class TornAction{
	
	private LoginPage pgLogin;
	private GymPage pgGym;
	private IndexPage pgIndex;
	private WebClient webClient;
	private HtmlPage htmlPage;
	private boolean onCaptcha;
	
	public TornAction(WebClient webClient)
	{
		this.webClient = webClient;
	}
	
	boolean verifyCaptcha(){
		HtmlElement htmlElement = (HtmlElement) htmlPage.getElementById("recaptcha_widget_div");
		if(htmlElement != null){
			onCaptcha = true;
		}
		return onCaptcha;
	}
	
	//login
	void login(String username, String pw) {
		pgLogin = new LoginPage(webClient);
		this.htmlPage = pgLogin.getHtmlPage();

		HtmlForm form = htmlPage.getFormByName("login");
		form.getInputByName("player").setValueAttribute(username);
		form.getInputByName("password").setValueAttribute(pw);

		HtmlSubmitInput loginButton = form.getInputByName("btnLogin");
		try {
			this.htmlPage = loginButton.click();
			goToHomePage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void goToHomePage(){
		pgIndex = new IndexPage(webClient);
		this.htmlPage = pgIndex.getHtmlPage();
		if(verifyCaptcha()){
			//TODO Captcha Instructions
			System.out.println("Captcha Page!");
		}
	}

	void trainStrength(String amount) throws IOException{
		pgGym = new GymPage(webClient);
		pgGym.gotoUrl();
		this.htmlPage = pgGym.getHtmlPage();
		// TODO Verify captcha
		if(verifyCaptcha()){
			//Captcha Code Solving
			System.out.println("Captcha Page!");
		}
		HtmlTextInput strengthTrain = (HtmlTextInput) this.htmlPage.getElementById("secondvaluea");
		if(strengthTrain !=null){
	    	strengthTrain.setValueAttribute(amount);
	    	HtmlElement htmlElement1 = (HtmlElement) htmlPage.getByXPath("/html/body/div[4]/table/tbody/tr/td[2]/center/div/form/div/div[2]/div[3]/div/div/div[2]/table/tbody/tr/td[4]/a").get(0);
    		htmlElement1.click();
		}	
		else{
			System.out.println(htmlPage.asText());
		}
	}
	
	void trainDefense(String amount) throws IOException{
		pgGym = new GymPage(webClient);
		pgGym.gotoUrl();
		this.htmlPage = pgGym.getHtmlPage();
		// TODO Verify captcha
		if(verifyCaptcha()){
			//Captcha Code Solving
			System.out.println("Captcha Page!");
		}
		HtmlTextInput defenseTrain = (HtmlTextInput) this.htmlPage.getElementById("secondvalueb");
		if(defenseTrain!=null){
			defenseTrain.setValueAttribute(amount);
    		HtmlElement htmlElement2 = (HtmlElement) htmlPage.getByXPath("/html/body/div[4]/table/tbody/tr/td[2]/center/div/form/div/div[2]/div[3]/div[2]/div/div[2]/table/tbody/tr/td[3]/a").get(0);
    		htmlElement2.click();
		} 	
		else{
			System.out.println(htmlPage.asText());
		}
	}
	
	void trainSpeed(String amount) throws IOException{
		pgGym = new GymPage(webClient);
		pgGym.gotoUrl();
		this.htmlPage = pgGym.getHtmlPage();
		// TODO Verify captcha
		if(verifyCaptcha()){
			//Captcha Code Solving
			System.out.println("Captcha Page!");
		}
		HtmlTextInput speedTrain = (HtmlTextInput) this.htmlPage.getElementById("secondvaluec");
		if(speedTrain!=null){
			speedTrain.setValueAttribute(amount);
    		HtmlElement htmlElement3 = (HtmlElement) htmlPage.getByXPath("/html/body/div[4]/table/tbody/tr/td[2]/center/div/form/div/div[2]/div[3]/div/div[2]/div[2]/table/tbody/tr/td[3]/a").get(0);
    		htmlElement3.click();
		}	
		else{
			System.out.println(htmlPage.asText());
		}
	}
	
	void trainDexterity(String amount) throws IOException{
		pgGym = new GymPage(webClient);
		pgGym.gotoUrl();
		this.htmlPage = pgGym.getHtmlPage();
		// TODO Verify captcha
		if(verifyCaptcha()){
			//Captcha Code Solving
			System.out.println("Captcha Page!");
		}
		HtmlTextInput dexterityTrain = (HtmlTextInput) this.htmlPage.getElementById("secondvalued");
		if(dexterityTrain != null){
			dexterityTrain.setValueAttribute(amount);
			HtmlElement htmlElement4 = (HtmlElement) htmlPage.getByXPath("/html/body/div[4]/table/tbody/tr/td[2]/center/div/form/div/div[2]/div[3]/div[2]/div[2]/div[2]/table/tbody/tr/td[3]/a").get(0);
    			htmlElement4.click();
		}	  
		else{
			System.out.println(htmlPage.asText());
			}
	}
	
	void searchDump(){
		
	}

	public WebClient getWebClient() {
		return webClient;
	}

	public void setWebClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public HtmlPage getHtmlPage() {
		return htmlPage;
	}

	public void setHtmlPage(HtmlPage htmlPage) {
		this.htmlPage = htmlPage;
	}

}