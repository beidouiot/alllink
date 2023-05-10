package com.beidouiot.alllink.community.device.dao.service.api;

import java.util.List;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceInfo;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceCommandsDto;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceInfoDto;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceInfoUpdateDto;

/**
*
* @Description 设备管理业务逻辑接口
* @author longww
* @date 2021年12月23日
*/
public interface DeviceInfoService extends BaseService<DeviceInfoDto, DeviceInfoUpdateDto, DeviceInfo, Long> {

	List<DeviceInfoDto> findAllGateways() throws ServiceException;
	List<DeviceInfoDto> findAllDeviceInfo(Boolean gatewayFlag, Boolean enableFlag);
	
	void sendProperties(DeviceCommandsDto deviceCommandsDto) throws ServiceException;
	
	void sendEvents(DeviceCommandsDto deviceCommandsDto) throws ServiceException;
	
	void sendCommands(DeviceCommandsDto deviceCommandsDto) throws ServiceException;
}
