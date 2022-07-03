package com.beidouiot.alllink.community.user.center.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.beidouiot.alllink.community.common.data.entity.user.center.UserPosition;
import com.beidouiot.alllink.community.common.data.entity.user.center.UserPositionKey;

/**
*
* @Description TODO
* @author longww
* @date 2021年5月9日
*/

public interface UserPositionRepository extends PagingAndSortingRepository<UserPosition, UserPositionKey>, JpaSpecificationExecutor<UserPosition>{

	@Query(value = "FROM UserPosition WHERE id.positionId =:positionId")
	List<UserPosition> findUserPositionByPositionId(@Param("positionId") Long positionId);
	
	@Query(value = "FROM UserPosition WHERE id.userId =:userId")
	List<UserPosition> findUserPositionByUserId(@Param("userId") Long userId);
}
