package com.beidouiot.alllink.community.common.data.mapping.product.server.productType;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.producttype.ProductTypeUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface ProductTypeUpdateRpoToProductTypeUpdateDtoMapping extends BaseMapping<ProductTypeUpdateDto, ProductTypeUpdateRpo> {

}
