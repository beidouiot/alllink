package com.beidouiot.alllink.community.common.data.mapping.user.center.menu;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.MenuDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.menu.MenuAddRpo;

/**
 * 
 *
 * @Description 增加请求参数转Dto
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface MenuRpoDtoMapping extends BaseMapping<MenuDto, MenuAddRpo> {

}
