package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 行业类别DTO
* @author longww
* @date 2021年12月23日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class IndustryTypeUpdateDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -339066424863693313L;

	private Long id;
	
	private String name;
	
	private String code;
	
	private String descr;
	
	private Integer sortNo;
	
	private Long pid;
	
	private String updatedBy;

}
