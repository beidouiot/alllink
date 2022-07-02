package com.beidouiot.alllink.community.common.data.entity.user.center;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;

import lombok.Data;
import lombok.ToString;

/**
*
* @Description 组织/部门用户关系
* @author longww
* @date 2021年4月14日
*/
@Data
@Embeddable
@ToString
public class DeptUserKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5043013653666017381L;

	@Column(name = EntityConstants.USER_ID, nullable = false, columnDefinition = EntityConstants.USER_ID_COLUMN_DEFINITION)
	private Long userId;
	
	@Column(name = EntityConstants.DEPT_ID, nullable = false, columnDefinition = EntityConstants.DEPT_ID_COLUMN_DEFINITION)
	private Long deptId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeptUserKey other = (DeptUserKey) obj;
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId))
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
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
}
