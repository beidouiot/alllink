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
import com.beidouiot.alllink.community.common.data.mapping.product.server.product.ProductRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.product.ProductUpdateRpoToProductUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.product.ProductAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.product.ProductSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.product.ProductUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022???2???15???
*/
@Api(tags = "????????????")
@RestController
@RequestMapping(value = ServiceConstants.PRODUCT_URI_BASE, produces = "application/json; charset=UTF-8")
public class ProductController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRpoDtoMapping productRpoDtoMapping;

	@Autowired
	private ProductUpdateRpoToProductUpdateDtoMapping productUpdateRpoToProductUpdateDtoMapping;

	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "??????????????????", value = "????????????????????????", required = true) @Valid ProductAddRpo productAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productAddRpo= [ {} ]", productAddRpo);
		}
		ProductDto productDto = productRpoDtoMapping.targetToSource(productAddRpo);
		addUsernameAndDate(productDto, true);
		productService.saveEntity(productDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????", notes = "??????????????????")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "??????????????????", value = "????????????????????????", required = true) @Valid ProductUpdateRpo productUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productUpdateRpo= [ {} ]", productUpdateRpo);
		}
		ProductUpdateDto productUpdateDto = productUpdateRpoToProductUpdateDtoMapping.targetToSource(productUpdateRpo);
		addUsername(productUpdateDto, false);
		productService.updateEntity(productUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????", notes = "????????????(????????????)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "????????????", value = "??????????????????????????????", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "????????????", notes = "????????????(????????????)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "????????????", value = "??????????????????????????????", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????", notes = "????????????????????????")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductDto>>> findAll() {
		List<ProductDto> list = productService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "???????????????????????????", notes = "?????????????????????????????????")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductDto>>> findPage(
			@RequestBody @ApiParam(name = "????????????", value = "?????????????????????????????????", required = true) ProductSearchRpo productSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productSearchRpo);
		Page<ProductDto> products = productService.findPage(searchParams, productSearchRpo.getPageNumber(),
				productSearchRpo.getPageSize(), productSearchRpo.getSortTypes());
		SmartPage<ProductDto> smartPage = new SmartPage<ProductDto>(products.getSize(), products.getTotalPages(),
				products.getTotalElements(), products.getNumber() + 1, products.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
	@ApiOperation(value = "????????????", notes = "??????id????????????")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "??????ID", value = "??????ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		ProductDto productDto = productService.findById(id.getId());
		return makeSuccessResponseEntity(productDto, HttpStatus.OK);
	}
	
}
