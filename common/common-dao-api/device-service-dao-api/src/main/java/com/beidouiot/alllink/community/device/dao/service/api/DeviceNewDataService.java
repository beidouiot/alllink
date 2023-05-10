package com.beidouiot.alllink.community.device.dao.service.api;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceNewData;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataDto;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataUpdateDto;

/**
*
* @Description 设备管理业务逻辑接口
* @author longww
* @date 2021年12月23日
*/
public interface DeviceNewDataService extends BaseService<DeviceNewDataDto, DeviceNewDataUpdateDto, DeviceNewData, Long> {
	
	/**
	 * 查询指定设备数据
	 * @param deviceId
	 * @return
	 * @throws ServiceException
	 */
	List<DeviceNewDataDto> findDeviceNewDatasByDeviceId(Long deviceId) throws ServiceException;
	
}
