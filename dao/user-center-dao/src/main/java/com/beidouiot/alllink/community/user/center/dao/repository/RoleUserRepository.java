package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.beidouiot.alllink.community.common.data.entity.user.center.RoleUser;
import com.beidouiot.alllink.community.common.data.entity.user.center.RoleUserKey;

/**
 * 
 *
 * @Description 角色配置用户数据库交互接口
 * @author longww
 * @date 2021年4月11日
 */
public interface RoleUserRepository  extends PagingAndSortingRepository<RoleUser, RoleUserKey>, JpaSpecificationExecutor<RoleUser> {
	@Query(value = "FROM RoleUser WHERE id.roleId =:roleId")
	List<RoleUser> findRoleUsersByRoleId(@Param("roleId") Long roleId);
	
	@Query(value = "FROM RoleUser ru WHERE ru.id.userId =:userId")
	List<RoleUser> findRoleUsersByUserId(@Param("userId") Long userId);
	
	@Query(value = "SELECT ru.id.roleId FROM RoleUser ru WHERE ru.id.userId =:userId")
	List<Long> findRoleIdsByUserId(@Param("userId") Long userId);
	
}
