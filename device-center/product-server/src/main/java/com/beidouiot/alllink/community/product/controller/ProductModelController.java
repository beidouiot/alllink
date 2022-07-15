package com.beidouiot.alllink.community.product.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.controller.BaseController;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductModelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月17日
*/

@Api(tags = "产品模型管理")
@RestController
@RequestMapping(value = ServiceConstants.PRODUCT_MODEL_URI_BASE, produces = "application/json; charset=UTF-8")
public class ProductModelController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductModelController.class);
	
	@Autowired
	private ProductModelService productModelService;
	
	@ApiOperation(value = "产品模型信息发布", notes = "产品模型信息发布")
	@PostMapping("v1/pubModel")
	public ResponseEntity<ResultDataRro<Object>> pubModel(
			@RequestBody @ApiParam(name = "发布产品模型信息", value = "产品模型信息发布请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		productModelService.publishModel(id.getId());
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "查询产品物模型信息", notes = "查询产品物模型信息")
	@PostMapping("v1/findOne")
	public ResponseEntity<ResultDataRro<ProductModelDto>> findStandardModel(@RequestBody @ApiParam(name = "产品ID", value = "产品ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		ProductModelDto productModelDto = productModelService.findProductModels(id.getId());
		return makeSuccessResponseEntity(productModelDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询已发布产品物模型信息", notes = "查询已发布产品物模型信息")
	@PostMapping("v1/findPubModel")
	public ResponseEntity<ResultDataRro<ProductModelDto>> findPubProductModel(@RequestBody @ApiParam(name = "产品ID", value = "产品ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		ProductModelDto productModelDto = productModelService.findPubProductModels(id.getId());
		return makeSuccessResponseEntity(productModelDto, HttpStatus.OK);
	}
	
}
