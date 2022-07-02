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
* @Description （产品类别）标准指令物模型参数
* @author longww
* @date 2021年12月26日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.STANDARD_COMMAND_MODEL_PARAM)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class StandardCommandModelParam extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3477621360702291766L;

	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_PARAM_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.CODE, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_PARAM_CODE_COLUMN_DEFINITION)
	private String code;
	
	@Column(name = EntityConstants.STANDARD_MODEL_DATA_TYPE, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_DATA_TYPE_COLUMN_DEFINITION)
	private String dataType;
	
	@Column(name = EntityConstants.STANDARD_MODEL_DATA_SPECS, nullable = true, columnDefinition = EntityConstants.STANDARD_MODEL_DATA_SPECS_COLUMN_DEFINITION)
	private String dataSpecs;
	
	@Column(name = EntityConstants.STATUS, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_STATUS_COLUMN_DEFINITION)
	private Boolean status;
	
	@Column(name = EntityConstants.STANDARD_MODEL_PARAM_DIRECTION, nullable = false, columnDefinition = EntityConstants.STANDARD_MODEL_PARAM_DIRECTION_COLUMN_DEFINITION)
	private Integer direction;
	
	@Column(name = EntityConstants.STANDARD_COMMAND_MODEL_ID, nullable = false, columnDefinition = EntityConstants.STANDARD_COMMAND_MODEL_ID_COLUMN_DEFINITION)
	private Long standardCommandModelId;
}
