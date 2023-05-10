package com.beidouiot.alllink.community.mqtt.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beidouiot.alllink.community.mqtt.listener.MqttBatchListener;
import com.beidouiot.alllink.community.mqtt.listener.MqttListener;
import com.beidouiot.alllink.community.mqtt.listener.MqttSubscription;
import com.beidouiot.alllink.community.mqtt.listener.MqttSubscriptionOnMethod;
import com.beidouiot.alllink.community.mqtt.listener.RegularMqttListener;
import com.beidouiot.alllink.community.mqtt.listener.RegularMqttSubscription;
import com.beidouiot.alllink.community.mqtt.listener.RegularMqttSubscriptionOnMethod;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MqttConnectConf {

    @Resource
    private MqttConnectConfPros mqttConnectConfPros;
    @Resource
    private MqttCallback mqttCallback;
    @Resource
    private Map<String, MqttListener> consumers;
    @Resource
    private Map<String, MqttBatchListener> batchConsumers;
    @Resource
    private Map<String, RegularMqttListener> regularConsumers;

    /**
     * 初始化mqtt链接
     *
     * @return mqttClient
     */
    @Bean("mqttClient")
    public MqttClient connect() {

        try {
            MqttClient mqttClient = new MqttClient(
                    mqttConnectConfPros.getHostUrl(),
                    mqttConnectConfPros.getClientId(),
                    new MemoryPersistence()
            );
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(mqttConnectConfPros.getUsername());
            options.setPassword(mqttConnectConfPros.getPassword().toCharArray());
            options.setCleanSession(true);
            options.setKeepAliveInterval(mqttConnectConfPros.getKeepalive());
            options.setConnectionTimeout(mqttConnectConfPros.getTimeout());
            options.setWill(mqttConnectConfPros.getWillTopic(), (mqttConnectConfPros.getClientId() + "与服务器断开连接").getBytes(), 0, false);

            mqttClient.setCallback(mqttCallback);
            mqttClient.connect(options);
            String[] topics = getTopics();
            mqttClient.subscribe(topics);
            return mqttClient;
        } catch (Exception e) {
            log.error("Mqtt connect error", e);
            throw new RuntimeException("Connect Mqtt Failed");
        }
    }


    /**
     * 获取topics topics 来自Consumer的注解
     *
     * @return topic 数组
     */
    private String[] getTopics() {

        List<String> topicList = new ArrayList<>();
        consumers.values().forEach(consumer -> {
            MqttSubscription mqttSubscription = consumer.getClass().getAnnotation(MqttSubscription.class);
            if (mqttSubscription != null) {
                topicList.add(mqttSubscription.topic());
            }
        });
        regularConsumers.values().forEach(consumer -> {
            RegularMqttSubscription regularMqttSubscription = consumer.getClass().getAnnotation(RegularMqttSubscription.class);
            if (regularMqttSubscription != null) {
                topicList.add(regularMqttSubscription.topic());
            }
        });
        batchConsumers.values().forEach(consumer -> {
            Method[] methods = consumer.getClass().getMethods();
            for (Method method : methods) {
                MqttSubscriptionOnMethod mqttSubscriptionOnMethod = method.getAnnotation(MqttSubscriptionOnMethod.class);
                if (mqttSubscriptionOnMethod != null) {
                    topicList.add(mqttSubscriptionOnMethod.topic());
                }
                RegularMqttSubscriptionOnMethod regularMqttSubscriptionOnMethod = method.getAnnotation(RegularMqttSubscriptionOnMethod.class);
                if (regularMqttSubscriptionOnMethod != null) {
                    topicList.add(regularMqttSubscriptionOnMethod.topic());
                }
            }
        });
        return topicList.toArray(new String[]{});
    }
}
