package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.user.center.Tenant;

/**
 * 
 *
 * @Description 租户管理数据库交互接口
 * @author longww
 * @date 2021年4月11日
 */
public interface TenantRepository extends PagingAndSortingRepository<Tenant, Long>, JpaSpecificationExecutor<Tenant> {

	List<Tenant> findByIndustryId(Long industryId);
	
	List<Tenant> findByDeleteFlag(Boolean deleteFlag);
}
