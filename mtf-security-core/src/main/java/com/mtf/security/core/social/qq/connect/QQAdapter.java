package com.mtf.security.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.mtf.security.core.social.qq.api.QQ;
import com.mtf.security.core.social.qq.api.QQUserInfo;

/**
 * 	在服务提供商qq和第三方应用之间做用户信息的转换
 *  ClassName: QQAdapter 
 *  @Description: 
 *	在服务提供商qq和第三方应用之间做用户信息的转换
 * 	实现 ApiAdapter接口，泛型是API接口，对应我们的QQ接口
 *  @author Bill
 *  @date 2019年12月4日
 */
public class QQAdapter implements ApiAdapter<QQ> {

	/**
     *	 测试当前api是否可用，测试qq是否可用
     */
	@Override
	public boolean test(QQ api) {
		//就不掉了，直接true
		return true;
	}

	/**
     *  Connection和api之间的适配
     * 	ConnectionValues:
     *	创建Connection需要的数据项
     *	从api中获取数据，给ConnectionValues设置值
     */
	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
			QQUserInfo userInfo;
			userInfo = api.getUserInfo();
			values.setDisplayName(userInfo.getNickname());
			values.setImageUrl(userInfo.getFigureurl_qq_1());
			values.setProfileUrl(null);
			values.setProviderUserId(userInfo.getOpenId());

	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		// do noting
	}

}
