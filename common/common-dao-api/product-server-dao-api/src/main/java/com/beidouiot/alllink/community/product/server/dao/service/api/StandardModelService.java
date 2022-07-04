package com.beidouiot.alllink.community.product.server.dao.service.api;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.product.StandardModel;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardModelUpdateDto;

/**
*
* @Description 标准物模型管理业务逻辑接口
* @author longww
* @date 2021年12月23日
*/
public interface StandardModelService extends BaseService<StandardModelDto, StandardModelUpdateDto, StandardModel, Long> {

	StandardModelDto findStandardModels(Long productTypeId) throws ServiceException;
	
	Boolean pubStandardModel(Long productTypeId) throws ServiceException;
}
