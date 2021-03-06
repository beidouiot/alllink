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
import com.beidouiot.alllink.community.common.data.mapping.user.center.tenant.TenantRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.tenant.TenantUpdateRpoToTenantUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.TenantDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.TenantUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.ID;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.tenant.TenantAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.tenant.TenantSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.tenant.TenantUpdateRpo;
import com.beidouiot.alllink.community.user.center.dao.service.api.TenantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 *
 * @Description ??????????????????
 * @author longww
 * @date 2021???4???11???
 */
@Api(tags = "??????????????????")
@RestController
@RequestMapping(value = ServiceConstants.TENANT_URI_BASE, produces = "application/json; charset=UTF-8")
public class TenantController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TenantController.class);
	
	@Autowired
	private TenantService tenantService;
	
	@Autowired
	private TenantRpoDtoMapping tenantRpoDtoMapping;
	
	@Autowired
	private TenantUpdateRpoToTenantUpdateDtoMapping tenantUpdateRpoToTenantUpdateDtoMapping;
	
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(@RequestBody @ApiParam(name = "??????????????????", value = "??????????????????????????????", required = true) @Valid TenantAddRpo tenantAddRpo) {
		LOGGER.debug("tenantAddRpo= [ {} ]", tenantAddRpo);
		TenantDto tenantDto = tenantRpoDtoMapping.targetToSource(tenantAddRpo);
		addUsernameAndDate(tenantDto, true);
		tenantService.saveEntity(tenantDto);
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????", notes = "??????????????????")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(@RequestBody @ApiParam(name = "??????????????????", value = "??????????????????????????????", required = true) @Valid TenantUpdateRpo tenantUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("tenantAddRpo= [ {} ]", tenantUpdateRpo);
		TenantUpdateDto tenantUpdateDto = tenantUpdateRpoToTenantUpdateDtoMapping.targetToSource(tenantUpdateRpo);
		addUsername(tenantUpdateDto, false);
		tenantService.updateEntity(tenantUpdateDto);
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????", notes = "????????????(????????????)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(@RequestBody @ApiParam(name = "????????????", value = "??????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		tenantService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "????????????", notes = "????????????(????????????)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(@RequestBody @ApiParam(name = "????????????", value = "??????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		tenantService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????", notes = "??????id????????????")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findOne(@RequestBody @ApiParam(name = "????????????", value = "??????ID??????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		TenantDto tenantDto = tenantService.findById(id.getId());
		return makeSuccessResponseEntity(tenantDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????", notes = "????????????????????????")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<TenantDto>>> findAll() {
		List<TenantDto> list = tenantService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "???????????????????????????", notes = "?????????????????????????????????")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<TenantDto>>> findPage(@RequestBody @ApiParam(name = "????????????", value = "?????????????????????????????????", required = true) TenantSearchRpo tenantSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(tenantSearchRpo);
		Page<TenantDto> tenants = tenantService.findPage(searchParams, tenantSearchRpo.getPageNumber(),
				tenantSearchRpo.getPageSize(), tenantSearchRpo.getSortTypes());
		SmartPage<TenantDto> smartPage = new SmartPage<TenantDto>(tenants.getSize(), tenants.getTotalPages(), tenants.getTotalElements(),
				tenants.getNumber() + 1, tenants.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
}
