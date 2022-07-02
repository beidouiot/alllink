package com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel;


import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SearchBaseRpo;

import io.swagger.annotations.ApiModelProperty;

/**
*
* @Description TODO
* @author longww
* @date 2021年12月23日
*/

public class StandardModelSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6459694261711263568L;

	@ApiModelProperty(value = "属性/事件/命令名称", dataType = "String", required = false)
	private String name;
	
	@ApiModelProperty(value = "属性/事件/命令标识符", dataType = "String", required = false)
	private String code;
	
	@ApiModelProperty(value = "标准物模型类型", dataType = "Integer", required = false)
	private Integer funcType;
	
	@ApiModelProperty(value = "数据类型", dataType = "String", required = false)
	private String dataType;
	
	@ApiModelProperty(value = "访问类型", dataType = "Integer", required = false)
	private Integer accessType;
	
	@ApiModelProperty(value = "发布状态", dataType = "Boolean", required = false)
	private Boolean status;
	
	@ApiModelProperty(value = "产品类别", dataType = "Long", required = false)
	private Long productTypeId;
}
