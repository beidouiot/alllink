package com.beidouiot.alllink.community.common.data.mapping.user.center.user;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateRpo;

/**
 * 
 *
 * @Description 修改请求参数转Dto
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface UserUpdateRpoToUserUpdateDtoMapping extends BaseMapping<UserUpdateDto, UserUpdateRpo> {

}
