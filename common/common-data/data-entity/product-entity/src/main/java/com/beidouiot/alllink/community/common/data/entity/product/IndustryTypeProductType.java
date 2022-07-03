package com.beidouiot.alllink.community.common.data.entity.product;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
*
* @Description 行业类别、产品类别
* @author longww
* @date 2022年1月4日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.INDUSTRY_TYPE_PRODUCT_TYPE)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString
public class IndustryTypeProductType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205399189245191598L;
	
	
	@EmbeddedId
    private IndustryTypeProductTypeKey id;
}
