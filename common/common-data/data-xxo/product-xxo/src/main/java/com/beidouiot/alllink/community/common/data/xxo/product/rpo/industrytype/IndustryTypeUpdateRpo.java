package com.beidouiot.alllink.community.common.data.xxo.product.rpo.industrytype;

import java.io.Serializable;

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
* @Description 修改行业类别请求
* @author longww
* @date 2021年12月23日
*/
@Api(tags="修改行业类别请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndustryTypeUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8753554959492649381L;

	@ApiModelProperty(value = "行业类别Id", dataType = "Long", required = true)
	@NotNull(message = "行业类别Id不能为空！")
	@CheckNumber(message = "行业类别Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "行业类别名称", dataType = "String", required = false)
	private String name;
	
	@ApiModelProperty(value = "行业类别编号", dataType = "String", required = false)
	private String code;
	
	@ApiModelProperty(value = "行业类别描述", dataType = "String", required = false)
	private String descr;
	
	@ApiModelProperty(value = "行业类别排序号", dataType = "Long", required = false)
	private Integer sortNo;
	
	@ApiModelProperty(value = "父Id", dataType = "Long", required = false)
	private Long pid;
}
