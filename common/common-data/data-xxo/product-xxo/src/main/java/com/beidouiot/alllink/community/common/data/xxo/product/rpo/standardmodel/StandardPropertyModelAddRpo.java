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
@Api(tags="增加标准属性物模型请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardPropertyModelAddRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5048975944596272611L;
	
	@ApiModelProperty(value = "属性名称", dataType = "String", required = true)
	@NotBlank(message = "属性名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "属性标识符", dataType = "String", required = true)
	@NotBlank(message = "属性标识符不能为空！")
	private String code;
	
	@ApiModelProperty(value = "数据类型", dataType = "String", required = true)
	@NotBlank(message = "数据类型不能为空！")
	private String dataType;
	
	@ApiModelProperty(value = "数据类型数据定义", dataType = "String", required = false)
	private String dataSpecs;
	
	@ApiModelProperty(value = "访问类型", dataType = "Integer", required = true)
	@NotNull(message = "访问类型不能为空！")
	private Integer accessType;
	
	@ApiModelProperty(value = "产品类别id", dataType = "Long", required = true)
	@NotNull(message = "产品类别id不能为空！")
	@CheckNumber(message = "产品类别id必须为正整数" )
	private Long productTypeId;

}
