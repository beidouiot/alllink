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
public class RoleUserKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 433939181401124045L;

	
	@Column(name = EntityConstants.USER_ID, nullable = false, columnDefinition = EntityConstants.USER_ID_COLUMN_DEFINITION)
	private Long userId;
	
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
		RoleUserKey other = (RoleUserKey) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	
	
}
