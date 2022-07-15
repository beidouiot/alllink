package com.beidouiot.alllink.community.common.data.mapping;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceNewDataUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年4月11日
*/
@Mapper(componentModel = "spring")
public interface DeviceNewDataUpdateRpoToDeviceNewDataUpdateDtoMapping extends BaseMapping<DeviceNewDataUpdateDto, DeviceNewDataUpdateRpo> {

	
}
