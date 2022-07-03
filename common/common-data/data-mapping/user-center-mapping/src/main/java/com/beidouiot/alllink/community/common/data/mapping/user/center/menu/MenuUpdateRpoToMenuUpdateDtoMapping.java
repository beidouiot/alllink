package com.beidouiot.alllink.community.common.data.mapping.user.center.menu;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.MenuUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.menu.MenuUpdateRpo;

/**
*
* @Description 修改请求参数转Dto
* @author longww
* @date 2021年4月11日
*/
@Mapper(componentModel = "spring")
public interface MenuUpdateRpoToMenuUpdateDtoMapping  extends BaseMapping<MenuUpdateDto, MenuUpdateRpo> {

}
