package com.beidouiot.alllink.community.common.data.mapping.user.center.park;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.ParkUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.park.ParkUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年4月11日
*/
@Mapper(componentModel = "spring")
public interface ParkUpdateRpoToParkUpdateDtoMapping extends BaseMapping<ParkUpdateDto, ParkUpdateRpo> {

}
