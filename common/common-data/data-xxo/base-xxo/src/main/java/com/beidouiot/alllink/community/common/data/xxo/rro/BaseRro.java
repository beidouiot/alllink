package com.beidouiot.alllink.community.common.data.xxo.rro;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
*
* @Description TODO
* @author Administrator
* @date 2022年6月11日
*/

public abstract class BaseRro extends RRO {

/**
	 * 
	 */
	private static final long serialVersionUID = 6497471728887040187L;

	protected Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date createdDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date updatedDate;
	
	protected String createdBy;
	
	protected String updatedBy;
	
	protected Boolean deleteFlag;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseRro other = (BaseRro) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (deleteFlag == null) {
			if (other.deleteFlag != null)
				return false;
		} else if (!deleteFlag.equals(other.deleteFlag))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((deleteFlag == null) ? 0 : deleteFlag.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		return result;
	}
}
