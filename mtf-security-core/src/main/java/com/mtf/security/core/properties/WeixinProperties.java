package com.mtf.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/** 
* @author Bill
* @date 2019年12月30日
*
*/
public class WeixinProperties extends SocialProperties {
	/**
	 * 	第三方id，用来决定发起第三方登录的url，默认是 weixin。
	 */
	private String providerId = "weixin";

	/**
	 * @return the providerId
	 */
	public String getProviderId() {
		return providerId;
	}

	/**
	 * @param providerId the providerId to set
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}