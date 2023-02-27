package com.beidouiot.alllink.community.device.service.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.device.DeviceInfo;

/**
*
* @Description 设备管理数据库交互接口
* @author longww
* @date 2021年12月24日
*/

public interface DeviceInfoRepository extends PagingAndSortingRepository<DeviceInfo, Long>, JpaSpecificationExecutor<DeviceInfo> {

	List<DeviceInfo> findByDeleteFlag(Boolean deleteFlag);
	
	List<DeviceInfo> findByDeleteFlagAndEnableFlag(Boolean deleteFlag, Boolean enableFlag);
	
	List<DeviceInfo> findByDeleteFlagAndGatewayFlag(Boolean deleteFlag, Boolean gatewayFlag);
	
	List<DeviceInfo> findByDeleteFlagAndEnableFlagAndGatewayFlag(Boolean deleteFlag, Boolean enableFlag, Boolean gatewayFlag);
	
	List<DeviceInfo> findByTenantIdAndDeleteFlag(Long tenantId, Boolean deleteFlag);
	
	List<DeviceInfo> findByTenantIdAndDeleteFlagAndGatewayFlag(Long tenantId, Boolean deleteFlag, Boolean gatewayFlag);
	
	List<DeviceInfo> findByTenantIdAndDeleteFlagAndEnableFlag(Long tenantId, Boolean deleteFlag, Boolean enableFlag);
	
	List<DeviceInfo> findByTenantIdAndDeleteFlagAndGatewayFlagAndEnableFlag(Long tenantId, Boolean deleteFlag, Boolean gatewayFlag, Boolean enableFlag);
	
	List<DeviceInfo> findByDeviceSnAndDeleteFlagAndEnableFlag(String deviceSn, Boolean deleteFlag, Boolean enableFlag);
}
