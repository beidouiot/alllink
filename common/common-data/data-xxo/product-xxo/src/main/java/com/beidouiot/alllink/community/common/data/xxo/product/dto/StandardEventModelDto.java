package com.beidouiot.alllink.community.common.data.xxo.product.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月9日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class StandardEventModelDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7454054985166993112L;
	
	@NotBlank(message = "事件名称不能为空！")
	private String name;
	
	@NotBlank(message = "事件标识符不能为空！")
	private String code;
	
	@NotNull(message = "事件类型不能为空！")
	private Integer eventType;
	
	private String params;
	
	private String remark;
	
	@NotNull(message = "发布状态不能为空！")
	private Boolean status;
	
	@NotNull(message = "产品类别id不能为空！")
	private Long productTypeId;

}
