package com.beidouiot.alllink.community.common.data.mapping;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceInfoUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceInfoUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年4月11日
*/
@Mapper(componentModel = "spring")
public interface DeviceInfoUpdateRpoToDeviceInfoUpdateDtoMapping extends BaseMapping<DeviceInfoUpdateDto, DeviceInfoUpdateRpo> {

	
}
