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
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardCommandModelParamRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardCommandModelParamUpdateRpoToStandardCommandModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardCommandModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardCommandModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardCommandModelParamAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardCommandModelParamSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardCommandModelParamUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardCommandModelParamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月16日
*/
@Api(tags = "标准指令模型参数管理")
@RestController
@RequestMapping(value = ServiceConstants.STANDARD_COMMAND_MODEL_PARAM_URI_BASE, produces = "application/json; charset=UTF-8")
public class StandardCommandModelParamController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardCommandModelParamController.class);

	@Autowired
	private StandardCommandModelParamService standardCommandModelParamService;

	@Autowired
	private StandardCommandModelParamRpoDtoMapping standardCommandModelParamRpoDtoMapping;

	@Autowired
	private StandardCommandModelParamUpdateRpoToStandardCommandModelParamUpdateDtoMapping standardCommandModelParamUpdateRpoToStandardCommandModelParamUpdateDtoMapping;

	@ApiOperation(value = "标准指令模型参数信息添加", notes = "标准指令模型参数信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增标准指令模型参数信息", value = "标准指令模型参数信息请求参数", required = true) @Valid StandardCommandModelParamAddRpo standardCommandModelParamAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardCommandModelParamAddRpo= [ {} ]", standardCommandModelParamAddRpo);
		}
		StandardCommandModelParamDto standardCommandModelParamDto = standardCommandModelParamRpoDtoMapping.targetToSource(standardCommandModelParamAddRpo);
		addUsernameAndDate(standardCommandModelParamDto, true);
		standardCommandModelParamService.saveEntity(standardCommandModelParamDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "标准指令模型参数修改", notes = "标准指令模型参数信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改标准指令模型参数信息", value = "标准指令模型参数信息请求参数", required = true) @Valid StandardCommandModelParamUpdateRpo standardCommandModelParamUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
		LOGGER.debug("standardCommandModelParamUpdateRpo= [ {} ]", standardCommandModelParamUpdateRpo);
		}
		StandardCommandModelParamUpdateDto standardCommandModelParamUpdateDto = standardCommandModelParamUpdateRpoToStandardCommandModelParamUpdateDtoMapping.targetToSource(standardCommandModelParamUpdateRpo);
		addUsername(standardCommandModelParamUpdateDto, false);
		standardCommandModelParamService.updateEntity(standardCommandModelParamUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除标准指令模型参数", notes = "删除标准指令模型参数(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除标准指令模型参数", value = "删除标准指令模型参数信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardCommandModelParamService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除标准指令模型参数", notes = "删除标准指令模型参数(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除标准指令模型参数", value = "删除标准指令模型参数信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardCommandModelParamService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询标准指令模型参数", notes = "根据ID标准指令模型参数")
	@DeleteMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "标准指令模型参数ID", value = "标准指令模型参数ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		StandardCommandModelParamDto standardCommandModelParamDto = standardCommandModelParamService.findById(id.getId());
		return makeSuccessResponseEntity(standardCommandModelParamDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有标准指令模型参数", notes = "查询所有标准指令模型参数信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<StandardCommandModelParamDto>>> findAll() {
		List<StandardCommandModelParamDto> list = standardCommandModelParamService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询标准指令模型参数", notes = "按条件分页查询标准指令模型参数信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<StandardCommandModelParamDto>>> findPage(
			@RequestBody @ApiParam(name = "查询标准指令模型参数", value = "按条件标准指令模型参数信息请求参数", required = true) StandardCommandModelParamSearchRpo standardCommandModelParamSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(standardCommandModelParamSearchRpo);
		Page<StandardCommandModelParamDto> standardCommandModelParams = standardCommandModelParamService.findPage(searchParams, standardCommandModelParamSearchRpo.getPageNumber(),
				standardCommandModelParamSearchRpo.getPageSize(), standardCommandModelParamSearchRpo.getSortTypes());
		SmartPage<StandardCommandModelParamDto> smartPage = new SmartPage<StandardCommandModelParamDto>(standardCommandModelParams.getSize(), standardCommandModelParams.getTotalPages(),
				standardCommandModelParams.getTotalElements(), standardCommandModelParams.getNumber() + 1, standardCommandModelParams.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
