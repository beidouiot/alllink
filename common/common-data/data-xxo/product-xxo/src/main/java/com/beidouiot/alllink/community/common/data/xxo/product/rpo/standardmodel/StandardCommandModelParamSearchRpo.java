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
@Api(tags="查询标准指令物模型参数请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardCommandModelParamSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1945669544735068669L;

	@ApiModelProperty(value = "参数名称", dataType = "String", required = false)
	private String name;
	
	@ApiModelProperty(value = "参数标识符", dataType = "String", required = false)
	private String code;
	
	@ApiModelProperty(value = "数据类型", dataType = "String", required = false)
	private String dataType;
	
	@ApiModelProperty(value = "数据类型数据定义", dataType = "String", required = false)
	private String dataSpecs;
	
	@ApiModelProperty(value = "发布状态", dataType = "Boolean", required = false)
	private Boolean status;
	
	@ApiModelProperty(value = "参数方向", dataType = "Integer", required = false)
	private Integer direction;
	
	@ApiModelProperty(value = "标准指令物模型id", dataType = "Long", required = false)
	private Long standardCommandModelId;
	
}
