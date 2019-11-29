package com.mtf.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/** 
* @author Bill
* @date 2019年11月28日
*
*/
public interface ValidateCodeGenerator {

//	ImageCode generate(ServletWebRequest request);
	ValidateCode generate(ServletWebRequest request);
}
