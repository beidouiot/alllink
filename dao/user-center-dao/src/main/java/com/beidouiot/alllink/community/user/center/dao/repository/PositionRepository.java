package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.user.center.Position;

/**
*
* @Description 职位管理数据库交互接口
* @author longww
* @date 2021年5月9日
*/

public interface PositionRepository
		extends PagingAndSortingRepository<Position, Long>, JpaSpecificationExecutor<Position> {
	List<Position> findByDeleteFlag(Boolean deleteFlag);
}
