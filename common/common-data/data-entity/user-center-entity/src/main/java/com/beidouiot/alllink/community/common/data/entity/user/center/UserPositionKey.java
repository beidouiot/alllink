package com.beidouiot.alllink.community.common.data.entity.user.center;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;

import lombok.Data;
import lombok.ToString;

/**
*
* @Description 联合主键
* @author longww
* @date 2021年5月9日
*/
@Data
@Embeddable
@ToString
public class UserPositionKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1208339872366625810L;

	@Column(name = EntityConstants.USER_ID, nullable = false, columnDefinition = EntityConstants.USER_ID_COLUMN_DEFINITION)
	private Long userId;
	
	@Column(name = EntityConstants.POSITION_ID, nullable = false, columnDefinition = EntityConstants.POSITION_ID_COLUMN_DEFINITION)
	private Long positionId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((positionId == null) ? 0 : positionId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPositionKey other = (UserPositionKey) obj;
		if (positionId == null) {
			if (other.positionId != null)
				return false;
		} else if (!positionId.equals(other.positionId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
