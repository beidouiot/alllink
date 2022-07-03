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
* @Description 职位信息管理
* @author longww
* @date 2021年5月9日
*/
@Api(tags = "职位信息管理")
@RestController
@RequestMapping(value = ServiceConstants.POSITION_URI_BASE, produces = "application/json; charset=UTF-8")
public class PositionController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionController.class);
	
	@Autowired
	private PositionService positionService;
	
	private PositionRpoDtoMapping positionRpoDtoMapping;
	
	private PositionUpdateRpoToPositionUpdateDtoMapping positionUpdateRpoToPositionUpdateDtoMapping;

	@ApiOperation(value = "职位信息添加", notes = "职位信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(@RequestBody @ApiParam(name = "新增职位信息", value = "职位信息请求参数", required = true) @Valid PositionAddRpo positionAddRpo) {
		LOGGER.debug("positionAddRpo= [ {} ]", positionAddRpo);
		PositionDto positionDto = positionRpoDtoMapping.targetToSource(positionAddRpo);
		addUsernameAndDate(positionDto,true);
		positionService.saveEntity(positionDto);
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "职位修改", notes = "职位信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(@RequestBody @ApiParam(name = "修改职位信息", value = "职位信息请求参数", required = true) @Valid PositionUpdateRpo positionUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("positionUpdateRpo= [ {} ]", positionUpdateRpo);
		PositionUpdateDto positionUpdateDto = positionUpdateRpoToPositionUpdateDtoMapping.targetToSource(positionUpdateRpo);
		addUsername(positionUpdateDto,false);
		positionService.updateEntity(positionUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除职位", notes = "删除职位(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(@RequestBody @ApiParam(name = "删除职位", value = "删除职位信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		positionService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "删除职位", notes = "删除职位(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(@RequestBody @ApiParam(name = "删除职位", value = "删除职位信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		positionService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有职位", notes = "查询所有职位信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<PositionDto>>> findAll() {
		List<PositionDto> list = positionService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "按条件分页查询职位", notes = "按条件分页查询职位信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<PositionDto>>> findPage(@RequestBody @ApiParam(name = "查询职位", value = "按条件职位信息请求参数", required = true) PositionSearchRpo positionSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(positionSearchRpo);
		Page<PositionDto> parks = positionService.findPage(searchParams, positionSearchRpo.getPageNumber(),
				positionSearchRpo.getPageSize(), positionSearchRpo.getSortTypes());
		SmartPage<PositionDto> smartPage = new SmartPage<PositionDto>(parks.getSize(), parks.getTotalPages(), parks.getTotalElements(),
				parks.getNumber() + 1, parks.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}

}
