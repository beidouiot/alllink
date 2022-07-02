package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description TODO
* @author longww
* @date 2022年1月24日
*/
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductEventModelJsonUpdateDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 526892441835806314L;

	private Long id;
	
	private String name;
	
	private Integer eventType;
	
	private List<ProductEventModelParamJsonDto> params;
	
	private String remark;
	
	private String updatedBy;
}
