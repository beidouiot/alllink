package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.beidouiot.alllink.community.common.data.entity.user.center.Menu;

/**
 * 
 *
 * @Description 菜单/功能数据库交互接口
 * @author longww
 * @date 2021年4月11日
 */
public interface MenuRepository extends PagingAndSortingRepository<Menu, Long>, JpaSpecificationExecutor<Menu> {

	List<Menu> findByAddrIsNotNull();
	
	List<Menu> findByDeleteFlag(Boolean deleteFlag);
	
	List<Menu> findByLevelAndDeleteFlag(Integer level, Boolean deleteFlag);
	
	List<Menu> findByDeleteFlagAndCodeStartingWith(String code, Boolean deleteFlag);
	
	List<Menu> findByPidAndDeleteFlag(Long pid, Boolean deleteFlag);
	
	List<Menu> findByPid(Long pid);
	
	List<Menu> findByCodeAndDeleteFlag(String code, Boolean deleteFlag);
	
	
	@Query("SELECT m FROM Menu m where m.code like CONCAT('%',:code,'%') and m.deleteFlag =:deleteFlag")
	List<Menu> findByCodeAndDeleteFlagLike(@Param("code") String code, @Param("deleteFlag") Boolean deleteFlag);
	
	
	
}
