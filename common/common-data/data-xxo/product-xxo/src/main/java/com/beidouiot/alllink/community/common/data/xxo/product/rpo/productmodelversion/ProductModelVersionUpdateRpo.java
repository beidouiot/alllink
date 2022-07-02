package com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodelversion;

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
* @Description 修改产品物模型历史版本请求信息
* @author longww
* @date 2021年12月23日
*/
@Api(tags="修改产品物模型历史版本请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductModelVersionUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6472382849453627736L;

	@ApiModelProperty(value = "产品物模型历史版本Id", dataType = "Long", required = true)
	@NotNull(message = "产品物模型历史版本Id不能为空！")
	@CheckNumber(message = "产品物模型历史版本Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "物模型内容", dataType = "String", required = true)
	@NotBlank(message = "物模型内容不能为空！")
	private String modelContent;
	
	@ApiModelProperty(value = "当前使用标志", dataType = "Boolean", required = true)
	@NotNull(message = "当前使用标志不能为空！")
	private Boolean userFlag;
	
	@ApiModelProperty(value = "版本号", dataType = "Integer", required = true)
	@NotBlank(message = "版本号不能为空！")
	private Integer versionNumber;

}
