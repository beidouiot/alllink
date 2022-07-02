package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.StandardEventModel;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/

public interface StandardEventModelRepository
		extends PagingAndSortingRepository<StandardEventModel, Long>, JpaSpecificationExecutor<StandardEventModel> {

	List<StandardEventModel> findByDeleteFlag(Boolean deleteFlag);
	
}
