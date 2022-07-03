package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 产品类别DTO
* @author longww
* @date 2021年12月23日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class ProductTypeUpdateDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6360797844713139638L;
	
	private Long id;
	
	private String name;
	
	private String descr;
	
	private String updatedBy;

}
