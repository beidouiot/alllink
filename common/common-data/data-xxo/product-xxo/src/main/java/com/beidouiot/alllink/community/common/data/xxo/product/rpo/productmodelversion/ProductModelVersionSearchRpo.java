package com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodelversion;


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
* @Description 查询产品物模型历史版本请求信息
* @author longww
* @date 2021年12月23日
*/
@Api(tags="查询产品物模型历史版本请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductModelVersionSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6952973050585683698L;

	@ApiModelProperty(value = "物模型内容", dataType = "String", required = false)
	private String modelContent;
	
	@ApiModelProperty(value = "当前使用标志", dataType = "Boolean", required = false)
	private Boolean userFlag;
	
	@ApiModelProperty(value = "版本号", dataType = "Integer", required = false)
	private Integer versionNumber;
	
	@ApiModelProperty(value = "产品id", dataType = "Long", required = false)
	private Long productId;
	
}
