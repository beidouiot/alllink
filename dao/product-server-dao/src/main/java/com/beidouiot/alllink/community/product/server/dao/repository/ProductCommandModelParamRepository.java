package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.ProductCommandModelParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/

public interface ProductCommandModelParamRepository extends PagingAndSortingRepository<ProductCommandModelParam, Long>,
		JpaSpecificationExecutor<ProductCommandModelParam> {

	List<ProductCommandModelParam> findByDeleteFlag(Boolean deleteFlag);
	List<ProductCommandModelParam> findByProductIdAndStatusAndDeleteFlag(Long productId, Boolean status, Boolean deleteFlag);
	
}
