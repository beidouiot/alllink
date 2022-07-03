package com.beidouiot.alllink.community.common.data.xxo.rro.datasearch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 *
 * @Description 查询列表排序方式请求对象
 * @author longww
 * @date 2021年4月11日
 */
@ApiModel("查询列表排序方式请求对象")
public class SortRpo {

	/**
	 * 排序列名
	 */
	@ApiModelProperty(value = "排序字段名", dataType = "String", required = false)
	private String sortName = "createdDate";
	
	/**
	 * 排序方向(升序，降序)
	 */
	@ApiModelProperty(value = "排序方向,值为ASC或DESC", dataType = "String", required = false)
	private String sortDirection="DESC";

	public SortRpo() {
		
	}
	
	public SortRpo(String sortName, String sortDirection) {
		this.sortName = sortName;
		this.sortDirection = sortDirection;
	}
	
	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	@Override
	public String toString() {
		return "SortRpo [sortName=" + sortName + ", sortDirection=" + sortDirection + "]";
	}
	
}

