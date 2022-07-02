package com.beidouiot.alllink.community.common.data.xxo.rro.datasearch;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 
 *
 * @Description 返回通用分页查询对象信息数据
 * @author longww
 * @date 2021年4月11日
 * @param <T>
 */
@ApiModel(description = "返回通用分页查询对象信息数据")
@Data
public class SmartPage<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2999030421848696825L;

	/**
	 * 每页大小
	 */
	@ApiModelProperty(value = "每页大小", dataType = "int")
	private int pageSize;
	
	/**
	 * 总页数
	 */
	@ApiModelProperty(value = "总页数", dataType = "int")
	private int totalPages;
	
	/**
	 * 总记录数
	 */
	@ApiModelProperty(value = "总记录数", dataType = "long")
	private long totalElements;
	
	/**
	 * 页码
	 */
	@ApiModelProperty(value = "页码", dataType = "int")
	private int pageNumber;
	
	/**
	 * 数据记录信息
	 */
	@ApiModelProperty(value = "数据记录信息", dataType = "List")
	private List<T> contents;
	
	public SmartPage(int pageSize, int totalPages, long totalElements, int pageNumber, List<T> contents) {
		super();
		this.pageSize = pageSize;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.pageNumber = pageNumber;
		this.contents = contents;
	}
	
	public SmartPage() {
		
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<T> getContents() {
		return contents;
	}

	public void setContents(List<T> contents) {
		this.contents = contents;
	}

}

