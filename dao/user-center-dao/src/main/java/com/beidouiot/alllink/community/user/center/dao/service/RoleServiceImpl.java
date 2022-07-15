package com.beidouiot.alllink.community.user.center.dao.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.beidouiot.alllink.community.common.base.exception.CanNotDeleteDataException;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.CacheKeyConstants;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.user.center.Menu;
import com.beidouiot.alllink.community.common.data.entity.user.center.Role;
import com.beidouiot.alllink.community.common.data.entity.user.center.RoleMenu;
import com.beidouiot.alllink.community.common.data.entity.user.center.RoleMenuKey;
import com.beidouiot.alllink.community.common.data.entity.user.center.RoleUser;
import com.beidouiot.alllink.community.common.data.entity.user.center.RoleUserKey;
import com.beidouiot.alllink.community.common.data.mapping.user.center.role.RoleDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.role.RoleUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleMenuDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleUserDto;
import com.beidouiot.alllink.community.user.center.dao.repository.MenuRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.RoleMenuRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.RoleRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.RoleUserRepository;
import com.beidouiot.alllink.community.user.center.dao.service.api.RoleService;

/**
 * 角色管理业务逻辑实现
 * @author longww
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	
	@Autowired
	private RoleDtoMapping roleDtoMapping;
	
	@Autowired
	private RoleUpdateDtoMapping roleUpdateDtoMapping;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleUserRepository roleUserRepository;
	
	@Autowired
	private RoleMenuRepository roleMenuRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
	
	private Map<String, List<String>> resourceRolesMap;
    
	public void saveEntity(RoleDto roleDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("roleDto = [ {} ]", roleDto);
		}
		Role role = roleDtoMapping.targetToSource(roleDto);
		Map<String, Object> map = getHeaderUser();
		String strTenantId = map.get("tenantId").toString();
		Long tenantId = strTenantId == null || strTenantId.equals("") ? null : Long.valueOf(strTenantId);
		role.setTenantId(tenantId);
		String code = "";
		if( tenantId != null) {
			code = tenantId.toString()+"-"+RandomStringUtils.randomAlphanumeric(6);
		} else {
			code = "-"+RandomStringUtils.randomAlphanumeric(6);
		}
		role.setCode(code);
		roleRepository.save(role);
	}

	public void delete(Long id) throws ServiceException {
		Optional<Role> optional = roleRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}
		List<RoleUser> roleUsers = roleUserRepository.findRoleUsersByRoleId(id);
		if ( null != roleUsers && roleUsers.size() > 0 ) {
			throw new CanNotDeleteDataException("角色已使用，不能删除");
		}
		
		List<RoleMenu> roleMenus = roleMenuRepository.findRoleMenusByRoleId(id);
		if ( null != roleMenus && roleMenus.size() > 0 ) {
			throw new CanNotDeleteDataException("角色已使用，不能删除");
		}
		roleRepository.deleteById(id);

	}

	public void logicalDelete(Long id) throws ServiceException {
		Optional<Role> optional = roleRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}
		List<RoleUser> roleUsers = roleUserRepository.findRoleUsersByRoleId(id);
		if ( null != roleUsers && roleUsers.size() > 0 ) {
			throw new CanNotDeleteDataException("角色已使用，不能删除");
		}
		
		List<RoleMenu> roleMenus = roleMenuRepository.findRoleMenusByRoleId(id);
		if ( null != roleMenus && roleMenus.size() > 0 ) {
			throw new CanNotDeleteDataException("角色已使用，不能删除");
		}
		
		Role role = optional.get();
		Map<String, Object> map = getHeaderUser();
		role.setUpdatedBy(map.get("username").toString());
		role.setDeleteFlag(Constants.TRUE);
		roleRepository.save(role);

	}

	public void updateEntity(RoleUpdateDto roleUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("roleUpdateDto = [ {} ]", roleUpdateDto);
		}
		Optional<Role> optional = roleRepository.findById(roleUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		Role role = optional.get();
		role = roleUpdateDtoMapping.targetToSourceForUpdate(roleUpdateDto, role);
		roleRepository.save(role);

	}

	public RoleDto findById(Long id) throws ServiceException {
		return roleDtoMapping.sourceToTarget(roleRepository.findById(id).get());
	}

	public List<RoleDto> findAll() throws ServiceException {
		List<Role> list = roleRepository.findByDeleteFlag(Constants.FALSE);
		if( null == list || list.size() == 0) {
			return null;
		}
		
		return roleDtoMapping.sourceToTarget(list);
	}

	public Page<RoleDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize, List<SortRpo> sortTypes)
			throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, roleRepository, roleDtoMapping);
	}

	@Override
	public void saveRoleUser(RoleUserDto roleUserDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("roleUserDto = [ {} ]", roleUserDto);
		}
		List<Long> list = roleUserDto.getUserIds();
		List<RoleUser> roleUserList = roleUserRepository.findRoleUsersByRoleId(roleUserDto.getRoleId());
		
		if(list != null && list.size() > 0) {
			RoleUser roleUsers[] = new RoleUser[list.size()];
			for(int i = 0; i < list.size(); i++) {
				Long userId = list.get(i);
				RoleUserKey roleUserKey = new RoleUserKey();
				roleUserKey.setRoleId(roleUserDto.getRoleId());
				roleUserKey.setUserId(userId);
				RoleUser roleUser = new RoleUser();
				roleUser.setId(roleUserKey);
				roleUsers[i] = roleUser;
				if( roleUserList != null && roleUserList.size() >0) {
					roleUserList.remove(roleUser);
				}
			}
			roleUserRepository.saveAll( Arrays.asList(roleUsers));
			if( roleUserList != null && roleUserList.size() >0) {
				RoleUser deleteRoleUsers[] = new RoleUser[roleUserList.size()];
				for( int i = 0; i < roleUserList.size(); i++ ){
					deleteRoleUsers[i] = roleUserList.get(i);
				}
				roleUserRepository.deleteAll(Arrays.asList(deleteRoleUsers));
			}
		} else {
			if( roleUserList != null && roleUserList.size() >0) {
				RoleUser deleteRoleUsers[] = new RoleUser[roleUserList.size()];
				for( int i = 0; i < roleUserList.size(); i++ ){
					deleteRoleUsers[i] = roleUserList.get(i);
				}
				roleUserRepository.deleteAll(Arrays.asList(deleteRoleUsers));
			}
		}
	}

	@Override
	public void saveRoleMenu(RoleMenuDto roleMenuDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("roleUserDto = [ {} ]", roleMenuDto);
		}
		List<Long> list = roleMenuDto.getMenuIds();
		List<RoleMenu> roleMenuList = roleMenuRepository.findRoleMenusByRoleId(roleMenuDto.getRoleId());
		
		if(list != null && list.size() > 0) {
			RoleMenu roleMenus[] = new RoleMenu[list.size()];
			for(int i = 0; i < list.size(); i++) {
				Long menuId = list.get(i);
				RoleMenuKey roleMenuKey = new RoleMenuKey();
				roleMenuKey.setRoleId(roleMenuDto.getRoleId());
				roleMenuKey.setMenuId(menuId);
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setId(roleMenuKey);
				roleMenus[i] = roleMenu;
				if( roleMenuList != null && roleMenuList.size() >0) {
					roleMenuList.remove(roleMenu);
				}
			}
			roleMenuRepository.saveAll( Arrays.asList(roleMenus));
			if( roleMenuList != null && roleMenuList.size() >0) {
				RoleMenu deleteRoleMenus[] = new RoleMenu[roleMenuList.size()];
				for( int i = 0; i < roleMenuList.size(); i++ ){
					deleteRoleMenus[i] = roleMenuList.get(i);
				}
				roleMenuRepository.deleteAll(Arrays.asList(deleteRoleMenus));
			}
		} else {
			if( roleMenuList != null && roleMenuList.size() >0) {
				RoleMenu deleteRoleMenus[] = new RoleMenu[roleMenuList.size()];
				for( int i = 0; i < roleMenuList.size(); i++ ){
					deleteRoleMenus[i] = roleMenuList.get(i);
				}
				roleMenuRepository.deleteAll(Arrays.asList(deleteRoleMenus));
			}
		}
		refreshRedis();
	}
	
	private void refreshRedis() {
		List<Menu> menuList = menuRepository.findByAddrIsNotNull();
		resourceRolesMap = new TreeMap<>();
		for (Menu menu : menuList) {
			List<String> roleCodeList = new ArrayList<String>();
			if(menu.getAddr().equals("")) {
				continue;
			}
			List<RoleMenu> roleMenuList = roleMenuRepository.findRoleMenusByMenuId(menu.getId());
			for (RoleMenu roleMenu : roleMenuList) {
				Role role = roleRepository.findById(roleMenu.getId().getRoleId()).get();
				roleCodeList.add(role.getCode());
			}
			resourceRolesMap.put(menu.getAddr(), roleCodeList);
		}
//		for (Iterator<Menu> iterator = menuList.iterator(); iterator.hasNext();) {
//			Menu menu = (Menu) iterator.next();
//			List<String> roleCodeList = new ArrayList<String>();
//			List<RoleMenu> roleMenuList = roleMenuRepository.findRoleMenusByMenuId(menu.getId());
//			for (Iterator<RoleMenu> iterator2 = roleMenuList.iterator(); iterator2.hasNext();) {
//				RoleMenu roleMenu = (RoleMenu) iterator2.next();
//				Role role = roleRepository.findById(roleMenu.getId().getRoleId()).get();
//				roleCodeList.add(role.getCode());
//			}
//			resourceRolesMap.put(menu.getAddr(), roleCodeList);
//		}
		redisTemplate.delete(CacheKeyConstants.RESOURCE_ROLES_MAP);
		redisTemplate.opsForHash().putAll(CacheKeyConstants.RESOURCE_ROLES_MAP, resourceRolesMap);
	}

	@Override
	public RoleMenuDto findRoleMenu(Long roleId) throws ServiceException {
		List<RoleMenu> roleMenuList = roleMenuRepository.findRoleMenusByRoleId(roleId);
		
		List<Long> menuIds = new ArrayList<Long>();
		if( null == roleMenuList || roleMenuList.size() == 0) {
			return null;
		}
		for (RoleMenu roleMenu : roleMenuList) {
			menuIds.add(roleMenu.getId().getMenuId());
		}
		RoleMenuDto roleMenuDto = new RoleMenuDto();
		roleMenuDto.setRoleId(roleId);
		roleMenuDto.setMenuIds(menuIds);
		return roleMenuDto;
	}
	
	@Override
	public RoleUserDto findRoleUser(Long roleId) throws ServiceException {
		List<RoleUser> roleUserList = roleUserRepository.findRoleUsersByRoleId(roleId);
		
		List<Long> userIds = new ArrayList<Long>();
		if( null == roleUserList || roleUserList.size() == 0) {
			return null;
		}
		for (RoleUser roleUser : roleUserList) {
			userIds.add(roleUser.getId().getUserId());
		}
		RoleUserDto roleUserDto = new RoleUserDto();
		roleUserDto.setRoleId(roleId);
		roleUserDto.setUserIds(userIds);
		return roleUserDto;
	}

}
