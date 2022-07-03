package com.beidouiot.alllink.community.user.center.dao.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beidouiot.alllink.community.common.base.exception.CanNotDeleteDataException;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.CacheKeyConstants;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.user.center.Menu;
import com.beidouiot.alllink.community.common.data.entity.user.center.RoleMenu;
import com.beidouiot.alllink.community.common.data.mapping.user.center.menu.MenuDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.menu.MenuUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.menu.MenutoMenuTreeRroMapping;
import com.beidouiot.alllink.community.common.data.xxo.dto.TreeNode;
import com.beidouiot.alllink.community.common.data.xxo.dto.TreeUtils;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.MenuDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.MenuUpdateDto;
import com.beidouiot.alllink.community.user.center.dao.repository.MenuRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.RoleMenuRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.RoleUserRepository;
import com.beidouiot.alllink.community.user.center.dao.service.api.MenuService;

/**
 * 菜单/功能管理业务逻辑实现
 * @author longww
 *
 */
@Service
public class MenuServiceImpl implements MenuService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuDtoMapping menuDtoMapping;
	
	@Autowired
	private MenuUpdateDtoMapping menuUpdateDtoMapping;
	
	@Autowired
	private MenutoMenuTreeRroMapping menutoMenuTreeRroMapping;
	
	@Autowired
	private RoleMenuRepository roleMenuRepository;
	
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@Autowired
    private RedisTemplate<String,Object> redisTemplate;
		
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveEntity(MenuDto menuDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("menuDto = [ {} ]", menuDto);
		}
		if (null == menuDto.getPid()) {
			menuDto.setPid(0L);
		}
		Menu menu = menuDtoMapping.targetToSource(menuDto);
		String code = "";
		if ( menu.getPid() == null || menu.getPid() == 0 ) {
			menu.setPid(0L);
			List<Menu> menus = menuRepository.findByPid(0L);
			if(null == menus || menus.size() == 0) {
				code = "0";
			} else {
				code = String.valueOf(menus.size());
			}
			menu.setLevel(0);
//			code = RandomStringUtils.randomAlphanumeric(6);
		} else {
			Optional<Menu> pmenuOpt = menuRepository.findById(menu.getPid());
			Menu pmenu = pmenuOpt.get();
			List<Menu> menus = menuRepository.findByPid(menu.getPid());
			if(null == menus || menus.size() == 0) {
				code = pmenu.getCode() + "-1";
			} else {
				code = pmenu.getCode() + "-" + String.valueOf(menus.size()+1);
			}
			int lev = StringUtils.countMatches(code, '-');
			menu.setLevel(lev);
//			code = pmenu.getCode()+"-"+RandomStringUtils.randomAlphanumeric(6);
		}
		menu.setCode(code);
		menu.setLeafFlag(Constants.TRUE);
		menuRepository.save(menu);
		if(null != menu.getPid() && menu.getPid() != 0 && menu.getType().equals("MENU")) {
			Menu pMenu = menuRepository.findById(menu.getPid()).get();
			pMenu.setLeafFlag(Constants.FALSE);
			Map<String, Object> map = getHeaderUser();
			pMenu.setUpdatedBy(map.get("username").toString());
			menuRepository.save(pMenu);
		}
		
		if(menu.getType().equals("BUTTON") || menu.getType().equals("TAB")) {
			Menu pMenu = menuRepository.findById(menu.getPid()).get();
			Map<String, Object> map = getHeaderUser();
			pMenu.setUpdatedBy(map.get("username").toString());
			pMenu.setHasButton(Constants.TRUE);
			menuRepository.save(pMenu);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		List<RoleMenu> users = roleMenuRepository.findRoleMenusByMenuId(id);
		if ( null != users && users.size() > 0 ) {
			throw new CanNotDeleteDataException("菜单已使用，不能删除");
		}
		Menu menu = menuRepository.findById(id).get();
		if( null == menu) {
			return;
		}
		String addr = menu.getAddr();
		List<Menu> list = menuRepository.findByPidAndDeleteFlag(menu.getPid(), Constants.FALSE);
		if ( null == list || list.size() == 0 ) {
			Menu pMenu = menuRepository.findById(menu.getPid()).get();
			pMenu.setLeafFlag(Constants.TRUE);
			Map<String, Object> map = getHeaderUser();
			pMenu.setUpdatedBy(map.get("username").toString());
			menuRepository.save(pMenu);
		}
		menuRepository.deleteById(id);
		if(StringUtils.isNotBlank(addr)) {
			redisTemplate.opsForHash().delete(CacheKeyConstants.RESOURCE_ROLES_MAP, addr);
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<Menu> optional = menuRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}
		List<RoleMenu> users = roleMenuRepository.findRoleMenusByMenuId(id);
		if ( null != users && users.size() > 0 ) {
			throw new CanNotDeleteDataException("菜单已使用，不能删除");
		}

		Menu menu = optional.get();
		Map<String, Object> map = getHeaderUser();
		menu.setUpdatedBy(map.get("username").toString());
		menu.setDeleteFlag(Constants.TRUE);
		menuRepository.save(menu);
		String addr = menu.getAddr();
		List<Menu> list = menuRepository.findByPidAndDeleteFlag(menu.getPid(), Constants.FALSE);
		if ( null == list || list.size() == 0 ) {
			Menu pMenu = menuRepository.findById(menu.getPid()).get();
			pMenu.setLeafFlag(Constants.TRUE);
			pMenu.setUpdatedBy(map.get("username").toString());
			menuRepository.save(pMenu);
		}
		
		if(StringUtils.isNotBlank(addr)) {
			redisTemplate.opsForHash().delete(CacheKeyConstants.RESOURCE_ROLES_MAP, addr);
		}

	}

	@Override
	public void updateEntity(MenuUpdateDto menuUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("menuUpdateDto = [ {} ]", menuUpdateDto);
		}
		Optional<Menu> optional = menuRepository.findById(menuUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		Menu menu = optional.get();
		String addr = menu.getAddr();
		menu = menuUpdateDtoMapping.targetToSourceForUpdate(menuUpdateDto,menu);//.targetToSource(menuUpdateDto);
		menuRepository.save(menu);
		
		if(StringUtils.isNotBlank(addr) && !StringUtils.equals(addr, menuUpdateDto.getAddr())) {
			Object obj = redisTemplate.opsForHash().get(CacheKeyConstants.RESOURCE_ROLES_MAP, addr);
//			Set<Object> sets = redisTemplate.opsForHash().keys(CacheKeyConstants.RESOURCE_ROLES_MAP);
//			Map<String, List<String>> resourceRolesMap= new TreeMap<>();
//			for (Object object : sets) {
//				if(object.toString().equals(addr)) {
//					
//				}
//			}
			redisTemplate.opsForHash().delete(CacheKeyConstants.RESOURCE_ROLES_MAP, addr);
			redisTemplate.opsForHash().put(CacheKeyConstants.RESOURCE_ROLES_MAP, menuUpdateDto.getAddr(), obj);
		}
	}

	@Override
	public MenuDto findById(Long id) throws ServiceException {
		return menuDtoMapping.sourceToTarget(menuRepository.findById(id).get());
	}

	@Override
	public List<MenuDto> findAll() throws ServiceException {
		List<Menu> list = menuRepository.findByDeleteFlag(Constants.FALSE);
		if ( null == list || list.size() == 0) {
			return null;
		}
		return menuDtoMapping.sourceToTarget(list);
	}
	
	@Override
	public  List<? extends TreeNode> findAllMenuTree() throws ServiceException {
		List<Menu> list = menuRepository.findByDeleteFlag(Constants.FALSE);
		
		return TreeUtils.build(menutoMenuTreeRroMapping.sourceToTarget(list), TreeNode.ASC);
	}
	
	@Override
	public List<? extends TreeNode> findUserAllMenuTree() throws ServiceException {
		Map<String, Object> map = getHeaderUser();
		String id = map.get("id").toString();
		List<Long> roleIds = roleUserRepository.findRoleIdsByUserId(Long.parseLong(id));

		List<Long> menuIds = roleMenuRepository.findMenuIdsByRoleIds(roleIds);
		List<Menu> menus = (List<Menu>) menuRepository.findAllById(menuIds);
		
		return TreeUtils.build(menutoMenuTreeRroMapping.sourceToTarget(menus), TreeNode.ASC);
	}

	@Override
	public Page<MenuDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize, List<SortRpo> sortTypes)
			throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, menuRepository, menuDtoMapping);
	}

	@Override
	public List<MenuDto> findByCode(String code) throws ServiceException {
		List<Menu> list = menuRepository.findByCodeAndDeleteFlag(code, Constants.FALSE);
		if ( null == list || list.size() == 0 ) {
			return null;
		}
		return menuDtoMapping.sourceToTarget(list);
	}

	@Override
	public List<MenuDto> findByParentIdAndLevel(Long parentId, Integer level) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuDto> findByCodeLike(String code) throws ServiceException {
		List<Menu> list = menuRepository.findByCodeAndDeleteFlagLike(code, Constants.FALSE);
		if ( null == list || list.size() == 0 ) {
			return null;
		}

		return menuDtoMapping.sourceToTarget(list);
	}

	@Override
	public List<MenuDto> findByCodeStartWith(String code) throws ServiceException {
		List<Menu> list = menuRepository.findByDeleteFlagAndCodeStartingWith(code, Constants.FALSE);

		if ( null == list || list.size() == 0 ) {
			return null;
		}

		return menuDtoMapping.sourceToTarget(list);
	}

}
