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
* @Description 组织/部门用户关系
* @author longww
* @date 2021年4月14日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.DEPT_USER)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString
public class DeptUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6331117225888027208L;

	@EmbeddedId
	private DeptUserKey id;
	
}
