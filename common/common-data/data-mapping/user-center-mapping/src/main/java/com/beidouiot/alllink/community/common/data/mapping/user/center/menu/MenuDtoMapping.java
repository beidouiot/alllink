package com.beidouiot.alllink.community.common.data.mapping.user.center.menu;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.user.center.Menu;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.MenuDto;

/**
 * 
 *
 * @Description Dto转实体类
 * @author longww
 * @date 2021年4月11日
 */
@Mapper(componentModel = "spring")
public interface MenuDtoMapping extends BaseMapping<Menu, MenuDto> {

}
