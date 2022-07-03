package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.ProductPropertyModelParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/

public interface ProductPropertyModelParamRepository
		extends PagingAndSortingRepository<ProductPropertyModelParam, Long>,
		JpaSpecificationExecutor<ProductPropertyModelParam> {

	List<ProductPropertyModelParam> findByDeleteFlag(Boolean deleteFlag);
	List<ProductPropertyModelParam> findByProductIdAndStatusAndDeleteFlag(Long productId, Boolean status, Boolean deleteFlag);
	
}
