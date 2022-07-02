package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.menu;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.beidouiot.alllink.community.common.data.xxo.validator.CheckNumber;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 *
 * @Description 增加菜单/功能请求信息
 * @author longww
 * @date 2021年4月11日
 */
@Api(tags="增加菜单/功能请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuAddRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2412571557985629971L;
	
	@ApiModelProperty(value = "菜单名称, length = 100", dataType = "String", required = true)
	@NotBlank(message = "菜单名不能为空！")
	@Length(min=1, max=100, message="租户名称长度不能超过100个字符")
	private String name;

	@ApiModelProperty(value = "地址, length = 200", dataType = "String", required = false)
	@Length(min=0, max=200, message="地址长度不能超过200个字符")
	private String addr;
	
	@ApiModelProperty(value = "图标, length = 200", dataType = "String", required = false)
	@Length(min=0, max=200, message="图标长度不能超过200个字符")
	private String icon;
	
	@ApiModelProperty(value = "菜单类型, length = 200", dataType = "String", required = true)
	@NotBlank(message = "菜单类型不能为空！")
	private String type;
	
	@ApiModelProperty(value = "菜单排序号", dataType = "Integer", required = true)
	@NotNull(message = "排序号不能为空！")
	@CheckNumber(message = "排序号必须为正整数")
	private Integer sortNo;
		
	@ApiModelProperty(value = "上级菜单Id", dataType = "Integer", required = false)
	private Long pid;

}
