package com.beidouiot.alllink.community.common.data.mapping.user.center.park;


import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.user.center.Park;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.ParkUpdateDto;


/**
 * 
 *
 * @Description Dto转实体类
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface ParkUpdateDtoMapping extends BaseMapping<Park, ParkUpdateDto> {

}
