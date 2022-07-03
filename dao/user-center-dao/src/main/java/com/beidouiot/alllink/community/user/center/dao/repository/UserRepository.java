package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.beidouiot.alllink.community.common.data.entity.user.center.User;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserDto;

/**
 * 
 *
 * @Description  用户管理数据库交互接口
 * @author longww
 * @date 2021年4月11日
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	User findUserByUsernameAndStatusAndDeleteFlag(String username,Boolean status, Boolean deleteFlag);
	
	User findUserByUsername(String usernameg);
	
	User findUserByUsernameAndDeleteFlag(String username, Boolean deleteFlag);
	
	User findUserByUsernameOrEmailOrMobileAndDeleteFlag(String username, String email, String mobile, Boolean deleteFlag);
	
	User findUserByEmailAndDeleteFlag(String email, Boolean deleteFlag);
	
	User findUserByMobileAndDeleteFlag(String mobile, Boolean deleteFlag);
	
	
	@Query(value = "SELECT new com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserDto"
			+ "(u.id, u.username, u.email, u.mobile, u.name, u.nickname, u.sex, u.status, u.userType, "
			+ "u.headPortrait, u.weixin, u.qqNo, u.descr, u.code, u.identityNo, u.identityType, u.tenantId, "
			+ "u.customerId, u.parkId, u.systemCode, u.updatedBy, u.updatedDate) FROM User as u WHERE deleteFlag =:deleteFlag")
	List<UserDto> findUsers(@Param("deleteFlag") Boolean deleteFlag);
	
	List<User> findByDeleteFlag(Boolean deleteFlag);
	
	List<User> findByTenantId(Long tenantId);
	
	List<User> findByCustomerId(Long customerId);
	
	List<User> findByParkId(Long parkId);
}
 