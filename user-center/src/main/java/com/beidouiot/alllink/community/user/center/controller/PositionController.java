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
import com.beidouiot.alllink.community.common.data.mapping.user.center.position.PositionRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.position.PositionUpdateRpoToPositionUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.PositionDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.PositionUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.ID;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.position.PositionAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.position.PositionSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.position.PositionUpdateRpo;
import com.beidouiot.alllink.community.user.center.dao.service.api.PositionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description ??????????????????
* @author longww
* @date 2021???5???9???
*/
@Api(tags = "??????????????????")
@RestController
@RequestMapping(value = ServiceConstants.POSITION_URI_BASE, produces = "application/json; charset=UTF-8")
public class PositionController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionController.class);
	
	@Autowired
	private PositionService positionService;
	
	private PositionRpoDtoMapping positionRpoDtoMapping;
	
	private PositionUpdateRpoToPositionUpdateDtoMapping positionUpdateRpoToPositionUpdateDtoMapping;

	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(@RequestBody @ApiParam(name = "??????????????????", value = "????????????????????????", required = true) @Valid PositionAddRpo positionAddRpo) {
		LOGGER.debug("positionAddRpo= [ {} ]", positionAddRpo);
		PositionDto positionDto = positionRpoDtoMapping.targetToSource(positionAddRpo);
		addUsernameAndDate(positionDto,true);
		positionService.saveEntity(positionDto);
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????", notes = "??????????????????")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(@RequestBody @ApiParam(name = "??????????????????", value = "????????????????????????", required = true) @Valid PositionUpdateRpo positionUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("positionUpdateRpo= [ {} ]", positionUpdateRpo);
		PositionUpdateDto positionUpdateDto = positionUpdateRpoToPositionUpdateDtoMapping.targetToSource(positionUpdateRpo);
		addUsername(positionUpdateDto,false);
		positionService.updateEntity(positionUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????", notes = "????????????(????????????)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(@RequestBody @ApiParam(name = "????????????", value = "??????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		positionService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "????????????", notes = "????????????(????????????)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(@RequestBody @ApiParam(name = "????????????", value = "??????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		positionService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????", notes = "????????????????????????")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<PositionDto>>> findAll() {
		List<PositionDto> list = positionService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "???????????????????????????", notes = "?????????????????????????????????")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<PositionDto>>> findPage(@RequestBody @ApiParam(name = "????????????", value = "?????????????????????????????????", required = true) PositionSearchRpo positionSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(positionSearchRpo);
		Page<PositionDto> parks = positionService.findPage(searchParams, positionSearchRpo.getPageNumber(),
				positionSearchRpo.getPageSize(), positionSearchRpo.getSortTypes());
		SmartPage<PositionDto> smartPage = new SmartPage<PositionDto>(parks.getSize(), parks.getTotalPages(), parks.getTotalElements(),
				parks.getNumber() + 1, parks.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}

}
