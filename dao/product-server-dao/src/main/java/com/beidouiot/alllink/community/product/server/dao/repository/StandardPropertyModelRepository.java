package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.StandardPropertyModel;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/

public interface StandardPropertyModelRepository extends PagingAndSortingRepository<StandardPropertyModel, Long>,
		JpaSpecificationExecutor<StandardPropertyModel> {
	
	List<StandardPropertyModel> findByDeleteFlag(Boolean deleteFlag);
	
	List<StandardPropertyModel> findByProductTypeIdAndDeleteFlag(Long productTypeId,Boolean deleteFlag);
}
