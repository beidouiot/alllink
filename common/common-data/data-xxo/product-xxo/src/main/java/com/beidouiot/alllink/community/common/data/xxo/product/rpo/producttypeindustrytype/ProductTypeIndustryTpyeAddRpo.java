package com.beidouiot.alllink.community.common.data.xxo.product.rpo.producttypeindustrytype;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
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
* @Description TODO
* @author longww
* @date 2022年2月16日
*/
@Api(tags="产品类别配置行业类别信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductTypeIndustryTpyeAddRpo implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 4926067226235422882L;

	@ApiModelProperty(value = "角色Id", dataType = "Long", required = true)
	@NotNull(message = "产品类别Id不能为空！")
	@Min( value = 0)
	@CheckNumber( message = "产品类别Id为正整数")
	private Long productTypeId;
	
	@ApiModelProperty(value = "行业类别Id集", dataType = "List", required = true)
	private List<Long> industryTypeIds;
	
}
