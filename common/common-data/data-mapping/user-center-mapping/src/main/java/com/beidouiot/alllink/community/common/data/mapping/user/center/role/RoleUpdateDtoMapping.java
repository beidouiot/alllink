package com.beidouiot.alllink.community.common.data.mapping.user.center.role;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.user.center.Role;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.RoleUpdateDto;

/**
 * 
 *
 * @Description  Dto转实体类
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface RoleUpdateDtoMapping extends BaseMapping<Role, RoleUpdateDto> {

}
