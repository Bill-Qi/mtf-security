package com.mtf.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mtf.security.core.properties.SecurityProperties;

/** 
* @author Bill
* @date 2019年11月28日
*
*/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()
			.loginPage("/authentication/require")
			.loginProcessingUrl("/authentication/form")
//		http.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers("/authentication/require",
					securityProperties.getBrowser().getLoginPage()).permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable();
		
	}
	
}
