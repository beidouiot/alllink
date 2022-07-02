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
import com.beidouiot.alllink.community.common.data.mapping.product.server.industryType.IndustryTypeRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.industryType.IndustryTypeUpdateRpoToIndustryTypeUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.IndustryTypeDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.IndustryTypeUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.industrytype.IndustryTypeAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.industrytype.IndustryTypeSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.industrytype.IndustryTypeUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.IndustryTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * @Description TODO
 * @author longww
 * @date 2022年2月15日
 */
@Api(tags = "行业类型管理")
@RestController
@RequestMapping(value = ServiceConstants.INDUSTRY_TYPE_URI_BASE, produces = "application/json; charset=UTF-8")
public class IndustryTypeController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndustryTypeController.class);

	@Autowired
	private IndustryTypeService industryTypeService;

	@Autowired
	private IndustryTypeRpoDtoMapping industryTypeRpoDtoMapping;

	@Autowired
	private IndustryTypeUpdateRpoToIndustryTypeUpdateDtoMapping industryTypeUpdateRpoToIndustryTypeUpdateDtoMapping;

	@ApiOperation(value = "行业类型信息添加", notes = "行业类型信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增行业类型信息", value = "行业类型信息请求参数", required = true) @Valid IndustryTypeAddRpo industryTypeAddRpo) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("industryTypeAddRpo= [ {} ]", industryTypeAddRpo);
		}

		IndustryTypeDto industryTypeDto = industryTypeRpoDtoMapping.targetToSource(industryTypeAddRpo);
		addUsernameAndDate(industryTypeDto, true);
		industryTypeService.saveEntity(industryTypeDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "行业类型修改", notes = "行业类型信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改行业类型信息", value = "行业类型信息请求参数", required = true) @Valid IndustryTypeUpdateRpo industryTypeUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("industryTypeUpdateRpo= [ {} ]", industryTypeUpdateRpo);
		}
		IndustryTypeUpdateDto industryTypeUpdateDto = industryTypeUpdateRpoToIndustryTypeUpdateDtoMapping
				.targetToSource(industryTypeUpdateRpo);
		addUsername(industryTypeUpdateDto, false);
		industryTypeService.updateEntity(industryTypeUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "删除行业类型", notes = "删除行业类型(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除行业类型", value = "删除行业类型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		industryTypeService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除行业类型", notes = "删除行业类型(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除行业类型", value = "删除行业类型信息请求参数", required = true) @Valid ID id) {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("id= {}", id);
		}
		industryTypeService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "查询所有行业类型", notes = "查询所有行业类型信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<IndustryTypeDto>>> findAll() {
		List<IndustryTypeDto> list = industryTypeService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询行业类型", notes = "按条件分页查询行业类型信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<IndustryTypeDto>>> findPage(
			@RequestBody @ApiParam(name = "查询行业类型", value = "按条件行业类型信息请求参数", required = true) IndustryTypeSearchRpo industryTypeSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(industryTypeSearchRpo);
		Page<IndustryTypeDto> industryTypes = industryTypeService.findPage(searchParams,
				industryTypeSearchRpo.getPageNumber(), industryTypeSearchRpo.getPageSize(),
				industryTypeSearchRpo.getSortTypes());
		SmartPage<IndustryTypeDto> smartPage = new SmartPage<IndustryTypeDto>(industryTypes.getSize(),
				industryTypes.getTotalPages(), industryTypes.getTotalElements(), industryTypes.getNumber() + 1,
				industryTypes.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}

}
