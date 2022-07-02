package com.beidouiot.alllink.community.user.center.dao.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.user.center.Department;

/**
*
* @Description 部门组织结构管理数据库交互接口
* @author longww
* @date 2021年5月9日
*/

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long>, JpaSpecificationExecutor<Department> {

}
