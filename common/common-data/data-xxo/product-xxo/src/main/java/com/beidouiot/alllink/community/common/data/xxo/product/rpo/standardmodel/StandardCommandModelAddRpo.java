package com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel;

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
@Api(tags="增加标准指令物模型请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardCommandModelAddRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8824522197711247727L;

	@ApiModelProperty(value = "指令名称", dataType = "String", required = true)
	@NotBlank(message = "指令名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "指令标识符", dataType = "String", required = true)
	@NotBlank(message = "指令标识符不能为空！")
	private String code;
	
	@ApiModelProperty(value = "调用方式", dataType = "Integer", required = true)
	@NotNull(message = "调用方式不能为空！")
	private Integer callMode;
	
	@ApiModelProperty(value = "物模型参数集（物模型参数ID集），多ID以逗号隔开", dataType = "String", required = false)
	private String params;
	
	@ApiModelProperty(value = "备注", dataType = "String", required = false)
	private String remark;
	
	@ApiModelProperty(value = "产品类别id", dataType = "Long", required = true)
	@NotNull(message = "产品类别id不能为空！")
	@CheckNumber(message = "产品类别id必须为正整数" )
	private Long productTypeId;
	
}
