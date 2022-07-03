package com.beidouiot.alllink.community.common.data.xxo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.beidouiot.alllink.community.common.data.xxo.validator.CheckNumber;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月15日
*/
@Api(tags="ID请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7762260329192178628L;

	@ApiModelProperty(value = "ID", dataType = "Long", required = true)
	@NotNull(message = "id不能为空！")
	@CheckNumber(message = "id必须为正整数")
	private Long id;
	
}
