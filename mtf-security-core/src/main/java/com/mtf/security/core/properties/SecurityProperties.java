package com.mtf.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** 
* @author Bill
* @date 2019年11月28日
*
*/
@ConfigurationProperties(prefix = "mtf.security")
public class SecurityProperties {
	private BrowserProperties browser = new BrowserProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
}
