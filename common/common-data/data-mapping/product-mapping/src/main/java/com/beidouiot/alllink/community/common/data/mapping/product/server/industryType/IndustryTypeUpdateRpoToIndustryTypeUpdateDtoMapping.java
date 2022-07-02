package com.beidouiot.alllink.community.common.data.mapping.product.server.industryType;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.IndustryTypeUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.industrytype.IndustryTypeUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface IndustryTypeUpdateRpoToIndustryTypeUpdateDtoMapping extends BaseMapping<IndustryTypeUpdateDto, IndustryTypeUpdateRpo> {

}
