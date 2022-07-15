package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.department;


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
* @Description TODO
* @author longww
* @date 2021年12月23日
*/
@Api(tags="查询组织/部门请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8616147872491085569L;

	@ApiModelProperty(value = "组织/部门名称", dataType = "String")
	private String name;
	
	@ApiModelProperty(value = "组织/部门编号", dataType = "String")
	private String code;
	
	@ApiModelProperty(value = "组织/部门描述", dataType = "String")
	private String descr;
	
	@ApiModelProperty(value = "排序号", dataType = "Integer")
	private Integer sortNo;
	
	@ApiModelProperty(value = "组织/部门类型", dataType = "String")
	private String type;
	
	@ApiModelProperty(value = "父部门Id", dataType = "Long")
	private Long pid;
	
	@ApiModelProperty(value = "租户Id", dataType = "Long")
	private Long tenantId;
	
}
