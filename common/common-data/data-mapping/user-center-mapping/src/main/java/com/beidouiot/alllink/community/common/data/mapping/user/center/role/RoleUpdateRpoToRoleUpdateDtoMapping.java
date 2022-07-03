package com.beidouiot.alllink.community.common.data.mapping.user.center.role;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.role.RoleUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年4月11日
*/
@Mapper(componentModel = "spring")
public interface RoleUpdateRpoToRoleUpdateDtoMapping extends BaseMapping<RoleUpdateDto, RoleUpdateRpo> {

}
