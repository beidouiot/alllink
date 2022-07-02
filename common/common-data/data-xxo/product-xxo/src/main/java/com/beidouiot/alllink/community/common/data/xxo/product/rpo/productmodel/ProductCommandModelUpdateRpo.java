package com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.beidouiot.alllink.community.common.data.xxo.validator.CheckNumber;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月11日
*/
@Api(tags="修改产品指令物模型请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductCommandModelUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4433096883788764983L;

	@ApiModelProperty(value = "产品指令物模型参数Id", dataType = "Long", required = true)
	@NotNull(message = "产品指令物模型参数Id不能为空！")
	@CheckNumber(message = "产品指令物模型参数Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "指令名称", dataType = "String", required = true)
	@NotBlank(message = "指令名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "调用方式", dataType = "Integer", required = true)
	@NotNull(message = "调用方式不能为空！")
	private Integer callMode;
	
	@ApiModelProperty(value = "物模型参数集（物模型参数ID集），多ID以逗号隔开", dataType = "String", required = false)
	private String params;
	
	@ApiModelProperty(value = "备注", dataType = "String", required = false)
	private String remark;
	
	@ApiModelProperty(value = "发布状态", dataType = "Boolean", required = true)
	@NotNull(message = "发布状态不能为空！")
	private Boolean status;
		
}
