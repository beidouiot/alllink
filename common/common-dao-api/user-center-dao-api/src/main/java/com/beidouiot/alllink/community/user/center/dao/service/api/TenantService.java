package com.beidouiot.alllink.community.user.center.dao.service.api;

import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.user.center.Tenant;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.TenantDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.TenantUpdateDto;

/**
 * 
 *
 * @Description 租户管理业务逻辑接口
 * @author longww
 * @date 2021年4月11日
 */
public interface TenantService extends BaseService<TenantDto, TenantUpdateDto, Tenant, Long> {

}
