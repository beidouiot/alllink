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
import com.beidouiot.alllink.community.common.data.mapping.product.server.productType.ProductTypeRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productType.ProductTypeUpdateRpoToProductTypeUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeIndustryTypeDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.producttype.ProductTypeAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.producttype.ProductTypeSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.producttype.ProductTypeUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.producttypeindustrytype.ProductTypeIndustryTpyeAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月15日
*/
@Api(tags = "产品类别管理")
@RestController
@RequestMapping(value = ServiceConstants.PRODUCT_TYPE_URI_BASE, produces = "application/json; charset=UTF-8")
public class ProductTypeController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeController.class);

	@Autowired
	private ProductTypeService productTypeService;

	@Autowired
	private ProductTypeRpoDtoMapping productTypeRpoDtoMapping;

	@Autowired
	private ProductTypeUpdateRpoToProductTypeUpdateDtoMapping productTypeUpdateRpoToProductTypeUpdateDtoMapping;

	@ApiOperation(value = "产品类别信息添加", notes = "产品类别信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增产品类别信息", value = "产品类别信息请求参数", required = true) @Valid ProductTypeAddRpo productTypeAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productTypeAddRpo= [ {} ]", productTypeAddRpo);
		}
		ProductTypeDto productTypeDto = productTypeRpoDtoMapping.targetToSource(productTypeAddRpo);
		addUsernameAndDate(productTypeDto, true);
		productTypeService.saveEntity(productTypeDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "产品类别修改", notes = "产品类别信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改产品类别信息", value = "产品类别信息请求参数", required = true) @Valid ProductTypeUpdateRpo productTypeUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productTypeUpdateRpo= [ {} ]", productTypeUpdateRpo);
		}
		ProductTypeUpdateDto productTypeUpdateDto = productTypeUpdateRpoToProductTypeUpdateDtoMapping.targetToSource(productTypeUpdateRpo);
		addUsername(productTypeUpdateDto, false);
		productTypeService.updateEntity(productTypeUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除产品类别", notes = "删除产品类别(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除产品类别", value = "删除产品类别信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productTypeService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除产品类别", notes = "删除产品类别(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除产品类别", value = "删除产品类别信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productTypeService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询产品类别", notes = "根据id产品类别")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "产品类别ID", value = "产品类别ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		ProductTypeDto productTypeDto = productTypeService.findById(id.getId());
		return makeSuccessResponseEntity(productTypeDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有产品类别", notes = "查询所有产品类别信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductTypeDto>>> findAll() {
		List<ProductTypeDto> list = productTypeService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询产品类别", notes = "按条件分页查询产品类别信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductTypeDto>>> findPage(
			@RequestBody @ApiParam(name = "查询产品类别", value = "按条件产品类别信息请求参数", required = true) ProductTypeSearchRpo productTypeSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productTypeSearchRpo);
		Page<ProductTypeDto> productTypes = productTypeService.findPage(searchParams, productTypeSearchRpo.getPageNumber(),
				productTypeSearchRpo.getPageSize(), productTypeSearchRpo.getSortTypes());
		SmartPage<ProductTypeDto> smartPage = new SmartPage<ProductTypeDto>(productTypes.getSize(), productTypes.getTotalPages(),
				productTypes.getTotalElements(), productTypes.getNumber() + 1, productTypes.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
	@ApiOperation(value = "产品类别配置行业类别", notes = "产品类别配置行业类别信息")
	@PostMapping("v1/configure-industry-type")
	public ResponseEntity<ResultDataRro<Object>> roleConfigureUser(@RequestBody @ApiParam(name = "产品类别配置行业类别", value = "产品类别配置行业类别请求参数", required = true) @Valid ProductTypeIndustryTpyeAddRpo productTypeIndustryTpyeAddRpo) {
		LOGGER.debug("roleUserAddRpo= [ {} ]", productTypeIndustryTpyeAddRpo);
		ProductTypeIndustryTypeDto productTypeIndustryTypeDto = new ProductTypeIndustryTypeDto();
		productTypeIndustryTypeDto.setProductTypeId(productTypeIndustryTpyeAddRpo.getProductTypeId());
		productTypeIndustryTypeDto.setIndustryTypeIds(productTypeIndustryTpyeAddRpo.getIndustryTypeIds());
		productTypeService.saveProductTypeIndustryType(productTypeIndustryTypeDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
}
