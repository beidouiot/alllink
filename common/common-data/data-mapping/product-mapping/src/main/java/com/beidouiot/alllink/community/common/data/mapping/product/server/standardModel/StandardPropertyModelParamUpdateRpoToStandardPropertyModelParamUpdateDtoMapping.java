package com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardPropertyModelParamUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface StandardPropertyModelParamUpdateRpoToStandardPropertyModelParamUpdateDtoMapping extends BaseMapping<StandardPropertyModelParamUpdateDto, StandardPropertyModelParamUpdateRpo> {

}
