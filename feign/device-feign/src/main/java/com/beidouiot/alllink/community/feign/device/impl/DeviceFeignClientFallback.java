package com.beidouiot.alllink.community.feign.device.impl;

import org.springframework.http.ResponseEntity;

import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceDataAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.feign.device.DeviceFeignClient;

public class DeviceFeignClientFallback implements DeviceFeignClient {

	@Override
	public ResponseEntity<ResultDataRro<Object>> add(DeviceDataAddRpo deviceDataAddRpo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
