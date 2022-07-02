package com.beidouiot.alllink.community.user.center.dao.service.api;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.user.center.Role;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleMenuDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleUserDto;

/**
 * 
 *
 * @Description 角色管理业务逻辑接口
 * @author longww
 * @date 2021年4月11日
 */
public interface RoleService extends BaseService<RoleDto, RoleUpdateDto, Role, Long> {

	/**
	 * 保存角色配置用户信息
	 * @param roleUserDto
	 * @throws ServiceException
	 */
	void saveRoleUser(RoleUserDto roleUserDto) throws ServiceException;
	
	/**
	 * 保存角色配置菜单信息
	 * @param roleUserDto
	 * @throws ServiceException
	 */
	void saveRoleMenu(RoleMenuDto roleMenuDto) throws ServiceException;
	
	/**
	 * 根据角色Id查询角色菜单信息
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	RoleMenuDto findRoleMenu(Long roleId) throws ServiceException;
	
	/**
	 *  根据角色Id查询角色用户信息
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	RoleUserDto findRoleUser(Long roleId) throws ServiceException;
}
