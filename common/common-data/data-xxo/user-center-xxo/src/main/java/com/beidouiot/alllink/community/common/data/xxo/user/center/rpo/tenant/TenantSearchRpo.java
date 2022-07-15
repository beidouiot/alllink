package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.tenant;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SearchBaseRpo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Api(tags="租户查询请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TenantSearchRpo extends SearchBaseRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1688469253651106998L;

	@ApiModelProperty(value = "租户名称, length = 100", dataType = "String", required = true)
	@NotBlank(message = "租户名称不能为空！")
	@Length(min=1, max=100, message="租户名称长度不能超过100个字符")
	private String name;
	
	@ApiModelProperty(value = "租户详细地址, length = 500", dataType = "String", required = false)
	@Length(min=0, max=500, message="租户详细地址长度不能超过500个字符")
	private String addr;
	
	@ApiModelProperty(value = "租户描述, length = 200", dataType = "String", required = false)
	@Length(min=0, max=200, message="租户描述长度不能超过200个字符")
	private String descr;
	
	@ApiModelProperty(value = "联系人姓名, length = 50", dataType = "String", required = false)
	@Length(min=0, max=50, message="联系人姓名长度不能超过50个字符")
	private String linkman;
	
	@ApiModelProperty(value = "联系人邮箱, length = 100", dataType = "String", required = false)
	@Length(min=0, max=100, message="联系人姓名长度不能超过100个字符")
	@Email(message = "邮箱格式不正确！")
	private String email;
	
	@ApiModelProperty(value = "电话, length = 20", dataType = "String", required = false)
	@Length(min=0, max=20, message="电话长度不能超过20个字符")
	private String phone;
	
	@ApiModelProperty(value = "邮编, length = 6", dataType = "String", required = false)
	@Length(min=0, max=6, message="邮编长度不能超过6个字符")
	private String zipCode;
	
	@ApiModelProperty(value = "所属行业, length = 6", dataType = "Long", required = true)
	@NotNull(message = "所属行业不能为空！")
	private Long industryId;
	
	@ApiModelProperty(value = "所属行业, length = 1000", dataType = "String", required = false)
	private String city;
	
	@ApiModelProperty(value = "租户状态, length = 1", dataType = "Boolean", required = true)
	@NotNull(message = "租户状态不能为空！")
	private Boolean status;
	
}
