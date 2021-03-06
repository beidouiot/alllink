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
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductEventModelRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductEventModelUpdateRpoToProductEventModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductEventModelAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductEventModelListRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductEventModelListUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductEventModelSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductEventModelUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductEventModelService;

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
@RequestMapping(value = ServiceConstants.PRODUCT_EVENT_MODEL_URI_BASE, produces = "application/json; charset=UTF-8")
public class ProductEventModelController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventModelController.class);

	@Autowired
	private ProductEventModelService productEventModelService;

	@Autowired
	private ProductEventModelRpoDtoMapping productEventModelRpoDtoMapping;

	@Autowired
	private ProductEventModelUpdateRpoToProductEventModelUpdateDtoMapping productEventModelUpdateRpoToProductEventModelUpdateDtoMapping;

	@ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "??????????????????????????????", value = "????????????????????????????????????", required = true) @Valid ProductEventModelListRpo productEventModelListRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productEventModelListRpo= [ {} ]", productEventModelListRpo);
		}
		List<ProductEventModelAddRpo> list = productEventModelListRpo.getProductEventModelList();
		for (ProductEventModelAddRpo productEventModelAddRpo : list) {
			ProductEventModelDto productEventModelDto = productEventModelRpoDtoMapping.targetToSource(productEventModelAddRpo);
			addUsernameAndDate(productEventModelDto, true);
			productEventModelService.saveEntity(productEventModelDto);
		}


		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????????????????", notes = "??????????????????????????????")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "??????????????????????????????", value = "????????????????????????????????????", required = true) @Valid ProductEventModelListUpdateRpo productEventModelListUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productEventModelListUpdateRpo= [ {} ]", productEventModelListUpdateRpo);
		}
		
		List<ProductEventModelUpdateRpo> list = productEventModelListUpdateRpo.getProductEventModelList();
		for (ProductEventModelUpdateRpo productEventModelUpdateRpo : list) {
			ProductEventModelUpdateDto productEventModelUpdateDto = productEventModelUpdateRpoToProductEventModelUpdateDtoMapping.targetToSource(productEventModelUpdateRpo);
			addUsername(productEventModelUpdateDto, false);
			productEventModelService.updateEntity(productEventModelUpdateDto);
		}
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????????????????", notes = "????????????????????????(????????????)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "????????????????????????", value = "??????????????????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		productEventModelService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "????????????????????????", notes = "????????????????????????(????????????)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "????????????????????????", value = "??????????????????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		productEventModelService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????????????????", notes = "????????????????????????????????????")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductEventModelDto>>> findAll() {
		List<ProductEventModelDto> list = productEventModelService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "???????????????????????????????????????", notes = "?????????????????????????????????????????????")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductEventModelDto>>> findPage(
			@RequestBody @ApiParam(name = "????????????????????????", value = "?????????????????????????????????????????????", required = true) ProductEventModelSearchRpo productEventModelSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productEventModelSearchRpo);
		Page<ProductEventModelDto> productEventModels = productEventModelService.findPage(searchParams, productEventModelSearchRpo.getPageNumber(),
				productEventModelSearchRpo.getPageSize(), productEventModelSearchRpo.getSortTypes());
		SmartPage<ProductEventModelDto> smartPage = new SmartPage<ProductEventModelDto>(productEventModels.getSize(), productEventModels.getTotalPages(),
				productEventModels.getTotalElements(), productEventModels.getNumber() + 1, productEventModels.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
