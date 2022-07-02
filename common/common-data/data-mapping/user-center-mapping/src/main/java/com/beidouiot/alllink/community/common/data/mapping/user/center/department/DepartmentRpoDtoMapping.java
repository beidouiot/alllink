package com.beidouiot.alllink.community.common.data.mapping.user.center.department;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.DepartmentDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.department.DepartmentAddRpo;

/**
*
* @Description 增加请求参数转Dto
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface DepartmentRpoDtoMapping extends BaseMapping<DepartmentDto, DepartmentAddRpo> {

}
