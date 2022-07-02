package com.beidouiot.alllink.community.common.data.entity.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;

import lombok.Data;
import lombok.ToString;

/**
*
* @Description 联合主键
* @author longww
* @date 2022年1月4日
*/
@Data
@Embeddable
@ToString
public class IndustryTypeProductTypeKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3073725231902988099L;

	@Column(name = EntityConstants.INDUSTRY_TYPE_ID, nullable = false, columnDefinition = EntityConstants.INDUSTRY_TYPE_ID_COLUMN_DEFINITION)
	private Long industryTypeId;
	
	@Column(name = EntityConstants.PRODUCT_TYPE_ID, nullable = false, columnDefinition = EntityConstants.PRODUCT_TYPE_ID_COLUMN_DEFINITION)
	private Long productTypeId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((industryTypeId == null) ? 0 : industryTypeId.hashCode());
		result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndustryTypeProductTypeKey other = (IndustryTypeProductTypeKey) obj;
		if (industryTypeId == null) {
			if (other.industryTypeId != null)
				return false;
		} else if (!industryTypeId.equals(other.industryTypeId))
			return false;
		if (productTypeId == null) {
			if (other.productTypeId != null)
				return false;
		} else if (!productTypeId.equals(other.productTypeId))
			return false;
		return true;
	}
	
	
}
