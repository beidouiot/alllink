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
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductCommandModelRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductCommandModelUpdateRpoToProductCommandModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductCommandModelAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductCommandModelSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductCommandModelUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductCommandModelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月16日
*/
@Api(tags = "产品指令模型管理")
@RestController
@RequestMapping(value = ServiceConstants.PRODUCT_COMMAND_MODEL_URI_BASE, produces = "application/json; charset=UTF-8")
public class ProductCommandModelController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCommandModelController.class);

	@Autowired
	private ProductCommandModelService productCommandModelService;

	@Autowired
	private ProductCommandModelRpoDtoMapping productCommandModelRpoDtoMapping;

	@Autowired
	private ProductCommandModelUpdateRpoToProductCommandModelUpdateDtoMapping ProductCommandModelUpdateRpoToProductCommandModelUpdateDtoMapping;

	@ApiOperation(value = "产品指令模型信息添加", notes = "产品指令模型信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增产品指令模型信息", value = "产品指令模型信息请求参数", required = true) @Valid ProductCommandModelAddRpo productCommandModelAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productCommandModelAddRpo= [ {} ]", productCommandModelAddRpo);
		}
		ProductCommandModelDto productCommandModelDto = productCommandModelRpoDtoMapping.targetToSource(productCommandModelAddRpo);
		addUsernameAndDate(productCommandModelDto, true);
		productCommandModelService.saveEntity(productCommandModelDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "产品指令模型修改", notes = "产品指令模型信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改产品指令模型信息", value = "产品指令模型信息请求参数", required = true) @Valid ProductCommandModelUpdateRpo productCommandModelUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productCommandModelUpdateRpo= [ {} ]", productCommandModelUpdateRpo);
		}
		ProductCommandModelUpdateDto productCommandModelUpdateDto = ProductCommandModelUpdateRpoToProductCommandModelUpdateDtoMapping.targetToSource(productCommandModelUpdateRpo);
		addUsername(productCommandModelUpdateDto, false);
		productCommandModelService.updateEntity(productCommandModelUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除产品指令模型", notes = "删除产品指令模型(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除产品指令模型", value = "删除产品指令模型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productCommandModelService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除产品指令模型", notes = "删除产品指令模型(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除产品指令模型", value = "删除产品指令模型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productCommandModelService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有产品指令模型", notes = "查询所有产品指令模型信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductCommandModelDto>>> findAll() {
		List<ProductCommandModelDto> list = productCommandModelService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询产品指令模型", notes = "按条件分页查询产品指令模型信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductCommandModelDto>>> findPage(
			@RequestBody @ApiParam(name = "查询产品指令模型", value = "按条件产品指令模型信息请求参数", required = true) ProductCommandModelSearchRpo productCommandModelSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productCommandModelSearchRpo);
		Page<ProductCommandModelDto> productCommandModels = productCommandModelService.findPage(searchParams, productCommandModelSearchRpo.getPageNumber(),
				productCommandModelSearchRpo.getPageSize(), productCommandModelSearchRpo.getSortTypes());
		SmartPage<ProductCommandModelDto> smartPage = new SmartPage<ProductCommandModelDto>(productCommandModels.getSize(), productCommandModels.getTotalPages(),
				productCommandModels.getTotalElements(), productCommandModels.getNumber() + 1, productCommandModels.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
