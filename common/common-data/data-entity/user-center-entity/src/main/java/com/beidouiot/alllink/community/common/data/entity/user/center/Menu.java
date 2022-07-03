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
 * @Description 菜单
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.MENU)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class Menu extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -895894173844554903L;
	
	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.MENU_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.CODE, nullable = false, columnDefinition = EntityConstants.MENU_CODE_COLUMN_DEFINITION)
	private String code;

	@Column(name = EntityConstants.ADDR, nullable = true, columnDefinition = EntityConstants.MENU_ADDR_COLUMN_DEFINITION)
	private String addr;
	
	@Column(name = EntityConstants.ICON, nullable = true, columnDefinition = EntityConstants.MENU_ICON_COLUMN_DEFINITION)
	private String icon;
	
	@Column(name = EntityConstants.TYPE, nullable = false, columnDefinition = EntityConstants.MENU_TYPE_COLUMN_DEFINITION)
	private String type;
	
	@Column(name = EntityConstants.SORT_NO, nullable = false, columnDefinition = EntityConstants.SORT_NO_COLUMN_DEFINITION)
	private Integer sortNo;
	
	@Column(name = EntityConstants.LEVEL, nullable = false, columnDefinition = EntityConstants.LEVEL_COLUMN_DEFINITION)
	private Integer level;
	
	@Column(name = EntityConstants.LEAF_FLAG, nullable = false, columnDefinition = EntityConstants.LEAF_FLAG_COLUMN_DEFINITION)
	private Boolean leafFlag;
	
	@Column(name = EntityConstants.HAS_BUTTON, nullable = false, columnDefinition = EntityConstants.HAS_BUTTON_COLUMN_DEFINITION)
	private Boolean hasButton;
	
	@Column(name = EntityConstants.PID, nullable = true, columnDefinition = EntityConstants.PID_COLUMN_DEFINITION)
	private Long pid;
}
