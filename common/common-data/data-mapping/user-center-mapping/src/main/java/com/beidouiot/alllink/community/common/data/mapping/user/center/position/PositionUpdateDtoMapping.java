package com.beidouiot.alllink.community.common.data.mapping.user.center.position;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.user.center.Position;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.PositionUpdateDto;

/**
*
* @Description Dto转实体类
* @author longww
* @date 2021年5月9日
*/
@Mapper(componentModel = "spring")
public interface PositionUpdateDtoMapping extends BaseMapping<Position, PositionUpdateDto> {

}
