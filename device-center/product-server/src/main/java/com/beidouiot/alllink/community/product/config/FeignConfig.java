package com.beidouiot.alllink.community.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * 
 *
 * @Description Feign配置
 * @author longww
 * @date 2021年4月11日
 */
@Configuration
public class FeignConfig {
	
    @Bean
    Logger.Level feignLoggerLevel(){

        return Logger.Level.FULL;
    }
}
