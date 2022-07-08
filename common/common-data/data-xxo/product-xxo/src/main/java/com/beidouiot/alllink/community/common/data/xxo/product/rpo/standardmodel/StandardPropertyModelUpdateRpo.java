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
@Api(tags="修改标准属性物模型请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardPropertyModelUpdateRpo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5723419258147241737L;

	@ApiModelProperty(value = "标准属性物模型Id", dataType = "Long", required = true)
	@NotNull(message = "标准属性物模型Id不能为空！")
	@CheckNumber(message = "标准属性物模型Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "属性名称", dataType = "String", required = true)
	@NotBlank(message = "属性名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "数据类型", dataType = "String", required = true)
	@NotBlank(message = "数据类型不能为空！")
	private String dataType;
	
	@ApiModelProperty(value = "数据类型数据定义", dataType = "String", required = false)
	private String dataSpecs;
	
	@ApiModelProperty(value = "访问类型", dataType = "Integer", required = true)
	@NotNull(message = "访问类型不能为空！")
	private Integer accessType;

	@ApiModelProperty(value = "产品类别Id", dataType = "Long", required = true)
	@NotNull(message = "产品类别Id不能为空！")
	@CheckNumber(message = "产品类别Id必须为正整数" )
	private Long productTypeId;

}
