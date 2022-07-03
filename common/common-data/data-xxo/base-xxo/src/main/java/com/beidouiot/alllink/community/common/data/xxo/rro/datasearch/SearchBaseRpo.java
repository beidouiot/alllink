package com.beidouiot.alllink.community.common.data.xxo.rro.datasearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 *
 * @Description 查询请求基本对象
 * @author longww
 * @date 2021年4月11日
 */
@ApiModel("查询请求基本对象")
@Data
public class SearchBaseRpo implements Serializable {
	
	
	/**
	 * 
	 */
	protected static final long serialVersionUID = 6313981369822350975L;

	/**
	 * 列表排序方式(支持多列排序)
	 */
	@ApiModelProperty(value = "列表排序方式(支持多列排序)", dataType = "List", required = false)
	private List<SortRpo> sortTypes;
	
	/**
	 * 排序列字段名称(仅支持单列排序)
	 */
	@ApiModelProperty(value = "排序列字段名称(仅支持单列排序)", dataType = "String", required = false)
	private String sortName = "updatedDate";
	
	/**
	 * 单列排序方向(升序，降序)
	 */
	@ApiModelProperty(value = "单列排序方向(升序，降序)", dataType = "String", required = false)
	private String sortDirection="DESC";
	
	

	/**
	 * 每页显示记录数
	 */
	@ApiModelProperty(value = "每页显示记录数", dataType = "int", required = true)
	private int pageSize=10;
	
	/**
	 * 显示的页码数
	 */
	@ApiModelProperty(value = "显示的页码数", dataType = "int", required = true)
	private int pageNumber=1;

	public List<SortRpo> getSortTypes() {
		if (sortTypes == null || sortTypes.size() == 0) {
			if (StringUtils.isNotBlank(sortName) && StringUtils.isNotBlank(sortDirection)) {
				SortRpo asb = new SortRpo(sortName,sortDirection);
				List<SortRpo> sort = new ArrayList<SortRpo>();
				sort.add(asb);
				sortTypes = sort;
			}
		}
		
		return sortTypes;
	}	
}
