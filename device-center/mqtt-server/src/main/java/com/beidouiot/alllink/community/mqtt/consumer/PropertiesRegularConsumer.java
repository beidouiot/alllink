package com.beidouiot.alllink.community.mqtt.consumer;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.feign.device.DeviceDataFeignClient;
import com.beidouiot.alllink.community.mqtt.listener.RegularMqttListener;
import com.beidouiot.alllink.community.mqtt.listener.RegularMqttSubscription;

@RegularMqttSubscription(topic = "+/+/properties/up")
public class PropertiesRegularConsumer implements RegularMqttListener {

	@Autowired
	private DeviceDataFeignClient deviceDataFeignClient;
    @Override
    public void consume(String topic, MqttMessage mqttMessage) {
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("data",JSONObject.parseObject(new String(mqttMessage.getPayload())));
    	deviceDataFeignClient.receivedProperties(jsonObj);
    }
}
