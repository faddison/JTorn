package com.jtorn.bot;

public class TornUser 
{
	private String username;
	private String password;
	private String email;
	private TornProxy proxy;
	
	/**
	 * @param username
	 * @param password
	 * @param email
	 * @param proxy
	 */
	public TornUser(String username, String password, String email,
			TornProxy proxy) 
	{
		this.username = username;
		this.password = password;
		this.email = email;
		this.proxy = proxy;
	}

	/**
	 * @return the username
	 */
	private String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	private void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	private String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	private void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	private String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	private void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the proxy
	 */
	private TornProxy getProxy() {
		return proxy;
	}

	/**
	 * @param proxy the proxy to set
	 */
	private void setProxy(TornProxy proxy) {
		this.proxy = proxy;
	}
	
	
}
