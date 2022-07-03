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
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductCommandModelParamRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductCommandModelParamUpdateRpoToProductCommandModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductCommandModelParamAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductCommandModelParamSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductCommandModelParamUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductCommandModelParamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月16日
*/
@Api(tags = "产品指令模型参数管理")
@RestController
@RequestMapping(value = ServiceConstants.PRODUCT_COMMAND_MODEL_PARAM_URI_BASE, produces = "application/json; charset=UTF-8")
public class ProductCommandModelParamController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCommandModelParamController.class);

	@Autowired
	private ProductCommandModelParamService productCommandModelParamService;

	@Autowired
	private ProductCommandModelParamRpoDtoMapping productCommandModelParamRpoDtoMapping;

	@Autowired
	private ProductCommandModelParamUpdateRpoToProductCommandModelParamUpdateDtoMapping productCommandModelParamUpdateRpoToProductCommandModelParamUpdateDtoMapping;

	@ApiOperation(value = "产品指令模型参数信息添加", notes = "产品指令模型参数信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增产品指令模型参数信息", value = "产品指令模型参数信息请求参数", required = true) @Valid ProductCommandModelParamAddRpo productCommandModelParamAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productCommandModelParamAddRpo= [ {} ]", productCommandModelParamAddRpo);
		}
		ProductCommandModelParamDto productCommandModelParamDto = productCommandModelParamRpoDtoMapping.targetToSource(productCommandModelParamAddRpo);
		addUsernameAndDate(productCommandModelParamDto, true);
		productCommandModelParamService.saveEntity(productCommandModelParamDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "产品指令模型参数修改", notes = "产品指令模型参数信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改产品指令模型参数信息", value = "产品指令模型参数信息请求参数", required = true) @Valid ProductCommandModelParamUpdateRpo productCommandModelParamUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productCommandModelParamUpdateRpo= [ {} ]", productCommandModelParamUpdateRpo);
		}
		ProductCommandModelParamUpdateDto productCommandModelParamUpdateDto = productCommandModelParamUpdateRpoToProductCommandModelParamUpdateDtoMapping.targetToSource(productCommandModelParamUpdateRpo);
		addUsername(productCommandModelParamUpdateDto, false);
		productCommandModelParamService.updateEntity(productCommandModelParamUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除产品指令模型参数", notes = "删除产品指令模型参数(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除产品指令模型参数", value = "删除产品指令模型参数信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productCommandModelParamService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除产品指令模型参数", notes = "删除产品指令模型参数(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除产品指令模型参数", value = "删除产品指令模型参数信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productCommandModelParamService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有产品指令模型参数", notes = "查询所有产品指令模型参数信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductCommandModelParamDto>>> findAll() {
		List<ProductCommandModelParamDto> list = productCommandModelParamService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询产品指令模型参数", notes = "按条件分页查询产品指令模型参数信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductCommandModelParamDto>>> findPage(
			@RequestBody @ApiParam(name = "查询产品指令模型参数", value = "按条件产品指令模型参数信息请求参数", required = true) ProductCommandModelParamSearchRpo productCommandModelParamSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productCommandModelParamSearchRpo);
		Page<ProductCommandModelParamDto> productCommandModelParams = productCommandModelParamService.findPage(searchParams, productCommandModelParamSearchRpo.getPageNumber(),
				productCommandModelParamSearchRpo.getPageSize(), productCommandModelParamSearchRpo.getSortTypes());
		SmartPage<ProductCommandModelParamDto> smartPage = new SmartPage<ProductCommandModelParamDto>(productCommandModelParams.getSize(), productCommandModelParams.getTotalPages(),
				productCommandModelParams.getTotalElements(), productCommandModelParams.getNumber() + 1, productCommandModelParams.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
