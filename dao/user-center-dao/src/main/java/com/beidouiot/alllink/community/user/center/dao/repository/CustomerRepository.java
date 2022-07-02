package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.user.center.Customer;
/**
 * 
 *
 * @Description 客户管理数据库交互接口
 * @author longww
 * @date 2021年4月11日
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
	
	List<Customer> findByTenantId(Long tenantId);
	
	List<Customer> findByDeleteFlag(Boolean deleteFlag);

}
