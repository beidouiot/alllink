package com.beidouiot.alllink.community.product.server.dao.service.api;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.product.ProductType;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeIndustryTypeDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeUpdateDto;

/**
*
* @Description 产品类别管理业务逻辑接口
* @author longww
* @date 2021年12月23日
*/

public interface ProductTypeService extends BaseService<ProductTypeDto, ProductTypeUpdateDto, ProductType, Long> {

	void saveProductTypeIndustryType(ProductTypeIndustryTypeDto productTypeIndustryTypeDto) throws ServiceException;
}
