package com.beidouiot.alllink.community.common.data.xxo.product.rpo.product;

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
* @Description 修改产品请求信息
* @author longww
* @date 2021年12月23日
*/
@Api(tags="修改产品请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6122626870837610412L;
	
	@ApiModelProperty(value = "产品Id", dataType = "Long", required = true)
	@NotNull(message = "产品Id不能为空！")
	@CheckNumber(message = "产品Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "产品中文名称", dataType = "String", required = false)
	private String chineseName;
	
	@ApiModelProperty(value = "产品名称", dataType = "String", required = true)
	@NotBlank(message = "产品名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "产品设备类型", dataType = "Integer", required = true)
	@NotNull(message = "产品设备类型不能为空！")
	private Integer productDeviceType;
	
	@ApiModelProperty(value = "产品接入方式", dataType = "Integer", required = false)
	private Integer productAccessMode;
	
	@ApiModelProperty(value = "通信协议", dataType = "String", required = true)
	@NotBlank(message = "通信协议不能为空！")
	private String communicationProtocol;
	
	@ApiModelProperty(value = "组网方式", dataType = "String", required = false)
	private String networkingProtocol;
	
	@ApiModelProperty(value = "产品描述", dataType = "String", required = false)
	private String descr;
	
	@ApiModelProperty(value = "是否复制标准物模型", dataType = "Boolean", required = true)
	@NotNull(message = "是否复制标准物模型不能为空！")
	private Boolean copyFlag;
	
	@ApiModelProperty(value = "所属产品类别id", dataType = "Long", required = true)
	@NotNull(message = "所属产品类别id不能为空！")
	private Long productTypeId;

}
