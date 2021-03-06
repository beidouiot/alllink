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
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductCommandModelListRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductCommandModelListUpdateRpo;
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
* @date 2022???2???16???
*/
@Api(tags = "????????????????????????")
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

	@ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "??????????????????????????????", value = "????????????????????????????????????", required = true) @Valid ProductCommandModelListRpo productCommandModelListRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productCommandModelListRpo= [ {} ]", productCommandModelListRpo);
		}
		List<ProductCommandModelAddRpo> list = productCommandModelListRpo.getProductCommandModelList();
		for (ProductCommandModelAddRpo productCommandModelAddRpo : list) {
			ProductCommandModelDto productCommandModelDto = productCommandModelRpoDtoMapping.targetToSource(productCommandModelAddRpo);
			addUsernameAndDate(productCommandModelDto, true);
			productCommandModelService.saveEntity(productCommandModelDto);
		}
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????????????????", notes = "??????????????????????????????")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "??????????????????????????????", value = "????????????????????????????????????", required = true) @Valid ProductCommandModelListUpdateRpo productCommandModelListUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productCommandModelListUpdateRpo= [ {} ]", productCommandModelListUpdateRpo);
		}
		
		List<ProductCommandModelUpdateRpo> list = productCommandModelListUpdateRpo.getProductCommandModelList();
		for (ProductCommandModelUpdateRpo productCommandModelUpdateRpo : list) {
			ProductCommandModelUpdateDto productCommandModelUpdateDto = ProductCommandModelUpdateRpoToProductCommandModelUpdateDtoMapping.targetToSource(productCommandModelUpdateRpo);
			addUsername(productCommandModelUpdateDto, false);
			productCommandModelService.updateEntity(productCommandModelUpdateDto);
		}
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????????????????", notes = "????????????????????????(????????????)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "????????????????????????", value = "??????????????????????????????????????????", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productCommandModelService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "????????????????????????", notes = "????????????????????????(????????????)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "????????????????????????", value = "??????????????????????????????????????????", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productCommandModelService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????????????????", notes = "????????????????????????????????????")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductCommandModelDto>>> findAll() {
		List<ProductCommandModelDto> list = productCommandModelService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "???????????????????????????????????????", notes = "?????????????????????????????????????????????")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductCommandModelDto>>> findPage(
			@RequestBody @ApiParam(name = "????????????????????????", value = "?????????????????????????????????????????????", required = true) ProductCommandModelSearchRpo productCommandModelSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productCommandModelSearchRpo);
		Page<ProductCommandModelDto> productCommandModels = productCommandModelService.findPage(searchParams, productCommandModelSearchRpo.getPageNumber(),
				productCommandModelSearchRpo.getPageSize(), productCommandModelSearchRpo.getSortTypes());
		SmartPage<ProductCommandModelDto> smartPage = new SmartPage<ProductCommandModelDto>(productCommandModels.getSize(), productCommandModels.getTotalPages(),
				productCommandModels.getTotalElements(), productCommandModels.getNumber() + 1, productCommandModels.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
