package com.beidouiot.alllink.community.common.data.mapping;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceInfoDto;
import com.beidouiot.alllink.community.common.data.xxo.device.rpo.DeviceInfoAddRpo;

/**
 * 
 *
 * @Description 增加请求参数转Dto
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface DeviceInfoRpoDtoMapping extends BaseMapping<DeviceInfoDto, DeviceInfoAddRpo> {

}
