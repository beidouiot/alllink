package com.beidouiot.alllink.community.common.data.mapping.user.center.menu;

import org.mapstruct.Mapper;

import com.beidouiot.alllink.community.common.data.entity.user.center.Menu;
import com.beidouiot.alllink.community.common.data.mapping.BaseMapping;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.menu.MenuTreeRro;

/**
*
* @Description TODO
* @author Administrator
* @date 2022年5月25日
*/
@Mapper(componentModel = "spring")
public interface MenutoMenuTreeRroMapping extends BaseMapping<Menu, MenuTreeRro> {

}
