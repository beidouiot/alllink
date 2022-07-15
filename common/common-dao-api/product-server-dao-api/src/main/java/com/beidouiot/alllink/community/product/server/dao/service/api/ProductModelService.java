package com.beidouiot.alllink.community.product.server.dao.service.api;

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

	/**
	 * 发布产品物模型
	 * @param productId
	 * @return
	 * @throws ServiceException
	 */
	Boolean publishModel(Long productId) throws ServiceException;
	
	/**
	 * 查询产品物模型
	 * @param productId
	 * @return
	 * @throws ServiceException
	 */
	ProductModelDto findProductModels(Long productId) throws ServiceException;
	
	/**
	 * 查询发布的物模型
	 * @param productId
	 * @return
	 * @throws ServiceException
	 */
	public ProductModelDto findPubProductModels(Long productId) throws ServiceException;
	
}
