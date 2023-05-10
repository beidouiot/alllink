package com.beidouiot.alllink.community.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableCaching
@EnableFeignClients(basePackages = {"com.beidouiot.alllink.community.feign"})
@EnableOpenApi
@SpringBootApplication(scanBasePackages = {"com.beidouiot.alllink.community"})
//@EnableJpaAuditing
@EnableDiscoveryClient
public class MqttServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqttServerApplication.class, args);
	}

}
