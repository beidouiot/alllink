package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.customer;

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
* @Description 修改客户请求信息
* @author longww
* @date 2021年4月11日
*/
@Api(tags="修改客户请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8132073454500950856L;

	@ApiModelProperty(value = "客户Id", dataType = "Long", required = true)
	@NotNull(message = "客户Id不能为空！")
	@CheckNumber(message = "客户Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "客户名称", dataType = "String", required = true)
	@NotBlank(message = "客户名不能为空！")
	private String name;
	
	@ApiModelProperty(value = "客户描述", dataType = "String", required = false)
	private String descr;
	
	@ApiModelProperty(value = "客户所在城市", dataType = "String", required = false)
	private String city;
	
	@ApiModelProperty(value = "客户地址", dataType = "String", required = false)
	private String addr;
	
	@ApiModelProperty(value = "客户邮箱", dataType = "String", required = false)
	private String email;
	
	@ApiModelProperty(value = "客户电话", dataType = "String", required = false)
	private String phone;
	
	@ApiModelProperty(value = "邮编", dataType = "String", required = false)
	private String zipCode;
	
	@ApiModelProperty(value = "所属租户id", dataType = "String", required = false)
	@CheckNumber(message = "id必须为正整数")
	private Long tenantId;
	
	@ApiModelProperty(value = "系统编号", dataType = "String", required = false)
	private String systemCode;
}
