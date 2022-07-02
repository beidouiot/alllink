package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.park;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.beidouiot.alllink.community.common.data.xxo.validator.CheckNumber;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 修改园区请求
* @author longww
* @date 2021年4月11日
*/
@Api(tags="修改园区/小区请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParkUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8320324019582683969L;

	@ApiModelProperty(value = "园区Id", dataType = "Long", required = true)
	@NotNull(message = "园区Id不能为空！")
	@CheckNumber(message = "园区Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "园区/小区名称, length = 200", dataType = "String", required = true)
	@NotBlank(message = "名称不能为空！")
	@Length(min=1, max=200, message="园区/小区名称长度不能超过200个字符")
	private String name;
	
	@ApiModelProperty(value = "节点类型, length = 20", dataType = "String", required = true)
	@NotBlank(message = "节点类型不能为空！")
	private String type;
	
	@ApiModelProperty(value = "排序号", dataType = "Long", required = true)
	@NotNull(message = "排序号不能为空！")
	@Min(value = 0, message= "排序号必须是最小值为0的正整数")
	@CheckNumber(message = "排序号必须为正整数")
	private Integer sortNo;
	
	@ApiModelProperty(value = "节点描述, length = 200", dataType = "String", required = false)
	@Length(min=0, max=200, message="节点描述长度不能超过200个字符")
	private String descr;
}
