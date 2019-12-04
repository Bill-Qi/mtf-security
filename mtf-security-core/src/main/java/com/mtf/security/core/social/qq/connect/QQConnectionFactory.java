package com.mtf.security.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

import com.mtf.security.core.social.qq.api.QQ;

/** 
* @author Bill
* @date 2019年12月4日
*
*/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	 /**
	 * 	 需要两个对象：
     *     1，ServiceProvider --> QQServiceProvider
     *     2，ApiAdapter      --> QQApiAdapter
     * <p>Description: </p>
     * @param providerId 
     * @param appId
     * @param appSecret
     */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
