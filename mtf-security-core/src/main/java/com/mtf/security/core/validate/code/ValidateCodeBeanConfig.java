package com.mtf.security.core.validate.code;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 
* @author Bill
* @date 2019年11月28日
*
*/
@Configuration
public class ValidateCodeBeanConfig {

	@Bean
	public ValidateCodeGenerator imageCodeGenerator() {
		return new DefaultImageCodeGenerator();
	}
	
}
