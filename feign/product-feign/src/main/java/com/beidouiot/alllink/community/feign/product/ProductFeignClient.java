package com.beidouiot.alllink.community.feign.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.product.ProductSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.feign.product.impl.ProductFeignClientFallback;


@FeignClient(value = ServiceConstants.PRODUCT_CENTER_SERVER, fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {
	
	@PostMapping(ServiceConstants.PRODUCT_URI_BASE+"v1/findOne")
	ResultDataRro<ProductDto> findProductById(ID id);
	
	@PostMapping(ServiceConstants.PRODUCT_URI_BASE+"v1/find/name")
	public ResultDataRro<ProductDto> findByName(ProductSearchRpo productSearchRpo);
}
