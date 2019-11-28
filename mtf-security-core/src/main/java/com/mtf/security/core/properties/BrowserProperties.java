package com.mtf.security.core.properties;
/** 
* @author Bill
* @date 2019年11月28日
*
*/
public class BrowserProperties {
	
	private String loginPage = "/login.html";
	
	private LoginType loginType = LoginType.JSON;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	
	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}
}
