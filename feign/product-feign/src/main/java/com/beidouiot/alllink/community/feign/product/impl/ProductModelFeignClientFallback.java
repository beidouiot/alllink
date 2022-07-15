package com.beidouiot.alllink.community.feign.product.impl;

import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.feign.product.ProductModelFeignClient;

public class ProductModelFeignClientFallback implements ProductModelFeignClient {

	public ResultDataRro<ProductModelDto> findPubProductModelByProductId(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
