package com.mtf.security.browser.support;

/**
 * @author Bill
 * @date 2019年11月28日
 *
 */
public class SimpleResponse {
	
	public SimpleResponse(Object content) {
		this.content = content;
	}

	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
