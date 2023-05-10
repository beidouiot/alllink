package com.beidouiot.alllink.community.device.service.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.device.DeviceNewData;

/**
*
* @Description 产品管理数据库交互接口
* @author longww
* @date 2021年12月24日
*/

public interface DeviceNewDataRepository extends PagingAndSortingRepository<DeviceNewData, Long>, JpaSpecificationExecutor<DeviceNewData> {

	List<DeviceNewData> findByDeleteFlag(Boolean deleteFlag);
	
	List<DeviceNewData> findByDeviceIdAndDeleteFlag(Long deviceId, Boolean deleteFlag);
	
	List<DeviceNewData> findByDeviceIdAndModelCodeAndModelTypeAndDeleteFlag(Long deviceId, String modelCode, Integer modelType, Boolean deleteFlag);
	
}
