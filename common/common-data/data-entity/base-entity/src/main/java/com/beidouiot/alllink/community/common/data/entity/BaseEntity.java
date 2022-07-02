package com.beidouiot.alllink.community.common.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;

import lombok.Data;

/**
 * 
 *
 * @Description 实体持久化基类
 * @author longww
 * @date 2021年4月11日
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@OptimisticLocking(type = OptimisticLockType.VERSION)
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8219853286302872891L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.beidouiot.alllink.community.common.data.snowflake.GenerateId")
	@Column(name = EntityConstants.ID, columnDefinition = EntityConstants.ID_COLUMN_DEFINITION)
	protected Long id;

	/**
	 * 创建时间, 必填
	 */
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = EntityConstants.CREATE_DATE, nullable = false, updatable = false, columnDefinition = EntityConstants.CREATE_DATE_COLUMN_DEFINITION)
	protected Date createdDate;

	/**
	 * 修改时间, 必填
	 */
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = EntityConstants.UPDATED_DATE, nullable = false, columnDefinition = EntityConstants.UPDATED_DATE_COLUMN_DEFINITION)
	protected Date updatedDate;

	/**
	 * 创建人（存储用户登录名）, 必填, length = 100
	 */
	@CreatedBy
	@Column(name = EntityConstants.CREATED_BY, nullable = false, updatable = false, columnDefinition = EntityConstants.CREATED_BY_COLUMN_DEFINITION)
	protected String createdBy = "System";

	/**
	 * 修改人, 必填, length = 100
	 */
	@LastModifiedBy
	@Column(name = EntityConstants.UPDATED_BY, nullable = false, columnDefinition = EntityConstants.UPDATED_BY_COLUMN_DEFINITION)
	protected String updatedBy = "System";

	/**
	 * 删除标志(假删除用, 0:正常, 1:删除),必填
	 */
	@Column(name = EntityConstants.DELETE_FLAG, nullable = false, columnDefinition = EntityConstants.DELETE_FLAG_COLUMN_DEFINITION)
	protected Boolean deleteFlag = new Boolean(false);

	@Version
	@Column(name = EntityConstants.OPTLOCK_VERSION, columnDefinition = EntityConstants.OPTLOCK_VERSION_COLUMN_DEFINITION)
	protected Integer optLockVersion;

	/**
	 * 对象比较
	 */
	@Override
	public boolean equals(Object obj) {
		if (!((obj.getClass()).isInstance(this))) {
			return false;
		}
		final BaseEntity temp = (BaseEntity) obj;
		if (temp.getId() == this.getId()) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		result = (result * PRIME) + id.hashCode();
		return result;
	}
}
