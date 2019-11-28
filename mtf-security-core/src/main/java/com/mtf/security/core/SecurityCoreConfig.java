package com.mtf.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import com.mtf.security.core.properties.SecurityProperties;

/** 
* @author Bill
* @date 2019年11月28日
*
*/
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
