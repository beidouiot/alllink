package com.beidouiot.alllink.community.common.data.mapping.user.center.role;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.role.RoleAddRpo;

/**
 * 
 *
 * @Description 增加请求参数转Dto
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface RoleRpoDtoMapping extends BaseMapping<RoleDto, RoleAddRpo> {

}
