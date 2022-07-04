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
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardModelDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardModelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "标准物模型管理")
@RestController
@RequestMapping(value = ServiceConstants.STANDARD_MODEL_URI_BASE, produces = "application/json; charset=UTF-8")
public class StandardModelController extends BaseController {


	private static final Logger LOGGER = LoggerFactory.getLogger(StandardModelController.class);

	@Autowired
	private StandardModelService standardModelService;
	
	
	
	@ApiOperation(value = "查询标准产品类别标准物模型信息", notes = "查询标准产品类别标准物模型信息")
	@PostMapping("v1/findOne")
	public ResponseEntity<ResultDataRro<StandardModelDto>> findStandardModel(@RequestBody @ApiParam(name = "产品类别ID", value = "产品类别ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		StandardModelDto standardModelDto = standardModelService.findStandardModels(id.getId());
		return makeSuccessResponseEntity(standardModelDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "发布标准产品类别标准物模型信息", notes = "发布标准产品类别标准物模型信息")
	@PostMapping("v1/pubModel")
	public ResponseEntity<ResultDataRro<Object>> pubStandardModel(@RequestBody @ApiParam(name = "产品类别ID", value = "产品类别ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		Boolean pubStats = standardModelService.pubStandardModel(id.getId());
		
		return makeSuccessResponseEntity(pubStats, HttpStatus.OK);
	}
}
