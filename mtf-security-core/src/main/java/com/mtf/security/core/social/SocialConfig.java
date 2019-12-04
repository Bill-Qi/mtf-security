package com.mtf.security.core.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

/** 
* @author Bill
* @date 2019年12月4日
*
*/
@Configuration
@EnableSocial //把Spring Social相关的特性启动
public class SocialConfig extends SocialConfigurerAdapter {
	
	@Autowired
    private DataSource dataSource;
    
    /**
     * 	配置JdbcUsersConnectionRepository
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
    
        /**
         * 	参数：
         *  1, dataSource：数据源 注进来
         *  2，connectionFactoryLocator：
         *	根据条件去查找需要的 ConnectionFactory，因为系统里可能有多个ConnectionFactory，如qq，微信等，使用默认穿的
         *  3，textEncryptor：把插入到数据库的数据加密解密
         */
//        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, 
//        			Encryptors.noOpText());//先不加密
    	JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource
    			, connectionFactoryLocator
    			, Encryptors.noOpText());
        repository.setTablePrefix("mtf_");
        return repository;
    }
}

