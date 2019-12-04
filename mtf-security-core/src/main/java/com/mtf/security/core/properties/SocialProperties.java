package com.mtf.security.core.properties;
/** 
* @author Bill
* @date 2019年12月4日
*
*/
public class SocialProperties {
	
	private String filterProcessesUrl = "/auth";
	
	private QQProperties qq = new QQProperties();

	public QQProperties getQq() {
		return qq;
	}

	public void setQq(QQProperties qq) {
		this.qq = qq;
	}
	
	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
}
