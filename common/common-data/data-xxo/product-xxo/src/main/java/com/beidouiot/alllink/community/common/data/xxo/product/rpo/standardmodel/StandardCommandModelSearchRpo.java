package com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel;

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
@Api(tags="查询标准指令物模型请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardCommandModelSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8824522197711247727L;

	@ApiModelProperty(value = "指令名称", dataType = "String", required = false)
	private String name;
	
	@ApiModelProperty(value = "指令标识符", dataType = "String", required = false)
	private String code;
	
	@ApiModelProperty(value = "调用方式", dataType = "Integer", required = false)
	private Integer callMode;
	
	@ApiModelProperty(value = "物模型参数集（物模型参数ID集），多ID以逗号隔开", dataType = "String", required = false)
	private String params;
	
	@ApiModelProperty(value = "备注", dataType = "String", required = false)
	private String remark;
	
	@ApiModelProperty(value = "发布状态", dataType = "Boolean", required = false)
	private Boolean status;
	
	@ApiModelProperty(value = "产品类别id", dataType = "Long", required = false)
	private Long productTypeId;
	
}
