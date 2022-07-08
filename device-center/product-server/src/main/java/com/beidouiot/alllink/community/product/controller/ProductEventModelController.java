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
* @date 2022年2月16日
*/
@Api(tags = "产品事件模型管理")
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

	@ApiOperation(value = "产品事件模型信息添加", notes = "产品事件模型信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增产品事件模型信息", value = "产品事件模型信息请求参数", required = true) @Valid ProductEventModelListRpo productEventModelListRpo) {
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
	
	@ApiOperation(value = "产品事件模型修改", notes = "产品事件模型信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改产品事件模型信息", value = "产品事件模型信息请求参数", required = true) @Valid ProductEventModelListUpdateRpo productEventModelListUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
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
	
	@ApiOperation(value = "删除产品事件模型", notes = "删除产品事件模型(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除产品事件模型", value = "删除产品事件模型信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		productEventModelService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除产品事件模型", notes = "删除产品事件模型(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除产品事件模型", value = "删除产品事件模型信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		productEventModelService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有产品事件模型", notes = "查询所有产品事件模型信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductEventModelDto>>> findAll() {
		List<ProductEventModelDto> list = productEventModelService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询产品事件模型", notes = "按条件分页查询产品事件模型信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductEventModelDto>>> findPage(
			@RequestBody @ApiParam(name = "查询产品事件模型", value = "按条件产品事件模型信息请求参数", required = true) ProductEventModelSearchRpo productEventModelSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productEventModelSearchRpo);
		Page<ProductEventModelDto> productEventModels = productEventModelService.findPage(searchParams, productEventModelSearchRpo.getPageNumber(),
				productEventModelSearchRpo.getPageSize(), productEventModelSearchRpo.getSortTypes());
		SmartPage<ProductEventModelDto> smartPage = new SmartPage<ProductEventModelDto>(productEventModels.getSize(), productEventModels.getTotalPages(),
				productEventModels.getTotalElements(), productEventModels.getNumber() + 1, productEventModels.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
