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
public class StandardCommandModelUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3590447334672947752L;

	private Long id;
	
	private String name;
	
	private Integer callMode;
	
	private String params;
	
	private String remark;

	private String updatedBy;
	
	private Long productTypeId;
	
	private String strId;
	
	public String getStrId() {
		strId = id == null || id == 0 ? "" : String.valueOf(id);
		return strId;
	}
	
	private String strProductTypeId;
	
	public String getStrProductTypeId() {
		strProductTypeId = String.valueOf(productTypeId);
		return strProductTypeId;
	}

}
