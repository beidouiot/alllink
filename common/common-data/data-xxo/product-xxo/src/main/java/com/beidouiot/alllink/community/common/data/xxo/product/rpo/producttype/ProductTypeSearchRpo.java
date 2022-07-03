package com.beidouiot.alllink.community.common.data.xxo.product.rpo.producttype;

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
* @Description 查询产品类别请求信息
* @author longww
* @date 2021年12月23日
*/
@Api(tags="查询产品类别请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductTypeSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1085740576897967859L;

	@ApiModelProperty(value = "产品类别名称", dataType = "String", required = false)
	private String name;
	
	@ApiModelProperty(value = "产品类别编号", dataType = "String", required = false)
	private String code;
	
	@ApiModelProperty(value = "产品类别描述", dataType = "String", required = false)
	private String descr;
	
}
