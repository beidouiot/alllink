package com.beidouiot.alllink.community.common.data.xxo.product.rpo.industrytype;

import javax.validation.constraints.NotNull;

import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SearchBaseRpo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 查询行业类别请求
* @author longww
* @date 2021年12月23日
*/
@Api(tags="查询行业类别请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndustryTypeSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3958829669447924109L;

	@ApiModelProperty(value = "行业类别名称", dataType = "String", required = false)
	private String name;
	
	@ApiModelProperty(value = "行业类别编号", dataType = "String", required = false)
	private String code;
	
	@ApiModelProperty(value = "行业类别描述", dataType = "String", required = false)
	private String descr;
	
	@ApiModelProperty(value = "行业类别排序号", dataType = "Long", required = false)
	@NotNull(message = "行业类别排序号不能为空！")
	private Integer sortNo;
	
	@ApiModelProperty(value = "父Id", dataType = "Long", required = false)
	private Long pid;
	
}
