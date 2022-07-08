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
public class ProductPropertyModelUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7631692862854778352L;

	private Long id;
	
	private String name;
	
	private String dataType;
	
	private String dataSpecs;
	
	private Integer accessType;
	
	private Boolean status;
	
	private String updatedBy;
	
	private String strId;
	
	public String getStrId() {
		strId = id == null || id == 0 ? "" : String.valueOf(id);
		return strId;
	}
	
}
