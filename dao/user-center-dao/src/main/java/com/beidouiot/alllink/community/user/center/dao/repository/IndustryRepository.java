package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.user.center.Industry;

/**
 * 
 *
 * @Description 行业管理数据库交互接口
 * @author longww
 * @date 2021年4月11日
 */
public interface IndustryRepository extends PagingAndSortingRepository<Industry, Long>, JpaSpecificationExecutor<Industry> {

	Industry findIndustryByCodeAndDeleteFlag(String code, Boolean deleteFlag);
	
	List<Industry> findByDeleteFlag(Boolean deleteFlag);
}
