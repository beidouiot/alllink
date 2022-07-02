package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.beidouiot.alllink.community.common.data.entity.user.center.RoleMenu;
import com.beidouiot.alllink.community.common.data.entity.user.center.RoleMenuKey;

/**
 * 
 *
 * @Description 角色配置菜单数据库交互接口
 * @author longww
 * @date 2021年4月11日
 */
public interface RoleMenuRepository  extends PagingAndSortingRepository<RoleMenu, RoleMenuKey>, JpaSpecificationExecutor<RoleMenu> {
	
	@Query(value = "FROM RoleMenu WHERE id.roleId =:roleId")
	List<RoleMenu> findRoleMenusByRoleId(@Param("roleId") Long roleId);
	
	@Query(value = "FROM RoleMenu WHERE id.menuId =:menuId")
	List<RoleMenu> findRoleMenusByMenuId(@Param("menuId") Long menuId);
	
	@Query(value="SELECT distinct(rm.id.menuId) FROM  RoleMenu rm where rm.id.roleId in(:roleIds)")
	List<Long> findMenuIdsByRoleIds(@Param("roleIds") List<Long> roleIds);

}
