package com.beidouiot.alllink.community.mqtt.consumer;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.beidouiot.alllink.community.mqtt.listener.MqttListener;
import com.beidouiot.alllink.community.mqtt.listener.MqttSubscription;

@MqttSubscription(topic = "test")
public class VoidConsumer implements MqttListener {

    @Override
    public void consume(MqttMessage mqttMessage) {
    	System.out.println("===============*****start******===============");
    	System.out.println(new String(mqttMessage.getPayload()));
    	System.out.println("===============*****end******===============");
    }
}
