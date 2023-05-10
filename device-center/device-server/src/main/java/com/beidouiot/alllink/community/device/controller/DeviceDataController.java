package com.beidouiot.alllink.community.device.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.controller.BaseController;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceDataAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.device.dao.service.api.DeviceDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "设备数据")
@RestController
@RequestMapping(value = ServiceConstants.DEVICE_DATA_URI_BASE, produces = "application/json; charset=UTF-8")
public class DeviceDataController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceDataController.class);
	
	@Autowired
	private DeviceDataService deviceDataService;
	
	@ApiOperation(value = "设备数据添加", notes = "设备数据添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增设备数据", value = "设备数据请求参数", required = true) @Valid DeviceDataAddRpo deviceDataAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("deviceDataAddRpo= [ {} ]", deviceDataAddRpo);
		}
		
		deviceDataService.saveData(deviceDataAddRpo.getDeviceId(), deviceDataAddRpo.getDeviceSN(), deviceDataAddRpo.getData());

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "设备属性上报", notes = "设备属性上报")
	@PostMapping("v1/propertiesup")
	public ResponseEntity<ResultDataRro<Object>> receivedProperties(
			@RequestBody @ApiParam(name = "设备上报数据", value = "设备上报数据请求参数", required = true) JSONObject jsonObject) {
		
		deviceDataService.doPropertiesUp(jsonObject);
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "设备事件上报", notes = "设备事件上报")
	@PostMapping("v1/eventsup")
	public ResponseEntity<ResultDataRro<Object>> receivedEvents(
			@RequestBody @ApiParam(name = "设备上报数据", value = "设备上报数据请求参数", required = true) JSONObject jsonObject) {
		
		deviceDataService.doEventsUp(jsonObject);
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "设备命令上报", notes = "设备命令上报")
	@PostMapping("v1/commandsup")
	public ResponseEntity<ResultDataRro<Object>> receivedCommands(
			@RequestBody @ApiParam(name = "设备上报数据", value = "设备上报数据请求参数", required = true) JSONObject jsonObject) {
		
		deviceDataService.doEventsUp(jsonObject);
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
}
