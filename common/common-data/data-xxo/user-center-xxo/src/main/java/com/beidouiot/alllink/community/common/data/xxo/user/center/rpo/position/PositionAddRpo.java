package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.position;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 增加职位请求信息
* @author longww
* @date 2021年5月9日
*/
@Api(tags="增加职位请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PositionAddRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4367315411965676017L;

	@ApiModelProperty(value = "职位名称, length = 50", dataType = "String", required = true)
	@NotBlank(message = "职位名称不能为空！")
	@Length(min=1, max=50, message="职位名称长度不能超过50个字符")
	private String name;
	
	@ApiModelProperty(value = "职位编号, length = 10", dataType = "String", required = true)
	@NotBlank(message = "职位编号不能为空！")
	@Length(min=1, max=10, message="职位编号长度不能超过10个字符")
	private String code;
	
	@ApiModelProperty(value = "职位描述, length = 200", dataType = "String", required = false)
	@Length(min=0, max=200, message="职位描述长度不能超过200个字符")
	private String descr;
}
