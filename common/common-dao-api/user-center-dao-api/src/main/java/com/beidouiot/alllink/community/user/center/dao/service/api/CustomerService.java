package com.beidouiot.alllink.community.user.center.dao.service.api;

import com.beidouiot.alllink.community.common.dao.api.service.BaseService;
import com.beidouiot.alllink.community.common.data.entity.user.center.Customer;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.CustomerDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.CustomerUpdateDto;

/**
 * 
 *
 * @Description 行业管理业务逻辑接口
 * @author longww
 * @date 2021年4月11日
 */
public interface CustomerService extends BaseService<CustomerDto, CustomerUpdateDto, Customer, Long> {

}
