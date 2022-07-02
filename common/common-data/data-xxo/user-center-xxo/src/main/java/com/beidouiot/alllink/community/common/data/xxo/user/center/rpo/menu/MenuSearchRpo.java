package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.menu;



import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SearchBaseRpo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 查询菜单请求信息
* @author longww
* @date 2021年4月11日
*/
@Api(tags="查询菜单请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -327743922787011412L;

	@ApiModelProperty(value = "菜单名称, length = 100", dataType = "String", required = false)
	private String name;

	@ApiModelProperty(value = "地址, length = 200", dataType = "String", required = false)
	private String addr;
	
	@ApiModelProperty(value = "图标, length = 200", dataType = "String", required = false)
	private String icon;
	
	@ApiModelProperty(value = "菜单类型, length = 200", dataType = "String", required = false)
	private String type;
	
	@ApiModelProperty(value = "菜单排序号", dataType = "Integer", required = false)
	private Integer sortNo;
	
	@ApiModelProperty(value = "菜单所在层数", dataType = "Integer", required = false)
	private Integer level;
	
	@ApiModelProperty(value = "是否叶节点", dataType = "Boolean", required = false)
	private Boolean leafFlag;
	
	@ApiModelProperty(value = "上级菜单Id", dataType = "Integer", required = false)
	private Long pid;
}
