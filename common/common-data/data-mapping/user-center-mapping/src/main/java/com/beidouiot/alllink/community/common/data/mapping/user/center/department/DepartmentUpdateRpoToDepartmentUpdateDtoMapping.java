package com.beidouiot.alllink.community.common.data.mapping.user.center.department;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.DepartmentUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.department.DepartmentUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年12月23日
*/
@Mapper(componentModel = "spring")
public interface DepartmentUpdateRpoToDepartmentUpdateDtoMapping extends BaseMapping<DepartmentUpdateDto, DepartmentUpdateRpo> {

}
