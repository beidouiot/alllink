package com.beidouiot.alllink.community.common.data.entity.user.center;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
*
* @Description 用户职位
* @author longww
* @date 2021年5月9日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.USER_POSITION)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString
public class UserPosition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2997524028210430807L;
	
	@EmbeddedId
    private UserPositionKey id;
}
