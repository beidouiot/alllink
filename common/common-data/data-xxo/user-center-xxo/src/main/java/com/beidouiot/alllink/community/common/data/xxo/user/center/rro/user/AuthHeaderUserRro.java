package com.beidouiot.alllink.community.common.data.xxo.user.center.rro.user;

import com.beidouiot.alllink.community.common.data.xxo.rro.RRO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Api(tags="认证鉴权后返回用户简单结果信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AuthHeaderUserRro extends RRO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7573862657650531708L;

	@ApiModelProperty(value = "用户Id", dataType = "Long")
	private Long id;
	
	@ApiModelProperty(value = "用户名(账号)", dataType = "String")
	private String username;
	
	@ApiModelProperty(value = "用户姓名", dataType = "String")
	private String name;
}
