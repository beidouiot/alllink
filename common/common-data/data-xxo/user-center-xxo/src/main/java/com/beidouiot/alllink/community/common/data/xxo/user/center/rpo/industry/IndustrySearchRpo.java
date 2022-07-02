package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.industry;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SearchBaseRpo;
import com.beidouiot.alllink.community.common.data.xxo.validator.CheckNumber;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description TODO
* @author longww
* @date 2021年4月11日
*/
@Api(tags="查询行业类型请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndustrySearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8162873424052851026L;

	@ApiModelProperty(value = "行业名称, length = 100", dataType = "String", required = true)
	@NotBlank(message = "行业名称不能为空！")
	@Length(min=1, max=100, message="行业名称长度不能超过100个字符")
	private String name;
	
	@ApiModelProperty(value = "行业编号, length = 100", dataType = "String", required = true)
	@NotBlank(message = "行业编号不能为空！")
	@Length(min=1, max=100, message="行业编号不能超过100个字符")
	private String code;
	
	@ApiModelProperty(value = "行业描述, length = 200", dataType = "String", required = false)
	@Length(min=0, max=200, message="行业描述长度不能超过200个字符")
	private String description;
	
	@ApiModelProperty(value = "排序号", dataType = "Long", required = true)
	@NotNull(message = "排序号不能为空！")
	@Min(value = 0, message= "排序号必须是最小值为0的正整数")
	@CheckNumber(message = "排序号必须为正整数")
	private Integer sortNo;
	
	@ApiModelProperty(value = "父Id", dataType = "Long", required = false)
	private Long pid;
	
}
