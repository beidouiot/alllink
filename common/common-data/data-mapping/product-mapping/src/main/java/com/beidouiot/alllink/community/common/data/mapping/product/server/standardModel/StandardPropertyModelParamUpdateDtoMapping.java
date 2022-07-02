package com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.product.StandardPropertyModelParam;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelParamUpdateDto;

/**
*
* @Description Dto转实体类
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface StandardPropertyModelParamUpdateDtoMapping extends BaseMapping<StandardPropertyModelParam, StandardPropertyModelParamUpdateDto> {

}
