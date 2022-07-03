package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.ProductEventModel;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/

public interface ProductEventModelRepository
		extends PagingAndSortingRepository<ProductEventModel, Long>, JpaSpecificationExecutor<ProductEventModel> {

	List<ProductEventModel> findByDeleteFlag(Boolean deleteFlag);
	List<ProductEventModel> findByProductIdAndStatusAndDeleteFlag(Long productId, Boolean status, Boolean deleteFlag);
	
}
