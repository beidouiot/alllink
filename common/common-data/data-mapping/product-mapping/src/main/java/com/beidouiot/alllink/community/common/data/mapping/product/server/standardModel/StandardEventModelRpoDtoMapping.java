package com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardEventModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardEventModelAddRpo;

/**
*
* @Description 增加请求参数转Dto
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface StandardEventModelRpoDtoMapping extends BaseMapping<StandardEventModelDto, StandardEventModelAddRpo> {

}
