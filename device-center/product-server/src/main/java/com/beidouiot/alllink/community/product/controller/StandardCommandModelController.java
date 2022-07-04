package com.beidouiot.alllink.community.product.controller;

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
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardCommandModelRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardCommandModelUpdateRpoToStandardCommandModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardCommandModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardCommandModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardCommandModelAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardCommandModelListRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardCommandModelSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardCommandModelUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardCommandModelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO 
* @author longww
* @date 2022年2月16日
*/
@Api(tags = "标准指令模型管理")
@RestController
@RequestMapping(value = ServiceConstants.STANDARD_COMMAND_MODEL_URI_BASE, produces = "application/json; charset=UTF-8")
public class StandardCommandModelController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardCommandModelController.class);

	@Autowired
	private StandardCommandModelService standardCommandModelService;

	@Autowired
	private StandardCommandModelRpoDtoMapping standardCommandModelRpoDtoMapping;

	@Autowired
	private StandardCommandModelUpdateRpoToStandardCommandModelUpdateDtoMapping standardCommandModelUpdateRpoToStandardCommandModelDtoMapping;

	@ApiOperation(value = "标准指令模型信息添加", notes = "标准指令模型信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增标准指令模型信息", value = "标准指令模型信息请求参数", required = true) @Valid StandardCommandModelListRpo standardCommandModelListRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardCommandModelAddRpo= [ {} ]", standardCommandModelListRpo);
		}
		List<StandardCommandModelAddRpo> list = standardCommandModelListRpo.getStandardCommandModelList();
		for (StandardCommandModelAddRpo standardCommandModelAddRpo : list) {
			StandardCommandModelDto standardCommandModelDto = standardCommandModelRpoDtoMapping.targetToSource(standardCommandModelAddRpo);
			addUsernameAndDate(standardCommandModelDto, true);
			standardCommandModelService.saveEntity(standardCommandModelDto);
		}

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "标准指令模型修改", notes = "标准指令模型信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改标准指令模型信息", value = "标准指令模型信息请求参数", required = true) @Valid StandardCommandModelUpdateRpo standardCommandModelUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardCommandModelUpdateRpo= [ {} ]", standardCommandModelUpdateRpo);
		}
		StandardCommandModelUpdateDto standardCommandModelUpdateDto = standardCommandModelUpdateRpoToStandardCommandModelDtoMapping.targetToSource(standardCommandModelUpdateRpo);
		addUsername(standardCommandModelUpdateDto, false);
		standardCommandModelService.updateEntity(standardCommandModelUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除标准指令模型", notes = "删除标准指令模型(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除标准指令模型", value = "删除标准指令模型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardCommandModelService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除标准指令模型", notes = "删除标准指令模型(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除标准指令模型", value = "删除标准指令模型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardCommandModelService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询标准指令模型", notes = "根据ID查询标准指令模型")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "标准指令模型ID", value = "标准指令模型ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		StandardCommandModelDto standardCommandModelDto = standardCommandModelService.findById(id.getId());
		return makeSuccessResponseEntity(standardCommandModelDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有标准指令模型", notes = "查询所有标准指令模型信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<StandardCommandModelDto>>> findAll() {
		List<StandardCommandModelDto> list = standardCommandModelService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询标准指令模型", notes = "按条件分页查询标准指令模型信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<StandardCommandModelDto>>> findPage(
			@RequestBody @ApiParam(name = "查询标准指令模型", value = "按条件标准指令模型信息请求参数", required = true) StandardCommandModelSearchRpo standardCommandModelSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(standardCommandModelSearchRpo);
		Page<StandardCommandModelDto> standardCommandModels = standardCommandModelService.findPage(searchParams, standardCommandModelSearchRpo.getPageNumber(),
				standardCommandModelSearchRpo.getPageSize(), standardCommandModelSearchRpo.getSortTypes());
		SmartPage<StandardCommandModelDto> smartPage = new SmartPage<StandardCommandModelDto>(standardCommandModels.getSize(), standardCommandModels.getTotalPages(),
				standardCommandModels.getTotalElements(), standardCommandModels.getNumber() + 1, standardCommandModels.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
