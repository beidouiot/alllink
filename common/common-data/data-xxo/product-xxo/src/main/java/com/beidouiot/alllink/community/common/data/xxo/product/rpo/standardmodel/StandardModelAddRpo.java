package com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 增加标准物模型请求信息
* @author longww
* @date 2021年12月23日
*/
@Api(tags="增加标准物模型请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardModelAddRpo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4261114031428615478L;

	@ApiModelProperty(value = "属性/事件/命令名称", dataType = "String", required = true)
	@NotBlank(message = "属性/事件/命令名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "属性/事件/命令标识符", dataType = "String", required = true)
	@NotBlank(message = "属性/事件/命令标识符不能为空！")
	private String code;
	
	@ApiModelProperty(value = "标准物模型类型", dataType = "Integer", required = true)
	@NotNull(message = "标准物模型类型不能为空！")
	private Integer funcType;
	
	@ApiModelProperty(value = "数据类型", dataType = "String", required = true)
	@NotBlank(message = "数据类型不能为空！")
	private String dataType;
	
	@ApiModelProperty(value = "访问类型", dataType = "Integer", required = true)
	@NotNull(message = "访问类型不能为空！")
	private Integer accessType;
	
	@ApiModelProperty(value = "发布状态", dataType = "Boolean", required = true)
	@NotNull(message = "发布状态不能为空！")
	private Boolean status;
	
	@ApiModelProperty(value = "产品类别", dataType = "Long", required = true)
	@NotNull(message = "产品类别id不能为空！")
	private Long productTypeId;
	
}
