package com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
*
* @Description TODO
* @author Administrator
* @date 2022年6月26日
*/
@Api(tags="增加标准指令物模型请求信息集")
@Data
public class StandardCommandModelListUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540718305462211761L;

	@ApiModelProperty(value = "产品类型ID", dataType = "Long", required = true)
	@NotNull(message = "产品类型ID不能为空！")
	private Long productTypeId;
	
	@ApiModelProperty(value = "标准指令物模型集", dataType = "Array", required = true)
	@NotNull(message = "标准指令物模型不能为空！")
	private List<StandardCommandModelUpdateRpo> standardCommandModelList;

}
