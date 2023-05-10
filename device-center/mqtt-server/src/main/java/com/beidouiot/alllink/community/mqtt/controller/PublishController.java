package com.beidouiot.alllink.community.mqtt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.mqtt.publish.MqttPublisher;

@RestController
@RequestMapping(value = ServiceConstants.MQTT_PUBLISH_URI_BASE, produces = "application/json; charset=UTF-8")
public class PublishController {

//	@Autowired
//	private MqttGateway mqttGateway;
	
	@Autowired
	private MqttPublisher mqttPublisher;
	
	@PostMapping(value = "v1/send")
	public String publish(@RequestBody JSONObject jsonObject) {
		String topic = jsonObject.get("topic").toString();
		JSONObject msgObj = jsonObject.getJSONObject("message");
		mqttPublisher.publish(1, true, topic, msgObj.toJSONString());
		
		return "OK";
	}

}
