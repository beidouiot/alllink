package com.beidouiot.alllink.community.common.data.mapping.user.center.industry;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.IndustryUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.industry.IndustryUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年4月11日
*/
@Mapper(componentModel = "spring")
public interface IndustryUpdateRpoToIndustryUpdateDtoMapping extends BaseMapping<IndustryUpdateDto, IndustryUpdateRpo> {

}
