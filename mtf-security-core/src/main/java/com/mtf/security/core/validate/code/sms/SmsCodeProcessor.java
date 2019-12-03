package com.mtf.security.core.validate.code.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.mtf.security.core.properties.SecurityConstants;
import com.mtf.security.core.validate.code.ValidateCode;
import com.mtf.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import com.mtf.security.core.validate.code.sms.SmsCodeSender;

/** 
* @author Bill
* @date 2019年11月29日
*
*/
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		//String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		smsCodeSender.send(mobile, validateCode.getCode());
	}

}
