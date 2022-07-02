package com.beidouiot.alllink.community.user.center.controller;

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

import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.controller.BaseController;
import com.beidouiot.alllink.community.common.dao.api.service.datasercher.SearchParamsUtils;
import com.beidouiot.alllink.community.common.data.mapping.user.center.park.ParkRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.park.ParkUpdateRpoToParkUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.ParkDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.ParkUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.ID;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.park.ParkAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.park.ParkSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.park.ParkUpdateRpo;
import com.beidouiot.alllink.community.user.center.dao.service.api.ParkService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 *
 * @Description 园区/小区信息管理
 * @author longww
 * @date 2021年4月11日
 */
@Api(tags = "园区/小区信息管理")
@RestController
@RequestMapping(value = ServiceConstants.PARK_URI_BASE, produces = "application/json; charset=UTF-8")
public class ParkController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParkController.class);
	
	@Autowired
	private ParkService parkService;
	
	@Autowired
	private ParkRpoDtoMapping parkRpoDtoMapping;
	
	private ParkUpdateRpoToParkUpdateDtoMapping parkUpdateRpoToParkUpdateDtoMapping;
	
	@ApiOperation(value = "园区/小区信息添加", notes = "园区/小区信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(@RequestBody @ApiParam(name = "新增园区/小区信息", value = "园区/小区信息请求参数", required = true) @Valid ParkAddRpo parkAddRpo) {
		LOGGER.debug("parkAddRpo= [ {} ]", parkAddRpo);
		ParkDto parkDto = parkRpoDtoMapping.targetToSource(parkAddRpo);
		addUsernameAndDate(parkDto,true);
		parkService.saveEntity(parkDto);
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "园区/小区修改", notes = "园区/小区信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(@RequestBody @ApiParam(name = "修改园区/小区信息", value = "园区/小区信息请求参数", required = true) @Valid ParkUpdateRpo parkUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("parkUpdateRpo= [ {} ]", parkUpdateRpo);
		ParkUpdateDto parkUpdateDto = parkUpdateRpoToParkUpdateDtoMapping.targetToSource(parkUpdateRpo);
		addUsername(parkUpdateDto,false);
		parkService.updateEntity(parkUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除园区/小区", notes = "删除园区/小区(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(@RequestBody @ApiParam(name = "删除园区/小区", value = "删除园区/小区信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		parkService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "删除园区/小区", notes = "删除园区/小区(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(@RequestBody @ApiParam(name = "删除园区/小区", value = "删除园区/小区信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		parkService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有园区/小区", notes = "查询所有园区/小区信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ParkDto>>> findAll() {
		List<ParkDto> list = parkService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "按条件分页查询园区/小区", notes = "按条件分页查询园区/小区信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ParkDto>>> findPage(@RequestBody @ApiParam(name = "查询园区/小区", value = "按条件园区/小区信息请求参数", required = true) ParkSearchRpo parkSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(parkSearchRpo);
		Page<ParkDto> parks = parkService.findPage(searchParams, parkSearchRpo.getPageNumber(),
				parkSearchRpo.getPageSize(), parkSearchRpo.getSortTypes());
		SmartPage<ParkDto> smartPage = new SmartPage<ParkDto>(parks.getSize(), parks.getTotalPages(), parks.getTotalElements(),
				parks.getNumber() + 1, parks.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
}
