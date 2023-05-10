package com.beidouiot.alllink.community.mqtt.listener;

import org.eclipse.paho.client.mqttv3.MqttMessage;


public interface RegularMqttListener {

    /**
     * consume mqtt msg
     *
     * @param topic       topic
     * @param mqttMessage msg
     */
    void consume(String topic, MqttMessage mqttMessage);
}
