package com.beidouiot.alllink.community.product.server.dao.service.api;

import java.util.List;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.product.ProductModel;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelUpdateDto;

/**
*
* @Description 产品物模型管理业务逻辑接口
* @author longww
* @date 2021年12月23日
*/

public interface ProductModelService extends BaseService<ProductModelDto, ProductModelUpdateDto, ProductModel, Long> {

	public void saveAllEntity(List<ProductModelDto> productModelDtoList) throws ServiceException;
	
	/**
	 * 发布产品物模型
	 * @param productModelDto          
	 * @throws ServiceException
	 */
	public void publishModel(ProductModelDto productModelDto) throws ServiceException;
}
