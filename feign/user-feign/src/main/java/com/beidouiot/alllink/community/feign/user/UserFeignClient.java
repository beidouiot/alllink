package com.beidouiot.alllink.community.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserSearchRpo;
import com.beidouiot.alllink.community.feign.user.impl.UserFeignClientFallback;


@FeignClient(value = ServiceConstants.USER_CENTER_SERVER, fallback = UserFeignClientFallback.class)
public interface UserFeignClient {
	
	@PostMapping(ServiceConstants.USER_URI_BASE+"v1/findOne")
	ResultDataRro<UserDto> findUserById(ID id);
	
	@PostMapping(ServiceConstants.USER_URI_BASE+"v1/findPage")
	ResultDataRro<SmartPage<UserDto>> findPage(UserSearchRpo userSearchRpo);
	
	
}
