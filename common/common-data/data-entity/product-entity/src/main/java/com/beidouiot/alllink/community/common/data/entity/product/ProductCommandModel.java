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
* @Description 产品指令物模型
* @author longww
* @date 2021年12月26日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.PRODUCT_COMMAND_MODEL)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class ProductCommandModel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8436663752585288331L;

	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.STANDARD_COMMOND_MODEL_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.CODE, nullable = false, columnDefinition = EntityConstants.STANDARD_COMMOND_MODEL_CODE_COLUMN_DEFINITION)
	private String code;
	
	@Column(name = EntityConstants.STANDARD_COMMOND_MODEL_CALL_MODE, nullable = false, columnDefinition = EntityConstants.STANDARD_COMMOND_MODEL_CALL_MODE_COLUMN_DEFINITION)
	private Integer callMode;
	
	@Column(name = EntityConstants.STANDARD_COMMOND_MODEL_PARAMS, nullable = true, columnDefinition = EntityConstants.STANDARD_COMMOND_MODEL_PARAMS_COLUMN_DEFINITION)
	private String params;
	
	@Column(name = EntityConstants.STANDARD_MODEL_REMARK, nullable = true, columnDefinition = EntityConstants.STANDARD_MODEL_REMARK_COLUMN_DEFINITION)
	private String remark;
	
	@Column(name = EntityConstants.STATUS, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_STATUS_COLUMN_DEFINITION)
	private Boolean status;
	
	@Column(name = EntityConstants.PRODUCT_ID, nullable = false, columnDefinition = EntityConstants.PRODUCT_ID_COLUMN_DEFINITION)
	private Long productId;
}
