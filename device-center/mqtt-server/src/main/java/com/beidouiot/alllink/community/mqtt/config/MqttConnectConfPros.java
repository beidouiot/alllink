package com.beidouiot.alllink.community.mqtt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix = "mqtt")
public class MqttConnectConfPros {

    private String hostUrl;
    private String username;
    private String password;
    private String clientId;
    private Integer keepalive;
    private Integer timeout;
    private String willTopic;

    private Boolean sslEnable;
    private String caFile;
    private String certificateFile;
    private String keyFile;
}
