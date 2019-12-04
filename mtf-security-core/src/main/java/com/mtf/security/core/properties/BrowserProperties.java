package com.mtf.security.core.properties;
/** 
* @author Bill
* @date 2019年11月28日
*
*/
public class BrowserProperties {
	
//	private String loginPage = "/login.html";
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	
	private LoginResponseType loginType = LoginResponseType.JSON;
	
	private int rememberMeSeconds = 3600;
	
	private String signUpUrl = "/imooc-signUp.html";

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	
	public LoginResponseType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginResponseType loginType) {
		this.loginType = loginType;
	}
	
	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}
	
	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}
}
