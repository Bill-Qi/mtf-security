package com.mtf.security.core.validate.code.sms;
/** 
* @author Bill
* @date 2019年11月29日
*
*/
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		System.out.println("向手机"+mobile+"发送短信验证码"+code);
	}

}
