package com.beidouiot.alllink.community.common.data.xxo.product.rpo.productmodel;

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
* @Description TODO
* @author longww
* @date 2022年2月11日
*/
@Api(tags="查询产品事件物模型请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductEventModelSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2595378324484614217L;
	
	@ApiModelProperty(value = "事件名称", dataType = "String", required = false)
	private String name;
	
	@ApiModelProperty(value = "事件标识符", dataType = "String", required = false)
	private String code;
	
	@ApiModelProperty(value = "事件类型", dataType = "Integer", required = false)
	private Integer eventType;
	
	@ApiModelProperty(value = "物模型参数集（物模型参数ID集），多ID以逗号隔开", dataType = "String", required = false)
	private String params;
	
	@ApiModelProperty(value = "备注", dataType = "String", required = false)
	private String remark;
	
	@ApiModelProperty(value = "发布状态", dataType = "Boolean", required = false)
	private Boolean status;
	
	@ApiModelProperty(value = "产品id", dataType = "Boolean", required = false)
	private Long productId;
	
}
