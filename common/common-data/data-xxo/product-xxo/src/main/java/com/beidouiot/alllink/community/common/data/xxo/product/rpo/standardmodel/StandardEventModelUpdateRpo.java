package com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel;

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
* @Description TODO
* @author longww
* @date 2022年2月11日
*/
@Api(tags="修改标准事件物模型请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardEventModelUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3028651763868935342L;

	@ApiModelProperty(value = "标准事件物模型Id", dataType = "Long", required = true)
	@NotNull(message = "标准事件物模型Id不能为空！")
	@CheckNumber(message = "标准事件物模型Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "事件名称", dataType = "String", required = true)
	@NotBlank(message = "事件名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "事件类型", dataType = "Integer", required = true)
	@NotNull(message = "事件类型不能为空！")
	private Integer eventType;
	
	@ApiModelProperty(value = "物模型参数集（物模型参数ID集），多ID以逗号隔开", dataType = "String", required = false)
	private String params;
	
	@ApiModelProperty(value = "备注", dataType = "String", required = false)
	private String remark;
	
	@ApiModelProperty(value = "产品类别Id", dataType = "Long", required = true)
	@NotNull(message = "产品类别Id不能为空！")
	@CheckNumber(message = "产品类别Id必须为正整数" )
	private Long productTypeId;

}
