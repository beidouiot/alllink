package com.beidouiot.alllink.community.common.data.mapping.product.server.product;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.product.ProductUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface ProductUpdateRpoToProductUpdateDtoMapping extends BaseMapping<ProductUpdateDto, ProductUpdateRpo> {

}
