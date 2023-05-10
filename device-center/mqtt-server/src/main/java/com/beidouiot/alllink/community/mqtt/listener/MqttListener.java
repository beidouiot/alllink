package com.beidouiot.alllink.community.mqtt.listener;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttListener {

    /**
     * consume mqtt msg
     *
     * @param mqttMessage msg
     */
    void consume(MqttMessage mqttMessage);
}
