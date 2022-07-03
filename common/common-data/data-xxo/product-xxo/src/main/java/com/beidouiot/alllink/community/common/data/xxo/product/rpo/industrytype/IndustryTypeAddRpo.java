package com.beidouiot.alllink.community.common.data.xxo.product.rpo.industrytype;

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
* @Description 增加行业类别请求
* @author longww
* @date 2021年12月23日
*/
@Api(tags="增加行业类别请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndustryTypeAddRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1973722138211514408L;

	@ApiModelProperty(value = "行业类别名称", dataType = "String", required = true)
	@NotBlank(message = "行业类别名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "行业类别编号", dataType = "String", required = true)
	@NotBlank(message = "行业类别编号不能为空！")
	private String code;
	
	@ApiModelProperty(value = "行业类别描述", dataType = "String", required = false)
	private String descr;
	
	@ApiModelProperty(value = "行业类别排序号", dataType = "Long", required = true)
	@NotNull(message = "行业类别排序号不能为空！")
	private Integer sortNo;
	
	@ApiModelProperty(value = "父Id", dataType = "Long", required = false)
	private Long pid;
}
