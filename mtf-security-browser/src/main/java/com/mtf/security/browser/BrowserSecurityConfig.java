package com.mtf.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import com.mtf.security.core.authentication.AbstractChannelSecurityConfig;
import com.mtf.security.core.authentication.sms.SmsCodeAuthenticationSecurityConfig;
import com.mtf.security.core.properties.SecurityConstants;
import com.mtf.security.core.properties.SecurityProperties;
import com.mtf.security.core.validate.code.ValidateCodeSecurityConfig;

/**
 * @author Bill
 * @date 2019年11月28日
 *
 */
@Configuration
//public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private AuthenticationSuccessHandler mtfAuthenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler mtfAuthenticationFailureHandler;

	//@Autowired
	private SpringSocialConfigurer mtfSocialSecurityConfig = new SpringSocialConfigurer();
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

//	@Autowired
//	private TempConfig tempConfig;
	
	protected void configure(HttpSecurity http) throws Exception {

//		 //~~~-------------> 图片验证码过滤器 <------------------
//        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        //验证码过滤器中使用自己的错误处理
//        validateCodeFilter.setAuthenticationFailureHandler(mtfAuthenticationFailureHandler);
//        //配置的验证码过滤url
//        validateCodeFilter.setSecurityProperties(securityProperties);
//        validateCodeFilter.afterPropertiesSet();
        
        //~~~-------------> 短信验证码过滤器 <------------------
//        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        //验证码过滤器中使用自己的错误处理
//        smsCodeFilter.setAuthenticationFailureHandler(mtfAuthenticationFailureHandler);
//        //配置的验证码过滤url
//        smsCodeFilter.setSecurityProperties(securityProperties);
//        smsCodeFilter.afterPropertiesSet();
        
        applyPasswordAuthenticationConfig(http);
		
		http.apply(validateCodeSecurityConfig)
				.and()
			.apply(smsCodeAuthenticationSecurityConfig)
			.and()
			.apply(mtfSocialSecurityConfig)
//			.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)// 把验证码过滤器加载登录过滤器前边
//			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//			.formLogin()
//			.loginPage("/authentication/require").loginProcessingUrl("/authentication/mobile")
//			.successHandler(mtfAuthenticationSuccessHandler).failureHandler(mtfAuthenticationFailureHandler)
			.and().rememberMe().tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
			.userDetailsService(userDetailsService)

			.and().authorizeRequests()
			.antMatchers(
					SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
					SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
					securityProperties.getBrowser().getLoginPage()
					, "/code/*"
					).permitAll()
					 .anyRequest()
					 .authenticated()
					 .and().csrf().disable()
			.apply(smsCodeAuthenticationSecurityConfig);//把短信验证码配置应用上
					 

		
//		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//		validateCodeFilter.setAuthenticationFailureHandler(mtfAuthenticationFailureHandler);
//		validateCodeFilter.setSecurityProperties(securityProperties);
//		validateCodeFilter.afterPropertiesSet();
//		
//		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
//		smsCodeFilter.setAuthenticationFailureHandler(mtfAuthenticationFailureHandler);
//		smsCodeFilter.setSecurityProperties(securityProperties);
//		smsCodeFilter.afterPropertiesSet();
		
//		http.apply(tempConfig);
//		
//		http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
//			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
