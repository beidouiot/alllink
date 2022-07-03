package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.ProductPropertyModel;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/

public interface ProductPropertyModelRepository
		extends PagingAndSortingRepository<ProductPropertyModel, Long>, JpaSpecificationExecutor<ProductPropertyModel> {

	List<ProductPropertyModel> findByDeleteFlag(Boolean deleteFlag);
	
	List<ProductPropertyModel> findByProductIdAndStatusAndDeleteFlag(Long productId, Boolean status, Boolean deleteFlag);
	
}
