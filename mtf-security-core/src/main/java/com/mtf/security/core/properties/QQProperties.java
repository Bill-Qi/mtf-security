package com.mtf.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/** 
* @author Bill
* @date 2019年12月4日
*
*/
public class QQProperties extends SocialProperties {
	
	private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
