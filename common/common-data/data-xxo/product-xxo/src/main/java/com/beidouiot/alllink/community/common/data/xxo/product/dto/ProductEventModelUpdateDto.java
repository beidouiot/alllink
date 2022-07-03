package com.beidouiot.alllink.community.common.data.xxo.product.dto;


import java.io.Serializable;

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
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class ProductEventModelUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4055237863288842194L;

	private Long id;
	
	private String name;
	
	private Integer eventType;
	
	private String params;
	
	private String remark;
	
	private Boolean status;

	private String updatedBy;
	
}
