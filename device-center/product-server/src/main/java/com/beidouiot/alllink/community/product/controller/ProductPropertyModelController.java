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
* @date 2022年2月16日
*/
@Api(tags = "产品属性模型管理")
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

	@ApiOperation(value = "产品属性模型信息添加", notes = "产品属性模型信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增产品属性模型信息", value = "产品属性模型信息请求参数", required = true) @Valid ProductPropertyModelAddRpo productPropertyModelAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productPropertyModelAddRpo= [ {} ]", productPropertyModelAddRpo);
		}
		ProductPropertyModelDto productPropertyModelDto = productPropertyModelRpoDtoMapping.targetToSource(productPropertyModelAddRpo);
		addUsernameAndDate(productPropertyModelDto, true);
		productPropertyModelService.saveEntity(productPropertyModelDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "产品属性模型修改", notes = "产品属性模型信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改产品属性模型信息", value = "产品属性模型信息请求参数", required = true) @Valid ProductPropertyModelUpdateRpo productPropertyModelUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productPropertyModelUpdateRpo= [ {} ]", productPropertyModelUpdateRpo);
		}
		ProductPropertyModelUpdateDto productPropertyModelUpdateDto = productPropertyModelUpdateRpoToProductPropertyModelUpdateDtoMapping.targetToSource(productPropertyModelUpdateRpo);
		addUsername(productPropertyModelUpdateDto, false);
		productPropertyModelService.updateEntity(productPropertyModelUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除产品属性模型", notes = "删除产品属性模型(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除产品属性模型", value = "删除产品属性模型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productPropertyModelService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除产品属性模型", notes = "删除产品属性模型(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除产品属性模型", value = "删除产品属性模型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productPropertyModelService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有产品属性模型", notes = "查询所有产品属性模型信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductPropertyModelDto>>> findAll() {
		List<ProductPropertyModelDto> list = productPropertyModelService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询产品属性模型", notes = "按条件分页查询产品属性模型信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductPropertyModelDto>>> findPage(
			@RequestBody @ApiParam(name = "查询产品属性模型", value = "按条件产品属性模型信息请求参数", required = true) ProductPropertyModelSearchRpo productPropertyModelSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productPropertyModelSearchRpo);
		Page<ProductPropertyModelDto> productPropertyModels = productPropertyModelService.findPage(searchParams, productPropertyModelSearchRpo.getPageNumber(),
				productPropertyModelSearchRpo.getPageSize(), productPropertyModelSearchRpo.getSortTypes());
		SmartPage<ProductPropertyModelDto> smartPage = new SmartPage<ProductPropertyModelDto>(productPropertyModels.getSize(), productPropertyModels.getTotalPages(),
				productPropertyModels.getTotalElements(), productPropertyModels.getNumber() + 1, productPropertyModels.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
