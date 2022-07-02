package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.ProductModelVersion;

/**
*
* @Description 产品管理物模型历史版本数据库交互接口
* @author longww
* @date 2021年12月24日
*/

public interface ProductModelVersionRepository
		extends PagingAndSortingRepository<ProductModelVersion, Long>, JpaSpecificationExecutor<ProductModelVersion> {

	List<ProductModelVersion> findByDeleteFlag(Boolean deleteFlag);
	List<ProductModelVersion> findByProductIdAndUserFlagAndDeleteFlag(Long productId, Boolean userFlag, Boolean deleteFlag);
	
}
