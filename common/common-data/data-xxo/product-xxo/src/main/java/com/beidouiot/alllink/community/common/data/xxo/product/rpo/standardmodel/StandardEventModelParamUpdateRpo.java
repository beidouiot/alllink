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
@Api(tags="修改标准事件物模型参数请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardEventModelParamUpdateRpo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7154857888448035510L;

	@ApiModelProperty(value = "标准事件物模型参数Id", dataType = "Long", required = true)
	@NotNull(message = "标准事件物模型参数Id不能为空！")
	@CheckNumber(message = "标准事件物模型参数Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "参数名称", dataType = "String", required = true)
	@NotBlank(message = "参数名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "数据类型", dataType = "String", required = true)
	@NotBlank(message = "数据类型不能为空！")
	private String dataType;
	
	@ApiModelProperty(value = "数据类型数据定义", dataType = "String", required = false)
	private String dataSpecs;
	
	@ApiModelProperty(value = "参数方向", dataType = "Integer", required = true)
	@NotNull(message = "参数方向不能为空！")
	private Integer direction;
	
}
