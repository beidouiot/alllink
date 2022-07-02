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
 *
 * @Description 角色用户
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.ROLE_USER)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString
public class RoleUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5042732832918512783L;
	
	@EmbeddedId
    private RoleUserKey id;

}
