package com.beidouiot.alllink.community.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableCaching
@EnableFeignClients(basePackages = {"com.beidouiot.alllink.community.feign"})
@EnableOpenApi
@SpringBootApplication(scanBasePackages = {"com.beidouiot.alllink.community"})
@EnableJpaAuditing
@EnableDiscoveryClient
@EntityScan("com.beidouiot.alllink.community.common.data.entity.device")
public class DeviceServerApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(DeviceServerApplication.class, args);
	}
}
