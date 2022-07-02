package com.beidouiot.alllink.community.common.data.mapping.user.center.customer;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.user.center.Customer;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.CustomerUpdateDto;


/**
 * 
 *
 * @Description Dto转实体类
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface  CustomerUpdateDtoMapping extends BaseMapping<Customer, CustomerUpdateDto> {

	
}
