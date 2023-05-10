package com.beidouiot.alllink.community.device.service.dao.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.device.DeviceData;

public interface DeviceDataRepository extends PagingAndSortingRepository<DeviceData, Long>, JpaSpecificationExecutor<DeviceData>{

	Page<DeviceData> findDeviceDataByDeviceIdAndCollectTimeBetweenOrderByDesc(Long deviceId, Date start, Date end, Pageable pageable);
	
	Page<DeviceData> findDeviceDataByDeviceSnAndCollectTimeBetweenOrderByDesc(String deviceSN, Date start, Date end, Pageable pageable);
	
	Page<DeviceData> findDeviceDataByDeviceIdAndModelCodeAndCollectTimeBetweenOrderByDesc(Long deviceId, String modelCode, Date start, Date end, Pageable pageable);
	
	Page<DeviceData> findDeviceDataByDeviceSnAndModelCodeAndCollectTimeBetweenOrderByDesc(String deviceSn, String modelCode, Date start, Date end, Pageable pageable);
	
}
