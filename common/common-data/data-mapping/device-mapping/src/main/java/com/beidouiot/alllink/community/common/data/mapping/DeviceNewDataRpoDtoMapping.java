package com.beidouiot.alllink.community.common.data.mapping;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataDto;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceNewDataAddRpo;

/**
 * 
 *
 * @Description 增加请求参数转Dto
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface DeviceNewDataRpoDtoMapping extends BaseMapping<DeviceNewDataDto, DeviceNewDataAddRpo> {

}
