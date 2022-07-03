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
import com.beidouiot.alllink.community.common.data.mapping.user.center.menu.MenuRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.menu.MenuUpdateRpoToMenuUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.TreeNode;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.MenuDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.MenuUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.ID;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.menu.MenuAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.menu.MenuSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.menu.MenuUpdateRpo;
import com.beidouiot.alllink.community.user.center.dao.service.api.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 *
 * @Description 菜单/功能信息管理
 * @author longww
 * @date 2021年4月11日
 */
@Api(tags = "菜单/功能信息管理")
@RestController
@RequestMapping(value = ServiceConstants.MENU_URI_BASE, produces = "application/json; charset=UTF-8")
public class MenuController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MenuRpoDtoMapping menuRpoDtoMapping;
	
	@Autowired
	private MenuUpdateRpoToMenuUpdateDtoMapping menuUpdateRpoToMenuUpdateDtoMapping;
	
	
	@ApiOperation(value = "菜单/功能信息添加", notes = "菜单/功能信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(@RequestBody @ApiParam(name = "新增角色信息", value = "角色信息请求参数", required = true) @Valid MenuAddRpo menuAddRpo) {
		LOGGER.debug("menuAddRpo= [ {} ]", menuAddRpo);
		MenuDto menuDto = menuRpoDtoMapping.targetToSource(menuAddRpo);
		addUsernameAndDate(menuDto,true);
		menuService.saveEntity(menuDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "菜单/功能修改", notes = "菜单/功能信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(@RequestBody @ApiParam(name = "修改菜单/功能信息", value = "菜单/功能信息请求参数", required = true) @Valid MenuUpdateRpo menuUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("menuUpdateRpo= [ {} ]", menuUpdateRpo);
		MenuUpdateDto menuUpdateDto = menuUpdateRpoToMenuUpdateDtoMapping.targetToSource(menuUpdateRpo);
		addUsername(menuUpdateDto,false);
 		menuService.updateEntity(menuUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除菜单/功能", notes = "删除菜单/功能(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(@RequestBody @ApiParam(name = "删除菜单/功能", value = "删除菜单/功能信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		menuService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "删除菜单/功能", notes = "删除菜单/功能(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(@RequestBody @ApiParam(name = "删除菜单/功能", value = "删除菜单/功能信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		menuService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有菜单/功能", notes = "查询所有菜单/功能信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<MenuDto>>> findAll() {
		List<MenuDto> list = menuService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有菜单/功能树", notes = "查询所有菜单/功能信息树")
	@GetMapping("v1/findAllTree")
	public ResponseEntity<?> findAllMenuTree() {
		List<? extends TreeNode> list = menuService.findAllMenuTree();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询用户所有菜单/功能树", notes = "查询用户所有菜单/功能信息树")
	@GetMapping("v1/findUserAllTree")
	public ResponseEntity<?> findUserAllMenuTree() {
		List<? extends TreeNode> list = menuService.findUserAllMenuTree();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "按条件分页查询菜单/功能", notes = "按条件分页查询菜单/功能信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<MenuDto>>> findPage(@RequestBody @ApiParam(name = "查询菜单/功能", value = "按条件菜单/功能信息请求参数", required = true) MenuSearchRpo menuSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(menuSearchRpo);
		Page<MenuDto> parks = menuService.findPage(searchParams, menuSearchRpo.getPageNumber(),
				menuSearchRpo.getPageSize(), menuSearchRpo.getSortTypes());
		SmartPage<MenuDto> smartPage = new SmartPage<MenuDto>(parks.getSize(), parks.getTotalPages(), parks.getTotalElements(),
				parks.getNumber() + 1, parks.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
}
