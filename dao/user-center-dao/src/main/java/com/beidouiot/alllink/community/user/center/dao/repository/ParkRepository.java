package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.user.center.Park;

/**
 * 
 *
 * @Description 园区/小区管理数据库交互接口
 * @author longww
 * @date 2021年4月11日
 */
public interface ParkRepository extends PagingAndSortingRepository<Park, Long>, JpaSpecificationExecutor<Park> {
	List<Park> findByDeleteFlag(Boolean deleteFlag);
}
