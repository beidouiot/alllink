package com.beidouiot.alllink.community.common.data.mapping.user.center.position;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.PositionUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.position.PositionUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年5月9日
*/
@Mapper(componentModel = "spring")
public interface PositionUpdateRpoToPositionUpdateDtoMapping extends BaseMapping<PositionUpdateDto, PositionUpdateRpo> {

}
