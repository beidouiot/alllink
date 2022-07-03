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
 * @Description 租户信息管理
 * @author longww
 * @date 2021年4月11日
 */
@Api(tags = "租户信息管理")
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
	
	@ApiOperation(value = "租户信息添加", notes = "租户信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(@RequestBody @ApiParam(name = "新增租户信息", value = "新增租户信息请求参数", required = true) @Valid TenantAddRpo tenantAddRpo) {
		LOGGER.debug("tenantAddRpo= [ {} ]", tenantAddRpo);
		TenantDto tenantDto = tenantRpoDtoMapping.targetToSource(tenantAddRpo);
		addUsernameAndDate(tenantDto, true);
		tenantService.saveEntity(tenantDto);
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "修改租户", notes = "租户信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(@RequestBody @ApiParam(name = "修改租户信息", value = "修改租户信息请求参数", required = true) @Valid TenantUpdateRpo tenantUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("tenantAddRpo= [ {} ]", tenantUpdateRpo);
		TenantUpdateDto tenantUpdateDto = tenantUpdateRpoToTenantUpdateDtoMapping.targetToSource(tenantUpdateRpo);
		addUsername(tenantUpdateDto, false);
		tenantService.updateEntity(tenantUpdateDto);
		
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除租户", notes = "删除租户(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(@RequestBody @ApiParam(name = "删除租户", value = "删除租户信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		tenantService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "删除租户", notes = "删除租户(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(@RequestBody @ApiParam(name = "删除租户", value = "删除租户信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		tenantService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询一个租户", notes = "根据id查询租户")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findOne(@RequestBody @ApiParam(name = "查询租户", value = "根据ID查询租户信息", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		TenantDto tenantDto = tenantService.findById(id.getId());
		return makeSuccessResponseEntity(tenantDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有租户", notes = "查询所有租户信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<TenantDto>>> findAll() {
		List<TenantDto> list = tenantService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "按条件分页查询租户", notes = "按条件分页查询租户信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<TenantDto>>> findPage(@RequestBody @ApiParam(name = "查询租户", value = "按条件租户信息请求参数", required = true) TenantSearchRpo tenantSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(tenantSearchRpo);
		Page<TenantDto> tenants = tenantService.findPage(searchParams, tenantSearchRpo.getPageNumber(),
				tenantSearchRpo.getPageSize(), tenantSearchRpo.getSortTypes());
		SmartPage<TenantDto> smartPage = new SmartPage<TenantDto>(tenants.getSize(), tenants.getTotalPages(), tenants.getTotalElements(),
				tenants.getNumber() + 1, tenants.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
}
