package com.beidouiot.alllink.community.common.data.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;
import com.beidouiot.alllink.community.common.data.entity.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
*
* @Description 产品物模型历史版本
* @author longww
* @date 2021年12月22日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.PRODUCT_MODEL_VERSION)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class ProductModelVersion extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3133896227242952305L;
	
	@Type(type = "json")
	@Column(name = EntityConstants.PRODUCT_MODEL_VERSION_MODEL_CONTENT, nullable = false, columnDefinition = EntityConstants.PRODUCT_MODEL_VERSION_MODEL_CONTENT_COLUMN_DEFINITION)
	private ProductModel productModel;
	
	@Column(name = EntityConstants.PRODUCT_MODEL_VERSION_USE_FLAG, nullable = false, columnDefinition = EntityConstants.PRODUCT_MODEL_VERSION_USE_FLAG_COLUMN_DEFINITION)
	private Boolean userFlag;
	
	@Column(name = EntityConstants.PRODUCT_MODEL_VERSION_VERSION_NUMBER, nullable = false, columnDefinition = EntityConstants.PRODUCT_MODEL_VERSION_VERSION_NUMBER_COLUMN_DEFINITION)
	private Long versionNumber;
	
	@Column(name = EntityConstants.PRODUCT_ID, nullable = false, columnDefinition = EntityConstants.PRODUCT_ID_COLUMN_DEFINITION)
	private Long productId;

}
