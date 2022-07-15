package com.beidouiot.alllink.community.feign.user.impl;

import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserDto;
import com.beidouiot.alllink.community.feign.user.UserFeignClient;

public class UserFeignClientFallback implements UserFeignClient {

	@Override
	public ResultDataRro<UserDto> findUserById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
