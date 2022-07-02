package com.beidouiot.alllink.community.common.data.mapping.product.server.productModel;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel.ProductEventModelParamAddRpo;

/**
*
* @Description 增加请求参数转Dto
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface ProductEventModelParamRpoDtoMapping extends BaseMapping<ProductEventModelParamDto, ProductEventModelParamAddRpo> {

}
