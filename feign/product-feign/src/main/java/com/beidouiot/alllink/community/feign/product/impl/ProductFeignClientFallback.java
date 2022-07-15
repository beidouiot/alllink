package com.beidouiot.alllink.community.feign.product.impl;

import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.feign.product.ProductFeignClient;

public class ProductFeignClientFallback implements ProductFeignClient {

	@Override
	public ResultDataRro<ProductDto> findProductById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
