package com.beidouiot.alllink.community.user.center.dao.service.api;

import java.util.List;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.user.center.Menu;
import com.beidouiot.alllink.community.common.data.xxo.dto.TreeNode;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.MenuDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.MenuUpdateDto;

/**
 * 
 *
 * @Description 菜单/功能管理业务逻辑接口
 * @author longww
 * @date 2021年4月11日
 */
public interface MenuService extends BaseService<MenuDto, MenuUpdateDto, Menu, Long> {

	/**
	 * 根据编码查询菜单
	 * @param code 编号
	 * @return
	 * @throws ServiceException
	 */
	List<MenuDto> findByCode(String code) throws ServiceException;
	
	/**
	 * 根据编号模糊查询菜单
	 * @param code 编号
	 * @return
	 * @throws ServiceException
	 */
	List<MenuDto> findByCodeLike(String code) throws ServiceException;
	
	/**
	 * 查询以编号开头的菜单
	 * @param code 编号
	 * @return
	 * @throws ServiceException
	 */
	List<MenuDto> findByCodeStartWith(String code) throws ServiceException;
	
	
	/**
	 * 根据层级查询菜单
	 * @param parentId 所在层级
	 * @param level 所在层级
	 * @return
	 * @throws ServiceException
	 */
	List<MenuDto> findByParentIdAndLevel(Long parentId, Integer level) throws ServiceException;
	
	/**
	 * 查询 所有菜单树
	 * @return
	 * @throws ServiceException
	 */
	List<? extends TreeNode> findAllMenuTree() throws ServiceException;
	
	/**
	 * 查询用户所有菜单树
	 * @return
	 * @throws ServiceException
	 */
	List<? extends TreeNode> findUserAllMenuTree() throws ServiceException;
}
