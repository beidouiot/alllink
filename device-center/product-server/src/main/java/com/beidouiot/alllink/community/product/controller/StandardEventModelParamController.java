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
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardEventModelParamRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardEventModelParamUpdateRpoToStandardEventModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardEventModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardEventModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardEventModelParamAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardEventModelParamSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardEventModelParamUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardEventModelParamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月16日
*/
@Api(tags = "标准事件模型参数管理")
@RestController
@RequestMapping(value = ServiceConstants.STANDARD_EVENT_MODEL_PARAM_URI_BASE, produces = "application/json; charset=UTF-8")
public class StandardEventModelParamController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StandardEventModelParamController.class);

	@Autowired
	private StandardEventModelParamService standardEventModelParamService;

	@Autowired
	private StandardEventModelParamRpoDtoMapping standardEventModelParamRpoDtoMapping;

	@Autowired
	private StandardEventModelParamUpdateRpoToStandardEventModelParamUpdateDtoMapping standardEventModelParamUpdateRpoToStandardEventModelParamDtoMapping;

	@ApiOperation(value = "标准事件模型参数信息添加", notes = "标准事件模型参数信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增标准事件模型参数信息", value = "标准事件模型参数信息请求参数", required = true) @Valid StandardEventModelParamAddRpo standardEventModelParamAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardEventModelParamAddRpo= [ {} ]", standardEventModelParamAddRpo);
		}
		StandardEventModelParamDto standardEventModelParamDto = standardEventModelParamRpoDtoMapping.targetToSource(standardEventModelParamAddRpo);
		addUsernameAndDate(standardEventModelParamDto, true);
		standardEventModelParamService.saveEntity(standardEventModelParamDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "标准事件模型参数修改", notes = "标准事件模型参数信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改标准事件模型参数信息", value = "标准事件模型参数信息请求参数", required = true) @Valid StandardEventModelParamUpdateRpo standardEventModelParamUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardEventModelParamUpdateRpo= [ {} ]", standardEventModelParamUpdateRpo);
		}
		StandardEventModelParamUpdateDto standardEventModelParamUpdateDto = standardEventModelParamUpdateRpoToStandardEventModelParamDtoMapping.targetToSource(standardEventModelParamUpdateRpo);
		addUsername(standardEventModelParamUpdateDto, false);
		standardEventModelParamService.updateEntity(standardEventModelParamUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除标准事件模型参数", notes = "删除标准事件模型参数(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除标准事件模型参数", value = "删除标准事件模型参数信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardEventModelParamService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除标准事件模型参数", notes = "删除标准事件模型参数(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除标准事件模型参数", value = "删除标准事件模型参数信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardEventModelParamService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询标准事件模型参数", notes = "根据ID查询标准事件模型参数")
	@DeleteMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "标准事件模型参数ID", value = "标准事件模型参数ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		StandardEventModelParamDto standardEventModelParamDto = standardEventModelParamService.findById(id.getId());
		return makeSuccessResponseEntity(standardEventModelParamDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有标准事件模型参数", notes = "查询所有标准事件模型参数信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<StandardEventModelParamDto>>> findAll() {
		List<StandardEventModelParamDto> list = standardEventModelParamService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询标准事件模型参数", notes = "按条件分页查询标准事件模型参数信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<StandardEventModelParamDto>>> findPage(
			@RequestBody @ApiParam(name = "查询标准事件模型参数", value = "按条件标准事件模型参数信息请求参数", required = true) StandardEventModelParamSearchRpo standardEventModelParamSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(standardEventModelParamSearchRpo);
		Page<StandardEventModelParamDto> standardEventModelParams = standardEventModelParamService.findPage(searchParams, standardEventModelParamSearchRpo.getPageNumber(),
				standardEventModelParamSearchRpo.getPageSize(), standardEventModelParamSearchRpo.getSortTypes());
		SmartPage<StandardEventModelParamDto> smartPage = new SmartPage<StandardEventModelParamDto>(standardEventModelParams.getSize(), standardEventModelParams.getTotalPages(),
				standardEventModelParams.getTotalElements(), standardEventModelParams.getNumber() + 1, standardEventModelParams.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
}
