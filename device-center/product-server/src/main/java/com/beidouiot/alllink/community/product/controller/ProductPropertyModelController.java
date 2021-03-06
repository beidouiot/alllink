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
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductPropertyModelRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductPropertyModelUpdateRpoToProductPropertyModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductPropertyModelAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductPropertyModelListRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductPropertyModelListUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductPropertyModelSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductPropertyModelUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductPropertyModelService;

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
@RequestMapping(value = ServiceConstants.PRODUCT_PROPERTY_MODEL_URI_BASE, produces = "application/json; charset=UTF-8")
public class ProductPropertyModelController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPropertyModelController.class);

	@Autowired
	private ProductPropertyModelService productPropertyModelService;

	@Autowired
	private ProductPropertyModelRpoDtoMapping productPropertyModelRpoDtoMapping;

	@Autowired
	private ProductPropertyModelUpdateRpoToProductPropertyModelUpdateDtoMapping productPropertyModelUpdateRpoToProductPropertyModelUpdateDtoMapping;

	@ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "??????????????????????????????", value = "????????????????????????????????????", required = true) @Valid ProductPropertyModelListRpo productPropertyModelListRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productPropertyModelListRpo= [ {} ]", productPropertyModelListRpo);
		}
		List<ProductPropertyModelAddRpo> list = productPropertyModelListRpo.getProductPropertyModelList();
		for (ProductPropertyModelAddRpo productPropertyModelAddRpo : list) {
			ProductPropertyModelDto productPropertyModelDto = productPropertyModelRpoDtoMapping.targetToSource(productPropertyModelAddRpo);
			addUsernameAndDate(productPropertyModelDto, true);
			productPropertyModelService.saveEntity(productPropertyModelDto);
		}
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????????????????", notes = "??????????????????????????????")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "??????????????????????????????", value = "????????????????????????????????????", required = true) @Valid ProductPropertyModelListUpdateRpo productPropertyModelListUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productPropertyModelListUpdateRpo= [ {} ]", productPropertyModelListUpdateRpo);
		}
		List<ProductPropertyModelUpdateRpo> list = productPropertyModelListUpdateRpo.getProductPropertyModelList();
		for (ProductPropertyModelUpdateRpo productPropertyModelUpdateRpo : list) {
			ProductPropertyModelUpdateDto productPropertyModelUpdateDto = productPropertyModelUpdateRpoToProductPropertyModelUpdateDtoMapping.targetToSource(productPropertyModelUpdateRpo);
			addUsername(productPropertyModelUpdateDto, false);
			productPropertyModelService.updateEntity(productPropertyModelUpdateDto);
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
		productPropertyModelService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "????????????????????????", notes = "????????????????????????(????????????)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "????????????????????????", value = "??????????????????????????????????????????", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productPropertyModelService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????????????????", notes = "????????????????????????????????????")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductPropertyModelDto>>> findAll() {
		List<ProductPropertyModelDto> list = productPropertyModelService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "???????????????????????????????????????", notes = "?????????????????????????????????????????????")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductPropertyModelDto>>> findPage(
			@RequestBody @ApiParam(name = "????????????????????????", value = "?????????????????????????????????????????????", required = true) ProductPropertyModelSearchRpo productPropertyModelSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productPropertyModelSearchRpo);
		Page<ProductPropertyModelDto> productPropertyModels = productPropertyModelService.findPage(searchParams, productPropertyModelSearchRpo.getPageNumber(),
				productPropertyModelSearchRpo.getPageSize(), productPropertyModelSearchRpo.getSortTypes());
		SmartPage<ProductPropertyModelDto> smartPage = new SmartPage<ProductPropertyModelDto>(productPropertyModels.getSize(), productPropertyModels.getTotalPages(),
				productPropertyModels.getTotalElements(), productPropertyModels.getNumber() + 1, productPropertyModels.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
