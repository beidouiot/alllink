package com.beidouiot.alllink.community.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserDto;
import com.beidouiot.alllink.community.feign.user.impl.UserFeignClientFallback;


@FeignClient(value = "user-center-server", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {
	
	@PostMapping("/uc/user/v1/findOne")
	ResultDataRro<UserDto> findUserById(ID id);
}
