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
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardPropertyModelRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardPropertyModelUpdateRpoToStandardPropertyModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardPropertyModelAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardPropertyModelListRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardPropertyModelSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardPropertyModelUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardPropertyModelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww 
* @date 2022年2月15日
*/
@Api(tags = "标准属性模型管理")
@RestController
@RequestMapping(value = ServiceConstants.STANDARD_PROPERTY_MODEL_URI_BASE, produces = "application/json; charset=UTF-8")
public class StandardPropertyModelController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardPropertyModelController.class);

	@Autowired
	private StandardPropertyModelService standardPropertyModelService;

	@Autowired
	private StandardPropertyModelRpoDtoMapping standardPropertyModelRpoDtoMapping;

	@Autowired
	private StandardPropertyModelUpdateRpoToStandardPropertyModelUpdateDtoMapping standardPropertyModelUpdateRpoToStandardPropertyModelUpdateDtoMapping;

	@ApiOperation(value = "标准属性模型信息添加", notes = "标准属性模型信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增标准属性模型信息", value = "标准属性模型信息请求参数", required = true) @Valid StandardPropertyModelListRpo standardPropertyModelListRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardPropertyModelAddRpo= [ {} ]", standardPropertyModelListRpo);
		}
		List<StandardPropertyModelAddRpo> list = standardPropertyModelListRpo.getStandardPropertyModelList();
		for (StandardPropertyModelAddRpo standardPropertyModelAddRpo : list) {
			StandardPropertyModelDto standardPropertyModelDto = standardPropertyModelRpoDtoMapping.targetToSource(standardPropertyModelAddRpo);
			addUsernameAndDate(standardPropertyModelDto, true);
			standardPropertyModelService.saveEntity(standardPropertyModelDto);
		}

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "标准属性模型修改", notes = "标准属性模型信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改标准属性模型信息", value = "标准属性模型信息请求参数", required = true) @Valid StandardPropertyModelUpdateRpo standardPropertyModelUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardPropertyModelUpdateRpo= [ {} ]", standardPropertyModelUpdateRpo);
		}
		StandardPropertyModelUpdateDto standardPropertyModelUpdateDto = standardPropertyModelUpdateRpoToStandardPropertyModelUpdateDtoMapping.targetToSource(standardPropertyModelUpdateRpo);
		addUsername(standardPropertyModelUpdateDto, false);
		standardPropertyModelService.updateEntity(standardPropertyModelUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除标准属性模型", notes = "删除标准属性模型(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除标准属性模型", value = "删除标准属性模型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardPropertyModelService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除标准属性模型", notes = "删除标准属性模型(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除标准属性模型", value = "删除标准属性模型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardPropertyModelService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询标准属性模型", notes = "根据ID查询标准属性模型")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "标准属性模型ID", value = "标准属性模型ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		StandardPropertyModelDto standardPropertyModelDto = standardPropertyModelService.findById(id.getId());
		return makeSuccessResponseEntity(standardPropertyModelDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有标准属性模型", notes = "查询所有标准属性模型信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<StandardPropertyModelDto>>> findAll() {
		List<StandardPropertyModelDto> list = standardPropertyModelService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询标准属性模型", notes = "按条件分页查询标准属性模型信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<StandardPropertyModelDto>>> findPage(
			@RequestBody @ApiParam(name = "查询标准属性模型", value = "按条件标准属性模型信息请求参数", required = true) StandardPropertyModelSearchRpo standardPropertyModelSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(standardPropertyModelSearchRpo);
		Page<StandardPropertyModelDto> standardPropertyModels = standardPropertyModelService.findPage(searchParams, standardPropertyModelSearchRpo.getPageNumber(),
				standardPropertyModelSearchRpo.getPageSize(), standardPropertyModelSearchRpo.getSortTypes());
		SmartPage<StandardPropertyModelDto> smartPage = new SmartPage<StandardPropertyModelDto>(standardPropertyModels.getSize(), standardPropertyModels.getTotalPages(),
				standardPropertyModels.getTotalElements(), standardPropertyModels.getNumber() + 1, standardPropertyModels.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
