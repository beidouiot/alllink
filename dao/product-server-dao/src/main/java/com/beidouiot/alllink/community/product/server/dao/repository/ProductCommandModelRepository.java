package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.ProductCommandModel;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/

public interface ProductCommandModelRepository
		extends PagingAndSortingRepository<ProductCommandModel, Long>, JpaSpecificationExecutor<ProductCommandModel> {

	List<ProductCommandModel> findByDeleteFlag(Boolean deleteFlag);
	List<ProductCommandModel> findByProductIdAndStatusAndDeleteFlag(Long productId, Boolean status, Boolean deleteFlag);
	
	List<ProductCommandModel> findByProductIdAndDeleteFlag(Long productId, Boolean deleteFlag);
	
}
