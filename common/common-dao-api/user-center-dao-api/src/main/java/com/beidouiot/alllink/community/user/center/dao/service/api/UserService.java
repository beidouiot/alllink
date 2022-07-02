package com.beidouiot.alllink.community.user.center.dao.service.api;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.user.center.User;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateEmailRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateHeadPortraitRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateMobileRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdatePasswordRpo;

/**
 * 
 *
 * @Description 用户管理业务逻辑接口
 * @author longww
 * @date 2021年4月11日
 */
public interface UserService extends BaseService<UserDto, UserUpdateDto, User, Long>, UserDetailsService {

	void updatePassword(UserUpdatePasswordRpo userUpdatePasswordRpo, UserDto userDto) throws ServiceException;
	
	void updateMobile(UserUpdateMobileRpo userUpdateMobileRpo, UserDto userDto) throws ServiceException;
	
	void updateEmail(UserUpdateEmailRpo userUpdateEmailRpo, UserDto userDto) throws ServiceException;
	
	void updateHeadPortrait(UserUpdateHeadPortraitRpo userUpdateHeadPortraitRpo, UserDto userDto) throws ServiceException;
	
	UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException;
	
	String sendSmsCode(String mobile) throws ServiceException;
}
