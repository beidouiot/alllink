package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import java.util.List;

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
* @Description 标准物模型DTO
* @author longww
* @date 2021年12月23日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class StandardModelDto extends BaseDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8830826149300335621L;
	
	@NotNull(message = "产品类别id不能为空！")
	private Long productTypeId;
	
	private List<StandardPropertyModelDto> standardPropertyModelList;
	
	private List<StandardEventModelDto> standardEventModelDtoList;
	
	private List<StandardCommandModelDto> standardCommandModelDtoList;

}
