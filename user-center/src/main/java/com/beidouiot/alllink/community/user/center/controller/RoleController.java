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
 * @Description 角色信息管理
 * @author longww
 * @date 2021年4月11日
 */
@Api(tags = "角色信息管理")
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
	
	@ApiOperation(value = "角色信息添加", notes = "角色信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(@RequestBody @ApiParam(name = "新增角色信息", value = "角色信息请求参数", required = true) @Valid RoleAddRpo roleAddRpo) {
		LOGGER.debug("roleAddRpo= [ {} ]", roleAddRpo);
		RoleDto roleDto = roleRpoDtoMapping.targetToSource(roleAddRpo);
		addUsernameAndDate(roleDto,true);
		roleService.saveEntity(roleDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "角色修改", notes = "角色信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(@RequestBody @ApiParam(name = "修改角色信息", value = "角色信息请求参数", required = true) @Valid RoleUpdateRpo roleUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("roleUpdateRpo= [ {} ]", roleUpdateRpo);
		RoleUpdateDto roleUpdateDto = roleUpdateRpoToRoleUpdateDtoMapping.targetToSource(roleUpdateRpo);
		addUsername(roleUpdateDto,false);
		roleService.updateEntity(roleUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除角色", notes = "删除角色(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(@RequestBody @ApiParam(name = "删除角色", value = "删除角色信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		roleService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "删除角色", notes = "删除角色(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(@RequestBody @ApiParam(name = "删除角色", value = "删除角色信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		roleService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有角色", notes = "查询所有角色信息")
	@PostMapping("v1/findOne")
	public ResponseEntity<ResultDataRro<RoleDto>> findOne(@RequestBody @ApiParam(name = "查询一个角色", value = "根据角色Id查询角色信息请求", required = true) @Valid ID id) {
		RoleDto roleDto = roleService.findById(id.getId());
		return makeSuccessResponseEntity(roleDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有角色", notes = "查询所有角色信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<RoleDto>>> findAll() {
		List<RoleDto> list = roleService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "按条件分页查询角色", notes = "按条件分页查询角色信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<RoleDto>>> findPage(@RequestBody @ApiParam(name = "查询角色", value = "按条件角色信息请求参数", required = true) RoleSearchRpo roleSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(roleSearchRpo);
		Page<RoleDto> tenants = roleService.findPage(searchParams, roleSearchRpo.getPageNumber(),
				roleSearchRpo.getPageSize(), roleSearchRpo.getSortTypes());
		SmartPage<RoleDto> smartPage = new SmartPage<RoleDto>(tenants.getSize(), tenants.getTotalPages(), tenants.getTotalElements(),
				tenants.getNumber() + 1, tenants.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
	@ApiOperation(value = "角色配置用户", notes = "角色配置用户信息")
	@PostMapping("v1/configureUser")
	public ResponseEntity<ResultDataRro<Object>> roleConfigureUser(@RequestBody @ApiParam(name = "角色配置用户", value = "角色配置用户请求参数", required = true) @Valid RoleUserAddRpo roleUserAddRpo) {
		LOGGER.debug("roleUserAddRpo= [ {} ]", roleUserAddRpo);
		RoleUserDto roleUserDto = new RoleUserDto();
		roleUserDto.setRoleId(roleUserAddRpo.getRoleId());
		roleUserDto.setUserIds(roleUserAddRpo.getUserIds());
		roleService.saveRoleUser(roleUserDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "角色配置菜单", notes = "角色配置菜单信息")
	@PostMapping("v1/configureMenu")
	public ResponseEntity<ResultDataRro<Object>> roleConfigureMenu(@RequestBody @ApiParam(name = "角色配置菜单", value = "角色配置菜单请求参数", required = true) @Valid RoleMenuAddRpo roleMenuAddRpo) {
		LOGGER.debug("roleMenuAddRpo= [ {} ]", roleMenuAddRpo);
		RoleMenuDto roleMenuDto = new RoleMenuDto();
		roleMenuDto.setRoleId(roleMenuAddRpo.getRoleId());
		roleMenuDto.setMenuIds(roleMenuAddRpo.getMenuIds());
		roleService.saveRoleMenu(roleMenuDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "查询菜单Id", notes = "根据角色id查询菜单id")
	@PostMapping("v1/findMenuIds")
	public ResponseEntity<ResultDataRro<RoleMenuDto>> findMenuIdsByRoleId(@RequestBody @ApiParam(name = "角色Id", value = "角色Id", required = true) @Valid ID id) {
 		LOGGER.debug("id= {}", id);
		RoleMenuDto roleMenuDto = roleService.findRoleMenu(id.getId());
		return makeSuccessResponseEntity(roleMenuDto,HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "查询用户Id", notes = "根据角色id查询用户id")
	@PostMapping("v1/findUserIds")
	public ResponseEntity<ResultDataRro<RoleUserDto>> findUserIdsByRoleId(@RequestBody @ApiParam(name = "角色Id", value = "角色Id", required = true) @Valid ID id) {
 		LOGGER.debug("id= {}", id);
		RoleUserDto roleUserDto = roleService.findRoleUser(id.getId());
		return makeSuccessResponseEntity(roleUserDto,HttpStatus.OK);
		
	}
}
