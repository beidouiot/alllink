package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.department;

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
* @Description TODO
* @author longww
* @date 2021年12月23日
*/
@Api(tags="增加组织/部门请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentAddRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5267107169605679450L;

	@ApiModelProperty(value = "组织/部门名称", dataType = "String", required = true)
	@NotBlank(message = "组织/部门名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "组织/部门编号", dataType = "String", required = true)
	@NotBlank(message = "组织/部门编号不能为空！")
	private String code;
	
	@ApiModelProperty(value = "组织/部门描述", dataType = "String")
	private String descr;
	
	@ApiModelProperty(value = "排序号", dataType = "Integer", required = true)
	@NotNull(message = "排序号不能为空")
	private Integer sortNo;
	
	@ApiModelProperty(value = "组织/部门类型", dataType = "String", required = true)
	@NotBlank(message = "组织/部门类型不能为空！")
	private String type;
	
	@ApiModelProperty(value = "父部门Id", dataType = "Long")
	private Long pid;
	
	@ApiModelProperty(value = "租户Id", dataType = "Long")
	private Long tenantId;
	
	@ApiModelProperty(value = "客户Id", dataType = "Long")
	private Long customerId;
	
	@ApiModelProperty(value = "系统编号", dataType = "String")
	private String systemCode;
}
