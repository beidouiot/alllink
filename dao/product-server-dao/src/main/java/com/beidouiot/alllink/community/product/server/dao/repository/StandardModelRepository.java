package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.StandardModel;

/**
*
* @Description 标准物模型数据库交互接口
* @author longww
* @date 2021年12月24日
*/

public interface StandardModelRepository
		extends PagingAndSortingRepository<StandardModel, Long>, JpaSpecificationExecutor<StandardModel> {

	List<StandardModel> findByDeleteFlag(Boolean deleteFlag);
}
