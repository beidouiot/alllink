package com.beidouiot.alllink.community.common.data.mapping.product.server.industryType;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.product.IndustryType;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.IndustryTypeDto;

/**
*
* @Description Dto转实体类
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface IndustryTypeDtoMapping extends BaseMapping<IndustryType, IndustryTypeDto> {

}
