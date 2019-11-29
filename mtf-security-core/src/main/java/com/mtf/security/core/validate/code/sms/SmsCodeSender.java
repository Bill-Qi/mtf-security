package com.mtf.security.core.validate.code.sms;
/** 
* @author Bill
* @date 2019年11月29日
*
*/
public interface SmsCodeSender {

	void send(String mobile, String code);
	
}
