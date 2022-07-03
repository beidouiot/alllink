package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.StandardEventModelParam;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/

public interface StandardEventModelParamRepository extends PagingAndSortingRepository<StandardEventModelParam, Long>,
		JpaSpecificationExecutor<StandardEventModelParam> {

	List<StandardEventModelParam> findByDeleteFlag(Boolean deleteFlag);
	
}
