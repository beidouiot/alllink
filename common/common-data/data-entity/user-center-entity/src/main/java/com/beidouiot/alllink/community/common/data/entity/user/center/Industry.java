package com.beidouiot.alllink.community.common.data.entity.user.center;

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
 * @Description  行业
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.INDUSTRY)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class Industry extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2452485990914147390L;

	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.INDUSTRY_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.CODE, nullable = false, columnDefinition = EntityConstants.INDUSTRY_CODE_COLUMN_DEFINITION)
	private String code;

	@Column(name = EntityConstants.DESCR, nullable = true, columnDefinition = EntityConstants.INDUSTRY_DESCRIPTION_COLUMN_DEFINITION)
	private String description;

	@Column(name = EntityConstants.SORT_NO, nullable = false, columnDefinition = EntityConstants.SORT_NO_COLUMN_DEFINITION)
	private Integer sortNo;

	@Column(name = EntityConstants.PID, nullable = true, columnDefinition = EntityConstants.PID_COLUMN_DEFINITION)
	private Long pid;
}
