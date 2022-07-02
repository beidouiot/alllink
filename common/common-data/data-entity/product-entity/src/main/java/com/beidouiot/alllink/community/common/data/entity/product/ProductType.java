package com.beidouiot.alllink.community.common.data.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;
import com.beidouiot.alllink.community.common.data.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 *
 * @Description 产品类别
 * @author longww
 * @date 2021年11月17日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.PRODUCT_TYPE)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class ProductType extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5004507735324703067L;

	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.PRODUCT_TYPE_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.CODE, nullable = false, columnDefinition = EntityConstants.PRODUCT_TYPE_CODE_COLUMN_DEFINITION)
	private String code;
	
	@Column(name = EntityConstants.DESCR, nullable = true, columnDefinition = EntityConstants.PRODUCT_TYPE_DESCR_COLUMN_DEFINITION)
	private String descr;

}
