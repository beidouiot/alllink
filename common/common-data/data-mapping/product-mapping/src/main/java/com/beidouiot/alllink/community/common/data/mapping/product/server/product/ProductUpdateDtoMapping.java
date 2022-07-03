package com.beidouiot.alllink.community.common.data.mapping.product.server.product;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.product.Product;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductUpdateDto;

/**
*
* @Description Dto转实体类
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface ProductUpdateDtoMapping extends BaseMapping<Product, ProductUpdateDto> {

}
