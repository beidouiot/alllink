package com.beidouiot.alllink.community.common.data.mapping.product.server.productType;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.product.ProductType;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeUpdateDto;

/**
*
* @Description Dto转实体类
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface ProductTypeUpdateDtoMapping extends BaseMapping<ProductType, ProductTypeUpdateDto> {

}
