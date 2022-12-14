package com.beidouiot.alllink.community.device.service.dao.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.influxdb.LogLevel;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;

@Configuration
public class InfluxDBIniti {
	
	@Value("${influx.token}")
	private String token;
	
	@Value("${influx.url}")
	private String url;
	
	@Bean
	public InfluxDBClient influxDBClient() {
		InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray());
		client.setLogLevel(LogLevel.BASIC);
		return client;
	}
	
}
