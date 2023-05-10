package com.beidouiot.alllink.community.device.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.controller.BaseController;
import com.beidouiot.alllink.community.common.data.mapping.DeviceNewDataRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.DeviceNewDataUpdateRpoToDeviceNewDataUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataDto;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceNewDataAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceNewDataUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.device.dao.service.api.DeviceNewDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "设备管理")
@RestController
@RequestMapping(value = ServiceConstants.DEVICE_NEW_DATA_URI_BASE, produces = "application/json; charset=UTF-8")
public class DeviceNewDataController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceNewDataController.class);

	@Autowired
	private DeviceNewDataService deviceNewDataService;

	@Autowired
	private DeviceNewDataRpoDtoMapping deviceNewDataRpoDtoMapping;

	@Autowired
	private DeviceNewDataUpdateRpoToDeviceNewDataUpdateDtoMapping deviceNewDataUpdateRpoToDeviceNewDataUpdateDtoMapping;

	@ApiOperation(value = "设备采集数据添加", notes = "设备采集数据添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增设备信息", value = "设备信息请求参数", required = true) @Valid DeviceNewDataAddRpo deviceNewDataAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("deviceNewDataAddRpo= [ {} ]", deviceNewDataAddRpo);
		}
		DeviceNewDataDto deviceNewDataDto = deviceNewDataRpoDtoMapping.targetToSource(deviceNewDataAddRpo);
		addUsernameAndDate(deviceNewDataDto, true);
		deviceNewDataService.saveEntity(deviceNewDataDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "设备采集数据", notes = "设备采集数据修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改设备信息", value = "设备信息请求参数", required = true) @Valid DeviceNewDataUpdateRpo deviceNewDataUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("deviceNewDataUpdateRpo= [ {} ]", deviceNewDataUpdateRpo);
		}
		DeviceNewDataUpdateDto deviceNewDataUpdateDto = deviceNewDataUpdateRpoToDeviceNewDataUpdateDtoMapping.targetToSource(deviceNewDataUpdateRpo);
		addUsername(deviceNewDataUpdateDto, false);
		deviceNewDataService.updateEntity(deviceNewDataUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除设备采集数据", notes = "删除设备采集数据(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除设备采集数据", value = "删除设备采集数据请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		deviceNewDataService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除设备采集数据", notes = "删除设备采集数据(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除设备采集数据", value = "删除设备采集数据", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		deviceNewDataService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有设备采集数据", notes = "查询所有设备采集数据")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<DeviceNewDataDto>>> findAll() {
		List<DeviceNewDataDto> list = deviceNewDataService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

//	@ApiOperation(value = "按条件分页查询设备", notes = "按条件分页查询设备信息")
//	@PostMapping("v1/findPage")
//	public ResponseEntity<ResultDataRro<SmartPage<DeviceNewDataDto>>> findPage(
//			@RequestBody @ApiParam(name = "查询设备", value = "按条件设备信息请求参数", required = true) DeviceNewDataSearchRpo deviceNewDataSearchRpo) {
//		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(deviceNewDataSearchRpo);
//		Page<DeviceNewDataDto> deviceNewDatas = deviceNewDataService.findPage(searchParams, deviceNewDataSearchRpo.getPageNumber(),
//				deviceNewDataSearchRpo.getPageSize(), deviceNewDataSearchRpo.getSortTypes());
//		SmartPage<DeviceNewDataDto> smartPage = new SmartPage<DeviceNewDataDto>(deviceNewDatas.getSize(), deviceNewDatas.getTotalPages(),
//				deviceNewDatas.getTotalElements(), deviceNewDatas.getNumber() + 1, deviceNewDatas.getContent());
//		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
//	}
	
	@ApiOperation(value = "查询设备采集数据", notes = "根据id查询设备采集数据")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "设备ID", value = "设备ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		DeviceNewDataDto deviceNewDataDto = deviceNewDataService.findById(id.getId());
		return makeSuccessResponseEntity(deviceNewDataDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询设备采集数据", notes = "根据设备id查询设备采集数据")
	@PostMapping("v1/findAllDeviceNewData")
	public ResponseEntity<?> findAllDeviceNewData(
			@RequestBody @ApiParam(name = "设备ID", value = "设备ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		List<DeviceNewDataDto> deviceNewDatas = deviceNewDataService.findDeviceNewDatasByDeviceId(id.getId());
		return makeSuccessResponseEntity(deviceNewDatas, HttpStatus.OK);
	}
}
