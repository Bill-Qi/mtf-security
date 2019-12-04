package com.mtf.security.core.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.mtf.security.core.properties.QQProperties;
import com.mtf.security.core.properties.SecurityProperties;
import com.mtf.security.core.social.qq.connect.QQConnectionFactory;

/**
 * 
 * 	ClassName: QQAutoConfig 
 * 	@Description: 
 *	@ConditionalOnProperty: 配置里有mtf.security.social.qq.app-id 这个类才会生效
 *  @author Bill
 *	@date 2019年12月4日
 */
@Configuration
@ConditionalOnProperty(prefix = "mtf.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
	 * #createConnectionFactory()
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}

}
