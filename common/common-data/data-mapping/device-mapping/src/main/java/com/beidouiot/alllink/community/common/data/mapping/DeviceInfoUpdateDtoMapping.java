package com.beidouiot.alllink.community.common.data.mapping;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.device.DeviceInfo;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceInfoUpdateDto;


/**
 * 
 *
 * @Description Dto转实体类
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface  DeviceInfoUpdateDtoMapping extends BaseMapping<DeviceInfo, DeviceInfoUpdateDto> {

	
}
