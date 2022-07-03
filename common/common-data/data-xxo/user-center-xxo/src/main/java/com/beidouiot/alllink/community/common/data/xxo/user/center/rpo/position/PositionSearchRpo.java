package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.position;


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
* @Description 查询职位请求信息
* @author longww
* @date 2021年5月9日
*/
@Api(tags="查询职位请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class PositionSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5900892460718291706L;

	@ApiModelProperty(value = "职位名称", dataType = "String")
	private String name;
	
	@ApiModelProperty(value = "职位编号", dataType = "String")
	private String code;
	
	@ApiModelProperty(value = "职位描述", dataType = "String")
	private String descr;
}
