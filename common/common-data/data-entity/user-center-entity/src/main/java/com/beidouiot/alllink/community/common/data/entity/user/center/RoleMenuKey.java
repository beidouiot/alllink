package com.beidouiot.alllink.community.common.data.entity.user.center;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;

import lombok.Data;
import lombok.ToString;

/**
 * 
 *
 * @Description 联合主键
 * @author longww
 * @date 2021年4月11日
 */
@Data
@Embeddable
@ToString
public class RoleMenuKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 433939181401124045L;

	
	@Column(name = EntityConstants.MENU_ID, nullable = false, columnDefinition = EntityConstants.MENU_ID_COLUMN_DEFINITION)
	private Long menuId;
	
	@Column(name = EntityConstants.ROLE_ID, nullable = false, columnDefinition = EntityConstants.ROLE_ID_COLUMN_DEFINITION)
	private Long roleId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleMenuKey other = (RoleMenuKey) obj;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	
	
}
