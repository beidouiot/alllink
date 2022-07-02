package com.beidouiot.alllink.community.common.data.mapping.user.center.tenant;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.TenantUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.tenant.TenantUpdateRpo;
/**
 * 
 *
 * @Description 修改请求参数转Dto
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface TenantUpdateRpoToTenantUpdateDtoMapping extends BaseMapping<TenantUpdateDto, TenantUpdateRpo> {

}
