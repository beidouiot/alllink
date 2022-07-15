package com.beidouiot.alllink.community.common.data.mapping;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.device.DeviceNewData;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataDto;

@Mapper(componentModel = "spring")
public interface DeviceNewDataDtoMapping extends BaseMapping<DeviceNewData, DeviceNewDataDto> {

}
