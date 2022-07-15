package com.beidouiot.alllink.community.feign.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.feign.product.impl.ProductModelFeignClientFallback;

@FeignClient(value = "product-center-server", fallback = ProductModelFeignClientFallback.class)
public interface ProductModelFeignClient {

	@PostMapping("/pc/product/model/v1/findPubModel")
	ResultDataRro<ProductModelDto> findPubProductModelByProductId(ID id);
}
