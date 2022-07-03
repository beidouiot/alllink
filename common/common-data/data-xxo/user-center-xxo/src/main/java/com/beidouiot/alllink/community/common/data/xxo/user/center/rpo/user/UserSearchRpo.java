package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user;

import java.io.Serializable;

import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SearchBaseRpo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Api(tags="增加用户请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class UserSearchRpo extends SearchBaseRpo implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -759027476810556306L;

	@ApiModelProperty(value = "用户名", dataType = "String", required = false)
	private String username;
	
	@ApiModelProperty(value = "用户邮箱", dataType = "String", required = false)
	private String email;
	
	@ApiModelProperty(value = "手机号", dataType = "String", required = false)
	private String mobile;
	
	@ApiModelProperty(value = "姓名", dataType = "String", required = false)
	private String name;
	
	@ApiModelProperty(value = "昵称", dataType = "String", required = false)
	private String nickname;
	
	@ApiModelProperty(value = "性别", dataType = "Integer", required = false)
	private Integer sex;
	
	@ApiModelProperty(value = "状态", dataType = "Boolean", required = false)
	private Boolean status;
	
	@ApiModelProperty(value = "用户类型", dataType = "String", required = false)
	private String userType;
	
	@ApiModelProperty(value = "微信号", dataType = "String", required = false)
	private String weixin;
	
	@ApiModelProperty(value = "QQ号", dataType = "String", required = false)
	private String qqNo;
	
	@ApiModelProperty(value = "用户描述", dataType = "String", required = false)
	private String descr;
	
	@ApiModelProperty(value = "用户工号/学号等", dataType = "String", required = false)
	private String code;
	
	@ApiModelProperty(value = "证件号", dataType = "String", required = false)
	private String identityNo;
	
	@ApiModelProperty(value = "证件类型，枚举（身份证、军官证、士官证、士兵证、台胞证、护照、绿卡）", dataType = "String", required = false)
	private String identityType;
	
	@ApiModelProperty(value = "所属租户（租户ID）", dataType = "Long", required = false)
	private Long tenantId;
	
	@ApiModelProperty(value = "所属客户（客户ID）", dataType = "Long", required = false)
	private Long customerId;
	
	@ApiModelProperty(value = "所属园区/小区（园区/小区ID）", dataType = "Long", required = false)
	private Long parkId;
}
