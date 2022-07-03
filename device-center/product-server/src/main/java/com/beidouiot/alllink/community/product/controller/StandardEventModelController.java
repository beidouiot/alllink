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
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardEventModelRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardEventModelUpdateRpoToStandardEventModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardEventModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardEventModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardEventModelAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardEventModelSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardEventModelUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardEventModelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月16日
*/
@Api(tags = "标准事件模型管理")
@RestController
@RequestMapping(value = ServiceConstants.STANDARD_EVENT_MODEL_URI_BASE, produces = "application/json; charset=UTF-8")
public class StandardEventModelController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StandardEventModelController.class);

	@Autowired
	private StandardEventModelService standardEventModelService;

	@Autowired
	private StandardEventModelRpoDtoMapping standardEventModelRpoDtoMapping;

	@Autowired
	private StandardEventModelUpdateRpoToStandardEventModelUpdateDtoMapping standardEventModelUpdateRpoToStandardEventModelUpdateDtoMapping;

	@ApiOperation(value = "标准事件模型信息添加", notes = "标准事件模型信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增标准事件模型信息", value = "标准事件模型信息请求参数", required = true) @Valid StandardEventModelAddRpo standardEventModelAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardEventModelAddRpo= [ {} ]", standardEventModelAddRpo);
		}
		StandardEventModelDto standardEventModelDto = standardEventModelRpoDtoMapping.targetToSource(standardEventModelAddRpo);
		addUsernameAndDate(standardEventModelDto, true);
		standardEventModelService.saveEntity(standardEventModelDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "标准事件模型修改", notes = "标准事件模型信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改标准事件模型信息", value = "标准事件模型信息请求参数", required = true) @Valid StandardEventModelUpdateRpo standardEventModelUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardEventModelUpdateRpo= [ {} ]", standardEventModelUpdateRpo);
		}
		StandardEventModelUpdateDto standardEventModelUpdateDto = standardEventModelUpdateRpoToStandardEventModelUpdateDtoMapping.targetToSource(standardEventModelUpdateRpo);
		addUsername(standardEventModelUpdateDto, false);
		standardEventModelService.updateEntity(standardEventModelUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除标准事件模型", notes = "删除标准事件模型(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除标准事件模型", value = "删除标准事件模型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardEventModelService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除标准事件模型", notes = "删除标准事件模型(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除标准事件模型", value = "删除标准事件模型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardEventModelService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询标准事件模型", notes = "根据ID查询标准事件模型)")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "标准事件模型ID", value = "标准事件模型ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		StandardEventModelDto standardEventModelDto = standardEventModelService.findById(id.getId());
		return makeSuccessResponseEntity(standardEventModelDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有标准事件模型", notes = "查询所有标准事件模型信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<StandardEventModelDto>>> findAll() {
		List<StandardEventModelDto> list = standardEventModelService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询标准事件模型", notes = "按条件分页查询标准事件模型信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<StandardEventModelDto>>> findPage(
			@RequestBody @ApiParam(name = "查询标准事件模型", value = "按条件标准事件模型信息请求参数", required = true) StandardEventModelSearchRpo standardEventModelSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(standardEventModelSearchRpo);
		Page<StandardEventModelDto> standardEventModels = standardEventModelService.findPage(searchParams, standardEventModelSearchRpo.getPageNumber(),
				standardEventModelSearchRpo.getPageSize(), standardEventModelSearchRpo.getSortTypes());
		SmartPage<StandardEventModelDto> smartPage = new SmartPage<StandardEventModelDto>(standardEventModels.getSize(), standardEventModels.getTotalPages(),
				standardEventModels.getTotalElements(), standardEventModels.getNumber() + 1, standardEventModels.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
}
