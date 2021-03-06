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
import com.beidouiot.alllink.community.common.data.mapping.user.center.role.RoleRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.role.RoleUpdateRpoToRoleUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleMenuDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleUserDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.ID;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.role.RoleAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.role.RoleSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.role.RoleUpdateRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.rolemenu.RoleMenuAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.roleuser.RoleUserAddRpo;
import com.beidouiot.alllink.community.user.center.dao.service.api.RoleService;

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
@RequestMapping(value = ServiceConstants.ROLE_URI_BASE, produces = "application/json; charset=UTF-8")
public class RoleController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleRpoDtoMapping roleRpoDtoMapping;
	
	@Autowired
	private RoleUpdateRpoToRoleUpdateDtoMapping roleUpdateRpoToRoleUpdateDtoMapping;
	
	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(@RequestBody @ApiParam(name = "??????????????????", value = "????????????????????????", required = true) @Valid RoleAddRpo roleAddRpo) {
		LOGGER.debug("roleAddRpo= [ {} ]", roleAddRpo);
		RoleDto roleDto = roleRpoDtoMapping.targetToSource(roleAddRpo);
		addUsernameAndDate(roleDto,true);
		roleService.saveEntity(roleDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????", notes = "??????????????????")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(@RequestBody @ApiParam(name = "??????????????????", value = "????????????????????????", required = true) @Valid RoleUpdateRpo roleUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("roleUpdateRpo= [ {} ]", roleUpdateRpo);
		RoleUpdateDto roleUpdateDto = roleUpdateRpoToRoleUpdateDtoMapping.targetToSource(roleUpdateRpo);
		addUsername(roleUpdateDto,false);
		roleService.updateEntity(roleUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????", notes = "????????????(????????????)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(@RequestBody @ApiParam(name = "????????????", value = "??????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		roleService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "????????????", notes = "????????????(????????????)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(@RequestBody @ApiParam(name = "????????????", value = "??????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		roleService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????", notes = "????????????????????????")
	@PostMapping("v1/findOne")
	public ResponseEntity<ResultDataRro<RoleDto>> findOne(@RequestBody @ApiParam(name = "??????????????????", value = "????????????Id????????????????????????", required = true) @Valid ID id) {
		RoleDto roleDto = roleService.findById(id.getId());
		return makeSuccessResponseEntity(roleDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????", notes = "????????????????????????")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<RoleDto>>> findAll() {
		List<RoleDto> list = roleService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "???????????????????????????", notes = "?????????????????????????????????")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<RoleDto>>> findPage(@RequestBody @ApiParam(name = "????????????", value = "?????????????????????????????????", required = true) RoleSearchRpo roleSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(roleSearchRpo);
		Page<RoleDto> tenants = roleService.findPage(searchParams, roleSearchRpo.getPageNumber(),
				roleSearchRpo.getPageSize(), roleSearchRpo.getSortTypes());
		SmartPage<RoleDto> smartPage = new SmartPage<RoleDto>(tenants.getSize(), tenants.getTotalPages(), tenants.getTotalElements(),
				tenants.getNumber() + 1, tenants.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????", notes = "????????????????????????")
	@PostMapping("v1/configureUser")
	public ResponseEntity<ResultDataRro<Object>> roleConfigureUser(@RequestBody @ApiParam(name = "??????????????????", value = "??????????????????????????????", required = true) @Valid RoleUserAddRpo roleUserAddRpo) {
		LOGGER.debug("roleUserAddRpo= [ {} ]", roleUserAddRpo);
		RoleUserDto roleUserDto = new RoleUserDto();
		roleUserDto.setRoleId(roleUserAddRpo.getRoleId());
		roleUserDto.setUserIds(roleUserAddRpo.getUserIds());
		roleService.saveRoleUser(roleUserDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "??????????????????", notes = "????????????????????????")
	@PostMapping("v1/configureMenu")
	public ResponseEntity<ResultDataRro<Object>> roleConfigureMenu(@RequestBody @ApiParam(name = "??????????????????", value = "??????????????????????????????", required = true) @Valid RoleMenuAddRpo roleMenuAddRpo) {
		LOGGER.debug("roleMenuAddRpo= [ {} ]", roleMenuAddRpo);
		RoleMenuDto roleMenuDto = new RoleMenuDto();
		roleMenuDto.setRoleId(roleMenuAddRpo.getRoleId());
		roleMenuDto.setMenuIds(roleMenuAddRpo.getMenuIds());
		roleService.saveRoleMenu(roleMenuDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "????????????Id", notes = "????????????id????????????id")
	@PostMapping("v1/findMenuIds")
	public ResponseEntity<ResultDataRro<RoleMenuDto>> findMenuIdsByRoleId(@RequestBody @ApiParam(name = "??????Id", value = "??????Id", required = true) @Valid ID id) {
 		LOGGER.debug("id= {}", id);
		RoleMenuDto roleMenuDto = roleService.findRoleMenu(id.getId());
		return makeSuccessResponseEntity(roleMenuDto,HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "????????????Id", notes = "????????????id????????????id")
	@PostMapping("v1/findUserIds")
	public ResponseEntity<ResultDataRro<RoleUserDto>> findUserIdsByRoleId(@RequestBody @ApiParam(name = "??????Id", value = "??????Id", required = true) @Valid ID id) {
 		LOGGER.debug("id= {}", id);
		RoleUserDto roleUserDto = roleService.findRoleUser(id.getId());
		return makeSuccessResponseEntity(roleUserDto,HttpStatus.OK);
		
	}
}
