package com.mtf.security.core.social.weixin.api;
/** 
* @author Bill
* @date 2019年12月30日
*
*/
public interface Weixin {
	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.security.social.api.SocialUserProfileService#getUserProfile(java.lang.String)
	 */
	WeixinUserInfo getUserInfo(String openId);
}
