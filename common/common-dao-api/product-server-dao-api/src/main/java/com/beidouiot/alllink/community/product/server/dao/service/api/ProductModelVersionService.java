package com.beidouiot.alllink.community.product.server.dao.service.api;

import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.product.ProductModelVersion;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelVersionDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelVersionUpdateDto;

/**
*
* @Description 产品物模型历史版本管理业务逻辑接口
* @author longww
* @date 2021年12月23日
*/

public interface ProductModelVersionService extends BaseService<ProductModelVersionDto, ProductModelVersionUpdateDto, ProductModelVersion, Long> {

}
