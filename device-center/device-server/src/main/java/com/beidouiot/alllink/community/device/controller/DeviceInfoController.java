package com.beidouiot.alllink.community.device.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.controller.BaseController;
import com.beidouiot.alllink.community.common.dao.api.service.datasercher.SearchParamsUtils;
import com.beidouiot.alllink.community.common.data.mapping.DeviceCommandsRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.DeviceInfoRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.DeviceInfoUpdateRpoToDeviceInfoUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceCommandsDto;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceInfoDto;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceInfoUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceCommandsRpo;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceInfoAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceInfoSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceInfoUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.device.dao.service.api.DeviceInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "设备管理")
@RestController
@RequestMapping(value = ServiceConstants.DEVICE_INFO_URI_BASE, produces = "application/json; charset=UTF-8")
public class DeviceInfoController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceInfoController.class);

	@Autowired
	private DeviceInfoService deviceInfoService;

	@Autowired
	private DeviceInfoRpoDtoMapping deviceInfoRpoDtoMapping;
	
	@Autowired
	private DeviceCommandsRpoDtoMapping deviceCommandsRpoDtoMapping;

	@Autowired
	private DeviceInfoUpdateRpoToDeviceInfoUpdateDtoMapping deviceInfoUpdateRpoToDeviceInfoUpdateDtoMapping;

	@ApiOperation(value = "设备信息添加", notes = "设备信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增设备信息", value = "设备信息请求参数", required = true) @Valid DeviceInfoAddRpo deviceInfoAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("deviceInfoAddRpo= [ {} ]", deviceInfoAddRpo);
		}
		DeviceInfoDto deviceInfoDto = deviceInfoRpoDtoMapping.targetToSource(deviceInfoAddRpo);
		addUsernameAndDate(deviceInfoDto, true);
		deviceInfoService.saveEntity(deviceInfoDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "设备修改", notes = "设备信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改设备信息", value = "设备信息请求参数", required = true) @Valid DeviceInfoUpdateRpo deviceInfoUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("deviceInfoUpdateRpo= [ {} ]", deviceInfoUpdateRpo);
		}
		DeviceInfoUpdateDto deviceInfoUpdateDto = deviceInfoUpdateRpoToDeviceInfoUpdateDtoMapping.targetToSource(deviceInfoUpdateRpo);
		addUsername(deviceInfoUpdateDto, false);
		deviceInfoService.updateEntity(deviceInfoUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除设备", notes = "删除设备(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除设备", value = "删除设备信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		deviceInfoService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除设备", notes = "删除设备(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除设备", value = "删除设备信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		deviceInfoService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有设备", notes = "查询所有设备信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<DeviceInfoDto>>> findAll() {
		List<DeviceInfoDto> list = deviceInfoService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "查询所有网关设备", notes = "查询所有网关设备信息")
	@GetMapping("v1/findAllGateway")
	public ResponseEntity<ResultDataRro<List<DeviceInfoDto>>> findAllGateway() {
		List<DeviceInfoDto> list = deviceInfoService.findAllDeviceInfo(Constants.TRUE, Constants.TRUE);
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	

	@ApiOperation(value = "按条件分页查询设备", notes = "按条件分页查询设备信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<DeviceInfoDto>>> findPage(
			@RequestBody @ApiParam(name = "查询设备", value = "按条件设备信息请求参数", required = true) DeviceInfoSearchRpo deviceInfoSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(deviceInfoSearchRpo);
		Page<DeviceInfoDto> deviceInfos = deviceInfoService.findPage(searchParams, deviceInfoSearchRpo.getPageNumber(),
				deviceInfoSearchRpo.getPageSize(), deviceInfoSearchRpo.getSortTypes());
		SmartPage<DeviceInfoDto> smartPage = new SmartPage<DeviceInfoDto>(deviceInfos.getSize(), deviceInfos.getTotalPages(),
				deviceInfos.getTotalElements(), deviceInfos.getNumber() + 1, deviceInfos.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询设备", notes = "根据id查询设备")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "设备ID", value = "设备ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		DeviceInfoDto productDto = deviceInfoService.findById(id.getId());
		return makeSuccessResponseEntity(productDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "设备属性下发", notes = "设备属性下发")
	@PostMapping("v1/properties/send")
	public ResponseEntity<?> sendProperties(@RequestBody @ApiParam(name = "设备事件", value = "设备事件", required = true) @Valid DeviceCommandsRpo deviceCommandsRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("deviceCommandsRpo= {}", deviceCommandsRpo.toString());
		}
		DeviceCommandsDto deviceCommandsDto = deviceCommandsRpoDtoMapping.targetToSource(deviceCommandsRpo);
		
		deviceInfoService.sendProperties(deviceCommandsDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "设备事件下发", notes = "设备事件下发")
	@PostMapping("v1/events/send")
	public ResponseEntity<?> sendEvents(@RequestBody @ApiParam(name = "设备事件", value = "设备事件", required = true) @Valid DeviceCommandsRpo deviceCommandsRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("deviceCommandsRpo= {}", deviceCommandsRpo.toString());
		}
		DeviceCommandsDto deviceCommandsDto = deviceCommandsRpoDtoMapping.targetToSource(deviceCommandsRpo);
		
		deviceInfoService.sendEvents(deviceCommandsDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "设备指令下发", notes = "设备指令下发")
	@PostMapping("v1/commands/send")
	public ResponseEntity<?> sendCommands(@RequestBody @ApiParam(name = "设备指令", value = "设备指令", required = true) @Valid DeviceCommandsRpo deviceCommandsRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("deviceCommandsRpo= {}", deviceCommandsRpo.toString());
		}
		DeviceCommandsDto deviceCommandsDto = deviceCommandsRpoDtoMapping.targetToSource(deviceCommandsRpo);
		
		deviceInfoService.sendCommands(deviceCommandsDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
}
