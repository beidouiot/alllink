package com.beidouiot.alllink.community.feign.device;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceDataAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.feign.device.impl.DeviceFeignClientFallback;


@FeignClient(value = ServiceConstants.DEVICE_CENTER_SERVER, fallback = DeviceFeignClientFallback.class)
public interface DeviceFeignClient {

	@PostMapping(ServiceConstants.DEVICE_DATA_URI_BASE+"v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(DeviceDataAddRpo deviceDataAddRpo);
}
