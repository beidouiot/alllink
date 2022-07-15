package com.beidouiot.alllink.community.common.data.mapping;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.device.DeviceInfo;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceInfoDto;

@Mapper(componentModel = "spring")
public interface DeviceInfoDtoMapping extends BaseMapping<DeviceInfo, DeviceInfoDto> {

}
