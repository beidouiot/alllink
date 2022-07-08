package com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(tags="修改产品属性物模型请求信息集")
@Data
public class ProductPropertyModelListUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6739738266196686904L;

	@ApiModelProperty(value = "产品ID", dataType = "Long", required = true)
	@NotNull(message = "产品ID不能为空！")
	private Long productId;
	
	@ApiModelProperty(value = "产品属性物模型集", dataType = "Array", required = true)
	@NotNull(message = "产品属性物模型不能为空！")
	private List<ProductPropertyModelUpdateRpo> productPropertyModelList;
	
}
