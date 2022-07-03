package com.beidouiot.alllink.community.product.server.dao.service.api;

import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.product.Product;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductUpdateDto;

/**
*
* @Description 产品管理业务逻辑接口
* @author longww
* @date 2021年12月23日
*/
public interface ProductService extends BaseService<ProductDto, ProductUpdateDto, Product, Long> {

}
