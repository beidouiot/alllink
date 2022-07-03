package com.beidouiot.alllink.community.common.dao.api.service.datasercher;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;

/**
 * 
 *
 * @Description 查询工具
 * @author longww
 * @date 2021年4月11日
 */
public class DataSearchUtils {

	private static DataSearchUtils dataSearchUtils = null;
	
	private DataSearchUtils() {
		
	}
	
	public static DataSearchUtils getInstance() {
		if (dataSearchUtils == null) {
			dataSearchUtils = new DataSearchUtils();
		}
		return dataSearchUtils;
	}
	
	/**
	 * 创建分页请求.
	 */
	public PageRequest buildPageRequest(int pageNumber, int pageSize, List<SortRpo> sortTypes) {
		Sort sort = null;
		if (sortTypes == null) {
			sort = Sort.by(Direction.DESC,"id");
		} else {
			int size = sortTypes.size();
			if (size == 0) {
				sort = Sort.by(Direction.DESC,"id");
			} else {
				for (int i = 0; i < size; i++) {
					SortRpo sortBean = sortTypes.get(i);
					if (i == 0) {
						sort = Sort.by(Direction.fromString(sortBean.getSortDirection()), sortBean.getSortName());
					} else {
						sort.and(Sort.by(Direction.fromString(sortBean.getSortDirection()), sortBean.getSortName()));
					}
				}
			}
		}
		
		return PageRequest.of(pageNumber - 1, pageSize, sort);
	}
	
	/**
	 * 创建动态查询条件组合.
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> Specification<T> buildSpecification( Map<String, Object> searchParams) {
		if (searchParams == null || searchParams.isEmpty()) {
			return null;
		}

		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> spec = 
				(Specification<T>) DynamicSpecifications.bySearchFilter(filters.values(), Class.class);
		return spec;
	}
	
	/**
	 * 创建动态查询条件组合.
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> Specification<T> buildSpecification(List<SearchFilter> searchFilterList) {
		if (searchFilterList == null || searchFilterList.isEmpty()) {
			return null;
		}
		Specification<T> spec = 
				(Specification<T>) DynamicSpecifications.bySearchFilter(searchFilterList, Class.class);
		return spec;
	}
}
