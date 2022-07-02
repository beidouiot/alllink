package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import java.io.Serializable;

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
public class ProductUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2832467822384002932L;

	private Long id;
	
	private String chineseName;
	
	private String name;
	
	private Integer productDeviceType;
	
	private Integer productAccessMode;
	
	private String communicationProtocol;
	
	private String networkingProtocol;
	
	private String descr;
	
	private Boolean copyFlag;
	
	private String updatedBy;

}
