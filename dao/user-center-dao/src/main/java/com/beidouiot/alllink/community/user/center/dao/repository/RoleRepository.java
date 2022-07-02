package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.user.center.Role;

/**
 * 
 *
 * @Description 角色管理数据库交互接口
 * @author longww
 * @date 2021年4月11日
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, JpaSpecificationExecutor<Role> {

	List<Role> findByDeleteFlag(Boolean deleteFlag);
}
