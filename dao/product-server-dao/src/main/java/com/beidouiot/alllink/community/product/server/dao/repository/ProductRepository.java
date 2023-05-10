package com.beidouiot.alllink.community.product.server.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.product.Product;

/**
*
* @Description 产品管理数据库交互接口
* @author longww
* @date 2021年12月24日
*/

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	List<Product> findByDeleteFlag(Boolean deleteFlag);
	
	Product findByNameAndDeleteFlag(String name, Boolean deleteFlag);
}
