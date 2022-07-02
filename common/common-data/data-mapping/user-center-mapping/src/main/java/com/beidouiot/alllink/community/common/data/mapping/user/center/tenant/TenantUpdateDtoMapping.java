package com.beidouiot.alllink.community.common.data.mapping.user.center.tenant;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.user.center.Tenant;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.TenantUpdateDto;

/**
 * 
 *
 * @Description Dto转实体类
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface TenantUpdateDtoMapping extends BaseMapping<Tenant, TenantUpdateDto> {

}
