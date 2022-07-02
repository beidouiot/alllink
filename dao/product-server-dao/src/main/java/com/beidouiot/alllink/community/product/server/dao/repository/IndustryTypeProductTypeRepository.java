package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.beidouiot.alllink.community.common.data.entity.product.IndustryTypeProductType;
import com.beidouiot.alllink.community.common.data.entity.product.IndustryTypeProductTypeKey;

/**
*
* @Description  行业类别与产品类别数据库交互接口
* @author longww
* @date 2022年1月4日
*/

public interface IndustryTypeProductTypeRepository extends PagingAndSortingRepository<IndustryTypeProductType, IndustryTypeProductTypeKey>, JpaSpecificationExecutor<IndustryTypeProductType> {

	@Query(value = "FROM IndustryTypeProductType WHERE id.industryTypeId =:industryTypeId")
	List<IndustryTypeProductType> findIndustryTypeProductTypesByIndustryTypeId(@Param("industryTypeId") Long industryTypeId);
	
	@Query(value = "FROM IndustryTypeProductType WHERE id.productTypeId =:productTypeId")
	List<IndustryTypeProductType> findIndustryTypeProductTypesByProductTypeId(@Param("productTypeId") Long productTypeId);
}
