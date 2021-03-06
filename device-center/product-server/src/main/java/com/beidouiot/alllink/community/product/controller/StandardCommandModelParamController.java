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
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardCommandModelParamRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardCommandModelParamUpdateRpoToStandardCommandModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardCommandModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardCommandModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardCommandModelParamAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardCommandModelParamSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardCommandModelParamUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardCommandModelParamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description TODO
* @author longww
* @date 2022???2???16???
*/
@Api(tags = "??????????????????????????????")
@RestController
@RequestMapping(value = ServiceConstants.STANDARD_COMMAND_MODEL_PARAM_URI_BASE, produces = "application/json; charset=UTF-8")
public class StandardCommandModelParamController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardCommandModelParamController.class);

	@Autowired
	private StandardCommandModelParamService standardCommandModelParamService;

	@Autowired
	private StandardCommandModelParamRpoDtoMapping standardCommandModelParamRpoDtoMapping;

	@Autowired
	private StandardCommandModelParamUpdateRpoToStandardCommandModelParamUpdateDtoMapping standardCommandModelParamUpdateRpoToStandardCommandModelParamUpdateDtoMapping;

	@ApiOperation(value = "????????????????????????????????????", notes = "????????????????????????????????????")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "????????????????????????????????????", value = "??????????????????????????????????????????", required = true) @Valid StandardCommandModelParamAddRpo standardCommandModelParamAddRpo) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardCommandModelParamAddRpo= [ {} ]", standardCommandModelParamAddRpo);
		}
		StandardCommandModelParamDto standardCommandModelParamDto = standardCommandModelParamRpoDtoMapping.targetToSource(standardCommandModelParamAddRpo);
		addUsernameAndDate(standardCommandModelParamDto, true);
		standardCommandModelParamService.saveEntity(standardCommandModelParamDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "??????????????????????????????", notes = "????????????????????????????????????")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "????????????????????????????????????", value = "??????????????????????????????????????????", required = true) @Valid StandardCommandModelParamUpdateRpo standardCommandModelParamUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if ( LOGGER.isDebugEnabled() ) {
		LOGGER.debug("standardCommandModelParamUpdateRpo= [ {} ]", standardCommandModelParamUpdateRpo);
		}
		StandardCommandModelParamUpdateDto standardCommandModelParamUpdateDto = standardCommandModelParamUpdateRpoToStandardCommandModelParamUpdateDtoMapping.targetToSource(standardCommandModelParamUpdateRpo);
		addUsername(standardCommandModelParamUpdateDto, false);
		standardCommandModelParamService.updateEntity(standardCommandModelParamUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????(????????????)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "??????????????????????????????", value = "????????????????????????????????????????????????", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardCommandModelParamService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????(????????????)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "??????????????????????????????", value = "????????????????????????????????????????????????", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		standardCommandModelParamService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????????????????", notes = "??????ID????????????????????????")
	@DeleteMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "????????????????????????ID", value = "????????????????????????ID", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		StandardCommandModelParamDto standardCommandModelParamDto = standardCommandModelParamService.findById(id.getId());
		return makeSuccessResponseEntity(standardCommandModelParamDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "????????????????????????????????????", notes = "??????????????????????????????????????????")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<StandardCommandModelParamDto>>> findAll() {
		List<StandardCommandModelParamDto> list = standardCommandModelParamService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "?????????????????????????????????????????????", notes = "???????????????????????????????????????????????????")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<StandardCommandModelParamDto>>> findPage(
			@RequestBody @ApiParam(name = "??????????????????????????????", value = "???????????????????????????????????????????????????", required = true) StandardCommandModelParamSearchRpo standardCommandModelParamSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(standardCommandModelParamSearchRpo);
		Page<StandardCommandModelParamDto> standardCommandModelParams = standardCommandModelParamService.findPage(searchParams, standardCommandModelParamSearchRpo.getPageNumber(),
				standardCommandModelParamSearchRpo.getPageSize(), standardCommandModelParamSearchRpo.getSortTypes());
		SmartPage<StandardCommandModelParamDto> smartPage = new SmartPage<StandardCommandModelParamDto>(standardCommandModelParams.getSize(), standardCommandModelParams.getTotalPages(),
				standardCommandModelParams.getTotalElements(), standardCommandModelParams.getNumber() + 1, standardCommandModelParams.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
}
