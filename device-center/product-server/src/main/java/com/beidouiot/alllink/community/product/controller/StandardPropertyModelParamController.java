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
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardPropertyModelParamRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardPropertyModelParamUpdateRpoToStandardPropertyModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardPropertyModelParamAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardPropertyModelParamSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardPropertyModelParamUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardPropertyModelParamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * @Description TODO
 * @author longww
 * @date 2022年2月15日
 */
@Api(tags = "标准属性模型参数管理")
@RestController
@RequestMapping(value = ServiceConstants.STANDARD_PROPERTY_MODEL_PARAM_URI_BASE, produces = "application/json; charset=UTF-8")
public class StandardPropertyModelParamController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardPropertyModelParamController.class);

	@Autowired
	private StandardPropertyModelParamService standardPropertyModelParamService;

	@Autowired
	private StandardPropertyModelParamRpoDtoMapping standardPropertyModelParamRpoDtoMapping;

	@Autowired
	private StandardPropertyModelParamUpdateRpoToStandardPropertyModelParamUpdateDtoMapping standardPropertyModelParamUpdateRpoToStandardPropertyModelParamDtoMapping;

	@ApiOperation(value = "标准属性模型参数信息添加", notes = "标准属性模型参数信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增标准属性模型参数信息", value = "标准属性模型参数信息请求参数", required = true) @Valid StandardPropertyModelParamAddRpo standardPropertyModelParamAddRpo) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("standardPropertyModelParamAddRpo= [ {} ]", standardPropertyModelParamAddRpo);
		}
		StandardPropertyModelParamDto standardPropertyModelParamDto = standardPropertyModelParamRpoDtoMapping
				.targetToSource(standardPropertyModelParamAddRpo);
		addUsernameAndDate(standardPropertyModelParamDto, true);
		standardPropertyModelParamService.saveEntity(standardPropertyModelParamDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "标准属性模型参数修改", notes = "标准属性模型参数信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改标准属性模型参数信息", value = "标准属性模型参数信息请求参数", required = true) @Valid StandardPropertyModelParamUpdateRpo standardPropertyModelParamUpdateRpo)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchFieldException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("standardPropertyModelParamUpdateRpo= [ {} ]", standardPropertyModelParamUpdateRpo);
		}
		StandardPropertyModelParamUpdateDto standardPropertyModelParamUpdateDto = standardPropertyModelParamUpdateRpoToStandardPropertyModelParamDtoMapping
				.targetToSource(standardPropertyModelParamUpdateRpo);
		addUsername(standardPropertyModelParamUpdateDto, false);
		standardPropertyModelParamService.updateEntity(standardPropertyModelParamUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "删除标准属性模型参数", notes = "删除标准属性模型参数(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除标准属性模型参数", value = "删除标准属性模型参数信息请求参数", required = true) @Valid ID id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("id= {}", id);
		}
		standardPropertyModelParamService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除标准属性模型参数", notes = "删除标准属性模型参数(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除标准属性模型参数", value = "删除标准属性模型参数信息请求参数", required = true) @Valid ID id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("id= {}", id);
		}
		standardPropertyModelParamService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询标准属性模型参数", notes = "根据ID查询标准属性模型参数")
	@DeleteMapping("v1/findOne")
	public ResponseEntity<?> findOne(
			@RequestBody @ApiParam(name = "标准属性模型参数ID", value = "标准属性模型参数ID", required = true) @Valid ID id) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("id= {}", id);
		}
		StandardPropertyModelParamDto tandardPropertyModelParamDto = standardPropertyModelParamService.findById(id.getId());
		return makeSuccessResponseEntity(tandardPropertyModelParamDto, HttpStatus.OK);
	}

	@ApiOperation(value = "查询所有标准属性模型参数", notes = "查询所有标准属性模型参数信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<StandardPropertyModelParamDto>>> findAll() {
		List<StandardPropertyModelParamDto> list = standardPropertyModelParamService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询标准属性模型参数", notes = "按条件分页查询标准属性模型参数信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<StandardPropertyModelParamDto>>> findPage(
			@RequestBody @ApiParam(name = "查询标准属性模型参数", value = "按条件标准属性模型参数信息请求参数", required = true) StandardPropertyModelParamSearchRpo standardPropertyModelParamSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(standardPropertyModelParamSearchRpo);
		Page<StandardPropertyModelParamDto> standardPropertyModelParams = standardPropertyModelParamService.findPage(
				searchParams, standardPropertyModelParamSearchRpo.getPageNumber(),
				standardPropertyModelParamSearchRpo.getPageSize(), standardPropertyModelParamSearchRpo.getSortTypes());
		SmartPage<StandardPropertyModelParamDto> smartPage = new SmartPage<StandardPropertyModelParamDto>(
				standardPropertyModelParams.getSize(), standardPropertyModelParams.getTotalPages(),
				standardPropertyModelParams.getTotalElements(), standardPropertyModelParams.getNumber() + 1,
				standardPropertyModelParams.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}

}
