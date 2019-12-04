package com.mtf.security.core.social.qq.api;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Bill
 * @date 2019年12月4日
 *
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

	private String appId;

	private String openId;

	private ObjectMapper objectMapper = new ObjectMapper();

	public QQImpl(String accessToken, String appId) {
		//父类默认构造会把accessToken放在请求头里，这是不符合qq要求的放在url参数里的，所以掉一下作为参数的构造
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

		this.appId = appId;

		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);//调用父类的restTemplate发请求，获取openid

		System.out.println(result);
		//{"client_id":"YOUR_APPID","openid":"YOUR_OPENID"}
        //截取openid
		this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.imooc.security.core.social.qq.api.QQ#getUserInfo()
	 */
	@Override
	public QQUserInfo getUserInfo() {
		//accessToken已在父类挂在了参数
		String url = String.format(URL_GET_USERINFO, appId, openId);
		String result = getRestTemplate().getForObject(url, String.class);

		System.out.println(result);

		try {
			return objectMapper.readValue(result, QQUserInfo.class);
		} catch (Exception e) {
			throw new RuntimeException("获取用户信息失败", e);
		}
		
		//return objectMapper.readValue(result, QQUserInfo.class);
	}

}
