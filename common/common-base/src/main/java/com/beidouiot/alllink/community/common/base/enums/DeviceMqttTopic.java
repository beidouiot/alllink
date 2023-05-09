package com.beidouiot.alllink.community.common.base.enums;

public enum DeviceMqttTopic {

	PROPERTIES_UP("${productName}/${deviceName}/properties/up","属性上报"),
	PROPERTIES_DOWN("${productName}/${deviceName}/properties/down","属性下发"),
	EVENTS_UP("${productName}/${deviceName}/events/up","事件上报"),
	EVENT_DOWN("${productName}/${deviceName}/events/down","事件下发"),
	COMMANDS_UP("${productName}/${deviceName}/commands/up","指令上报"),
	COMMANDS_DOWN("${productName}/${deviceName}/commands/down","指令下发");
	
	
	
	
	
    private DeviceMqttTopic(String topic, String remark) {
		this.topic = topic;
		this.remark = remark;
	}
    
	public String getTopic() {
		return topic;
	}
	public String getRemark() {
		return remark;
	}
	private String topic;
    private String remark;
    
}
