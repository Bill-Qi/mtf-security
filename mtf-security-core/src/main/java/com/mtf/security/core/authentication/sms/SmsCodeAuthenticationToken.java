package com.mtf.security.core.authentication.sms;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/** 
* @author Bill
* @date 2019年11月29日
*
*/
public class SmsCodeAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 7772703991322484628L;

	public SmsCodeAuthenticationToken(Object principal) {
		super(principal, null);
	}
}
