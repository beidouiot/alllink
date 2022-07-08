package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import com.beidouiot.alllink.community.common.base.enums.ProductDeviceTypeEnum;
import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 产品DTO
* @author longww
* @date 2021年12月23日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class ProductDto extends BaseDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3992893409028466279L;
	
	private String chineseName;
	
	private String name;
	
	private Integer productDeviceType;
	
	private Integer productAccessMode;
	
	private String communicationProtocol;
	
	private String networkingProtocol;
	
	private String descr;
	
	private Boolean copyFlag;
	
	private Long productTypeId;
	
	private String strProductDeviceType;
	
	public String getStrProductDeviceType() {
		strProductDeviceType =  ProductDeviceTypeEnum.getMsg(productDeviceType);
		return strProductDeviceType;
	}
	
	private String strId;
	
	public String getStrId() {
		strId = id == null || id == 0 ? "" : String.valueOf(id);
		return strId;
	}
	
	private String strProductTypeId;
	
	public String getStrProductTypeId() {
		strProductTypeId = productTypeId == null || productTypeId == 0 ? "" : String.valueOf(productTypeId);
		return strProductTypeId;
	}

}
