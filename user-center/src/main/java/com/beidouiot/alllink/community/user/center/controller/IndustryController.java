package com.beidouiot.alllink.community.user.center.controller;

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
import com.beidouiot.alllink.community.common.data.mapping.user.center.industry.IndustryRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.industry.IndustryUpdateRpoToIndustryUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.IndustryDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.IndustryUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.ID;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.industry.IndustryAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.industry.IndustrySearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.industry.IndustryUpdateRpo;
import com.beidouiot.alllink.community.user.center.dao.service.api.IndustryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 *
 * @Description 行业信息管理
 * @author longww
 * @date 2021年4月11日
 */
@Api(tags = "行业信息管理")
@RestController
@RequestMapping(value = ServiceConstants.INDUSTRY_URI_BASE, produces = "application/json; charset=UTF-8")
public class IndustryController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndustryController.class);

	@Autowired
	private IndustryService industryService;

	@Autowired
	private IndustryRpoDtoMapping industryRpoDtoMapping;

	@Autowired
	private IndustryUpdateRpoToIndustryUpdateDtoMapping industryUpdateRpoToIndustryUpdateDtoMapping;

	@ApiOperation(value = "行业信息添加", notes = "行业信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增行业信息", value = "行业信息请求参数", required = true) @Valid IndustryAddRpo industryAddRpo) {
		LOGGER.debug("industryAddRpo= [ {} ]", industryAddRpo);
		IndustryDto industryDto = industryRpoDtoMapping.targetToSource(industryAddRpo);
		addUsernameAndDate(industryDto, true);
		industryService.saveEntity(industryDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "行业修改", notes = "行业信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改行业信息", value = "行业信息请求参数", required = true) @Valid IndustryUpdateRpo industryUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("industryUpdateRpo= [ {} ]", industryUpdateRpo);
		IndustryUpdateDto industryUpdateDto = industryUpdateRpoToIndustryUpdateDtoMapping.targetToSource(industryUpdateRpo);
		addUsername(industryUpdateDto, false);
		industryService.updateEntity(industryUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "删除行业", notes = "删除行业(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除行业", value = "删除行业信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		industryService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除行业", notes = "删除行业(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除行业", value = "删除行业信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		industryService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "查询所有行业", notes = "查询所有行业信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<IndustryDto>>> findAll() {
		List<IndustryDto> list = industryService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询行业", notes = "按条件分页查询行业信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<IndustryDto>>> findPage(
			@RequestBody @ApiParam(name = "查询行业", value = "按条件行业信息请求参数", required = true) IndustrySearchRpo industrySearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(industrySearchRpo);
		Page<IndustryDto> industries = industryService.findPage(searchParams, industrySearchRpo.getPageNumber(),
				industrySearchRpo.getPageSize(), industrySearchRpo.getSortTypes());
		SmartPage<IndustryDto> smartPage = new SmartPage<IndustryDto>(industries.getSize(), industries.getTotalPages(),
				industries.getTotalElements(), industries.getNumber() + 1, industries.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
}
