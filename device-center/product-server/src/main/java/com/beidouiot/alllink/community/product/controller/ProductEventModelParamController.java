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
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductEventModelParamRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductEventModelParamUpdateRpoToProductEventModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductEventModelParamAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductEventModelParamSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductEventModelParamUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductEventModelParamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月16日
*/
@Api(tags = "产品事件模型参数管理")
@RestController
@RequestMapping(value = ServiceConstants.PRODUCT_EVENT_MODEL_PARAM_URI_BASE, produces = "application/json; charset=UTF-8")
public class ProductEventModelParamController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventModelParamController.class);

	@Autowired
	private ProductEventModelParamService productEventModelParamService;

	@Autowired
	private ProductEventModelParamRpoDtoMapping productEventModelParamRpoDtoMapping;

	@Autowired
	private ProductEventModelParamUpdateRpoToProductEventModelParamUpdateDtoMapping productEventModelParamUpdateRpoToProductEventModelParamUpdateDtoMapping;

	@ApiOperation(value = "产品事件模型参数信息添加", notes = "产品事件模型参数信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增产品事件模型参数信息", value = "产品事件模型参数信息请求参数", required = true) @Valid ProductEventModelParamAddRpo productEventModelParamAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productEventModelParamAddRpo= [ {} ]", productEventModelParamAddRpo);
		}
		ProductEventModelParamDto productEventModelParamDto = productEventModelParamRpoDtoMapping.targetToSource(productEventModelParamAddRpo);
		addUsernameAndDate(productEventModelParamDto, true);
		productEventModelParamService.saveEntity(productEventModelParamDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "产品事件模型参数修改", notes = "产品事件模型参数信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改产品事件模型参数信息", value = "产品事件模型参数信息请求参数", required = true) @Valid ProductEventModelParamUpdateRpo productEventModelParamUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productEventModelParamUpdateRpo= [ {} ]", productEventModelParamUpdateRpo);
		}
		ProductEventModelParamUpdateDto productEventModelParamUpdateDto = productEventModelParamUpdateRpoToProductEventModelParamUpdateDtoMapping.targetToSource(productEventModelParamUpdateRpo);
		addUsername(productEventModelParamUpdateDto, false);
		productEventModelParamService.updateEntity(productEventModelParamUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除产品事件模型参数", notes = "删除产品事件模型参数(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除产品事件模型参数", value = "删除产品事件模型参数信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productEventModelParamService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除产品事件模型参数", notes = "删除产品事件模型参数(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除产品事件模型参数", value = "删除产品事件模型参数信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productEventModelParamService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有产品事件模型参数", notes = "查询所有产品事件模型参数信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductEventModelParamDto>>> findAll() {
		List<ProductEventModelParamDto> list = productEventModelParamService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询产品事件模型参数", notes = "按条件分页查询产品事件模型参数信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductEventModelParamDto>>> findPage(
			@RequestBody @ApiParam(name = "查询产品事件模型参数", value = "按条件产品事件模型参数信息请求参数", required = true) ProductEventModelParamSearchRpo productEventModelParamSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productEventModelParamSearchRpo);
		Page<ProductEventModelParamDto> productEventModelParams = productEventModelParamService.findPage(searchParams, productEventModelParamSearchRpo.getPageNumber(),
				productEventModelParamSearchRpo.getPageSize(), productEventModelParamSearchRpo.getSortTypes());
		SmartPage<ProductEventModelParamDto> smartPage = new SmartPage<ProductEventModelParamDto>(productEventModelParams.getSize(), productEventModelParams.getTotalPages(),
				productEventModelParams.getTotalElements(), productEventModelParams.getNumber() + 1, productEventModelParams.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
