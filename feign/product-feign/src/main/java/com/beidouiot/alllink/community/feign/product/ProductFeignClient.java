package com.beidouiot.alllink.community.feign.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.feign.product.impl.ProductFeignClientFallback;


@FeignClient(value = "product-center-server", fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {
	
	@PostMapping("/pc/product/v1/findOne")
	ResultDataRro<ProductDto> findProductById(ID id);
}
