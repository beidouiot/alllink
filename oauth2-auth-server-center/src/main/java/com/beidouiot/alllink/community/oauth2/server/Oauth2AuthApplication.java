package com.beidouiot.alllink.community.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 *
 * @Description 认证中心
 * @author longww
 * @date 2021年4月11日
 */
@EnableCaching
@EntityScan("com.beidouiot.alllink.community.common.data.entity.user.center")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "com.beidouiot.alllink.community" })
@EnableFeignClients(basePackages = {"com.beidouiot.alllink.community.**.feign"})
public class Oauth2AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthApplication.class, args);
	}

}
