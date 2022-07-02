package com.beidouiot.alllink.community.common.data.mapping.user.center.park;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.ParkDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.park.ParkAddRpo;

/**
 * 
 *
 * @Description 增加请求参数转Dto
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface ParkRpoDtoMapping extends BaseMapping<ParkDto, ParkAddRpo> {

}
