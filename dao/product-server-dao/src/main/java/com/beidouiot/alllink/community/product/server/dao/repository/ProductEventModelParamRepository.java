package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.ProductEventModelParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/

public interface ProductEventModelParamRepository extends PagingAndSortingRepository<ProductEventModelParam, Long>,
		JpaSpecificationExecutor<ProductEventModelParam> {

	List<ProductEventModelParam> findByDeleteFlag(Boolean deleteFlag);
	List<ProductEventModelParam> findByProductIdAndStatusAndDeleteFlag(Long productId, Boolean status, Boolean deleteFlag);
	
}
