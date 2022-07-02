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
* @Description （产品类别）标准物模型
* @author longww
* @date 2021年12月22日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
//@Table(name = EntityConstants.STANDARD_MODEL)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class StandardModel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7008136894686824045L;
	
//	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_NAME_COLUMN_DEFINITION)
	private String name;
	
//	@Column(name = EntityConstants.CODE, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_CODE_COLUMN_DEFINITION)
	private String code;
	
//	@Column(name = EntityConstants.STANDARD_MODEL_FUNC_TYPE, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_FUNC_TYPE_COLUMN_DEFINITION)
	private Integer funcType;
	
	@Column(name = EntityConstants.STANDARD_MODEL_DATA_TYPE, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_DATA_TYPE_COLUMN_DEFINITION)
	private String dataType;
	
	@Column(name = EntityConstants.STANDARD_MODEL_ACCESS_TYPE, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_ACCESS_TYPE_COLUMN_DEFINITION)
	private Integer accessType;
	
	@Column(name = EntityConstants.STATUS, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_STATUS_COLUMN_DEFINITION)
	private Boolean status;
	
	@Column(name = EntityConstants.PRODUCT_TYPE_ID, nullable = false, columnDefinition = EntityConstants.PRODUCT_TYPE_ID_COLUMN_DEFINITION)
	private Long productTypeId;
}
