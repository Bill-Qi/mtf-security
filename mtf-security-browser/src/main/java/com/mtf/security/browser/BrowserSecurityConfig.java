package com.mtf.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.mtf.security.core.properties.SecurityProperties;
import com.mtf.security.core.validate.code.ValidateCodeFilter;

/**
 * @author Bill
 * @date 2019年11月28日
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private AuthenticationSuccessHandler mtfAuthenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler mtfAuthenticationFailureHandler;

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// 验证码过滤器
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		// 验证码过滤器中使用自己的错误处理
		validateCodeFilter.setAuthenticationFailureHandler(mtfAuthenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();

		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)// 把验证码过滤器加载登录过滤器前边
				.formLogin().loginPage("/authentication/require").loginProcessingUrl("/authentication/form")
				.successHandler(mtfAuthenticationSuccessHandler).failureHandler(mtfAuthenticationFailureHandler)
//		http.httpBasic()
				.and().rememberMe().tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)

				.and().authorizeRequests()
				.antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage(), "/code/image")
				.permitAll().anyRequest().authenticated().and().csrf().disable();

	}

}
