package com.beidouiot.alllink.community.common.data.xxo.product.rpo.producttype;

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
* @Description 修改产品类别请求信息
* @author longww
* @date 2021年12月23日
*/
@Api(tags="修改产品类别请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductTypeUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5987641984104849160L;
	
	@ApiModelProperty(value = "产品类别Id", dataType = "Long", required = true)
	@NotNull(message = "产品类别Id不能为空！")
	@CheckNumber(message = "产品类别Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "产品类别名称", dataType = "String", required = true)
	@NotBlank(message = "产品类别名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "产品类别编号", dataType = "String", required = true)
	@NotBlank(message = "产品类别编号不能为空！")
	private String code;
	
	@ApiModelProperty(value = "产品类别描述", dataType = "String", required = false)
	private String descr;
	
}
