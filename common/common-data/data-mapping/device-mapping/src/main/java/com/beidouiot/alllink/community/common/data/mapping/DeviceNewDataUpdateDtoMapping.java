package com.beidouiot.alllink.community.common.data.mapping;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.device.DeviceNewData;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataUpdateDto;


/**
 * 
 *
 * @Description Dto转实体类
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface  DeviceNewDataUpdateDtoMapping extends BaseMapping<DeviceNewData, DeviceNewDataUpdateDto> {

	
}
