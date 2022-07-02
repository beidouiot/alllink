package com.beidouiot.alllink.community.common.dao.api.service.datasercher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.beidouiot.alllink.community.common.utils.AlllinkStringUtils;

/**
 * 
 *
 * @Description 查询方式
 * @author longww
 * @date 2021年4月11日
 */
public class SearchFilter {

	public enum Operator {
		EQ, LIKE, GT, LT, GTE, LTE
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value == null) {
				continue;
			}
			if (value.getClass().getSimpleName().equals("String")) {
				if (AlllinkStringUtils.isBlank((String) value)) {
					continue;
				}
			} 
			
			// 拆分operator与filedAttribute
			String[] names = AlllinkStringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);

			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}
	
	public static List<SearchFilter> parseForList(Map<String, Object> searchParams, Map<String, Integer> hash) {
		List<SearchFilter> list = new ArrayList<>();
		int hashcode = 0;
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value == null) {
				continue;
			}
			if (value.getClass().getSimpleName().equals("String")) {
				if (AlllinkStringUtils.isBlank((String) value)) {
					continue;
				}
			} 
			
			// 拆分operator与filedAttribute
			String[] names = AlllinkStringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);
			hashcode += hashcode + key.hashCode()+operator.hashCode()+value.hashCode();
			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, value);
			list.add(filter);
		}
		hash.put("hashcode", hashcode);
		return list;
	}
}
