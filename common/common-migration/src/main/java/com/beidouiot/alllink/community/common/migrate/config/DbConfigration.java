package com.beidouiot.alllink.community.common.migrate.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;

public class DbConfigration implements Serializable{

	private static final long serialVersionUID = 5627979508112061405L;
	
	@Value("${spring.datasource.url}")
	public String mysqlUrl;
	
	@Value("${spring.datasource.username}")
	public String mysqlUserName;
	
	@Value("${spring.datasource.password}")
	public String mysqlPassword;
	
	@Value("${spring.datasource.driver-class-name}")
	public String mysqlDriverClassName;

}
