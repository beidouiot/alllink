package com.beidouiot.alllink.community.common.data.xxo.product.rpo.product;


import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SearchBaseRpo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 查询产品请求
* @author longww
* @date 2021年12月23日
*/
@Api(tags="查询产品请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3064694505993091340L;

	@ApiModelProperty(value = "产品中文名称", dataType = "String", required = false)
	private String chineseName;
	
	@ApiModelProperty(value = "产品名称", dataType = "String", required = false)
	private String name;
	
	@ApiModelProperty(value = "产品设备类型", dataType = "Integer", required = false)
	private Integer productDeviceType;
	
	@ApiModelProperty(value = "产品接入方式", dataType = "Integer", required = false)
	private Integer productAccessMode;
	
	@ApiModelProperty(value = "通信协议", dataType = "String", required = false)
	private String communicationProtocol;
	
	@ApiModelProperty(value = "组网方式", dataType = "String", required = false)
	private String networkingProtocol;
	
	@ApiModelProperty(value = "产品描述", dataType = "String", required = false)
	private String descr;
	
	@ApiModelProperty(value = "是否复制标准物模型", dataType = "Boolean", required = false)
	private Boolean copyFlag;
	
	@ApiModelProperty(value = "所属产品类别id", dataType = "Long", required = false)
	private Long productTypeId;
}
