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
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductPropertyModelParamRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductPropertyModelParamUpdateRpoToProductPropertyModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductPropertyModelParamAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductPropertyModelParamSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductPropertyModelParamUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductPropertyModelParamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月16日
*/
@Api(tags = "产品属性模型参数管理")
@RestController
@RequestMapping(value = ServiceConstants.PRODUCT_PROPERTY_MODEL_PARAM_URI_BASE, produces = "application/json; charset=UTF-8")
public class ProductPropertyModelParamController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPropertyModelParamController.class);

	@Autowired
	private ProductPropertyModelParamService productPropertyModelParamService;

	@Autowired
	private ProductPropertyModelParamRpoDtoMapping productPropertyModelParamRpoDtoMapping;

	@Autowired
	private ProductPropertyModelParamUpdateRpoToProductPropertyModelParamUpdateDtoMapping productPropertyModelParamUpdateRpoToProductPropertyModelParamUpdateDtoMapping;

	@ApiOperation(value = "产品属性模型参数信息添加", notes = "产品属性模型参数信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增产品属性模型参数信息", value = "产品属性模型参数信息请求参数", required = true) @Valid ProductPropertyModelParamAddRpo productPropertyModelParamAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productPropertyModelParamAddRpo= [ {} ]", productPropertyModelParamAddRpo);
		}
		ProductPropertyModelParamDto productPropertyModelParamDto = productPropertyModelParamRpoDtoMapping.targetToSource(productPropertyModelParamAddRpo);
		addUsernameAndDate(productPropertyModelParamDto, true);
		productPropertyModelParamService.saveEntity(productPropertyModelParamDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "产品属性模型参数修改", notes = "产品属性模型参数信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改产品属性模型参数信息", value = "产品属性模型参数信息请求参数", required = true) @Valid ProductPropertyModelParamUpdateRpo productPropertyModelParamUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productPropertyModelParamUpdateRpo= [ {} ]", productPropertyModelParamUpdateRpo);
		}
		ProductPropertyModelParamUpdateDto productPropertyModelParamDto = productPropertyModelParamUpdateRpoToProductPropertyModelParamUpdateDtoMapping.targetToSource(productPropertyModelParamUpdateRpo);
		addUsername(productPropertyModelParamDto, false);
		productPropertyModelParamService.updateEntity(productPropertyModelParamDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除产品属性模型参数", notes = "删除产品属性模型参数(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除产品属性模型参数", value = "删除产品属性模型参数信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productPropertyModelParamService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除产品属性模型参数", notes = "删除产品属性模型参数(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除产品属性模型参数", value = "删除产品属性模型参数信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productPropertyModelParamService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询产品属性模型参数", notes = "根据ID查询产品属性模型参数")
	@DeleteMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "产品属性模型参数ID", value = "产品属性模型参数ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		ProductPropertyModelParamDto productPropertyModelParamDto = productPropertyModelParamService.findById(id.getId());
		return makeSuccessResponseEntity(productPropertyModelParamDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有产品属性模型参数", notes = "查询所有产品属性模型参数信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<ProductPropertyModelParamDto>>> findAll() {
		List<ProductPropertyModelParamDto> list = productPropertyModelParamService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询产品属性模型参数", notes = "按条件分页查询产品属性模型参数信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<ProductPropertyModelParamDto>>> findPage(
			@RequestBody @ApiParam(name = "查询产品属性模型参数", value = "按条件产品属性模型参数信息请求参数", required = true) ProductPropertyModelParamSearchRpo productPropertyModelParamSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(productPropertyModelParamSearchRpo);
		Page<ProductPropertyModelParamDto> productPropertyModelParams = productPropertyModelParamService.findPage(searchParams, productPropertyModelParamSearchRpo.getPageNumber(),
				productPropertyModelParamSearchRpo.getPageSize(), productPropertyModelParamSearchRpo.getSortTypes());
		SmartPage<ProductPropertyModelParamDto> smartPage = new SmartPage<ProductPropertyModelParamDto>(productPropertyModelParams.getSize(), productPropertyModelParams.getTotalPages(),
				productPropertyModelParams.getTotalElements(), productPropertyModelParams.getNumber() + 1, productPropertyModelParams.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
