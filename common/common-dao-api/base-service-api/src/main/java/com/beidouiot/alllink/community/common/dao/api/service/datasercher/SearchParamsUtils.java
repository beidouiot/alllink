package com.beidouiot.alllink.community.common.dao.api.service.datasercher;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 * @Description 查询参数封装
 * @author longww
 * @date 2021年4月11日
 */
public class SearchParamsUtils {
	
	/**
	 * 根据查询对象组装查询条件Map. 将查询对象中非空的String类型属性组装为模糊查询，
	 * 非空Date类型属性的属性名如果以"from"或者"start"开头则组装为大于等于查询，
	 * 非空Date类型属性的属性名如果以"to"或者"end"开头则组装为小于查询，
	 * 非空Date类型属性的属性名非以上4个字符串开头则组装为等于查询
	 * 其他非空类型(Long,Integer,Byte,Shot,Float,Double,Boolean)属性租住为等于查询
	 * 所有属性联合查询以and进行连接查询。非以上属性类型的暂不支持。
	 * @param paramsObject 查询条件对象
	 * @return 如果paramsObject为null则直接返回null, 如果不为null则，返回组装后的Map
	 */
	public static Map<String, Object> makeSearchParams(Object paramsObject) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		if (paramsObject == null) {
			return searchParams;
		}
		
		Field[] fields = paramsObject.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				if (fieldName.equals("serialVersionUID")) {
					continue;
				}
				String temp = fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				
				if (fields[i].getType().isMemberClass()) {
					continue;
				} else if (fields[i].getGenericType().equals(String.class)) {
					if (fieldName.equals("sortName") || fieldName.equals("sortDirection")) {
						continue;
					}
					Method method;
					method = paramsObject.getClass().getMethod("get" + temp, new Class[0]);
					String str = (String)method.invoke(paramsObject, new Object[0]);
					if (StringUtils.isNotBlank(str)) {
						searchParams.put("LIKE_"+fieldName, str);
					}
				}  else if (fields[i].getGenericType().equals(Date.class)) {
					Method method;
					method = paramsObject.getClass().getMethod("get" + temp, new Class[0]);
					Object obj = method.invoke(paramsObject, new Object[0]);
					if (obj != null) {
						if (StringUtils.startsWith(fieldName, "from")) {
							String subFieldName = StringUtils.substringAfter(fieldName, "from");
							subFieldName = StringUtils.uncapitalize(subFieldName);
							searchParams.put("GTE_"+subFieldName, obj);
						} else if (StringUtils.startsWith(fieldName, "start")) {
							String subFieldName = StringUtils.substringAfter(fieldName, "start");
							subFieldName = StringUtils.uncapitalize(subFieldName);
							searchParams.put("GTE_"+subFieldName, obj);
						} else if (StringUtils.startsWith(fieldName, "to")) {
							String subFieldName = StringUtils.substringAfter(fieldName, "to");
							subFieldName = StringUtils.uncapitalize(subFieldName);
							searchParams.put("LT_"+subFieldName, obj);
						} else if (StringUtils.startsWith(fieldName, "end")) {
							String subFieldName = StringUtils.substringAfter(fieldName, "end");
							subFieldName = StringUtils.uncapitalize(subFieldName);
							searchParams.put("LT_"+subFieldName, obj);
						} else {
							searchParams.put("EQ_"+fieldName, obj);
						}
					}
				} else if (fields[i].getGenericType().equals(Long.class)) {
					Method method;
					method = paramsObject.getClass().getMethod("get" + temp, new Class[0]);
					Object obj = method.invoke(paramsObject, new Object[0]);
					if (obj != null) {
						searchParams.put("EQ_"+fieldName, obj);
					}
				} else if (fields[i].getGenericType().equals(Integer.class)) {
					Method method;
					method = paramsObject.getClass().getMethod("get" + temp, new Class[0]);
					Object obj = method.invoke(paramsObject, new Object[0]);
					if (obj != null) {
						searchParams.put("EQ_"+fieldName, obj);
					}
				} else if (fields[i].getGenericType().equals(Double.class)) {
					Method method;
					method = paramsObject.getClass().getMethod("get" + temp, new Class[0]);
					Object obj = method.invoke(paramsObject, new Object[0]);
					if (obj != null) {
						searchParams.put("EQ_"+fieldName, obj);
					}
				} else if (fields[i].getGenericType().equals(Float.class)) {
					Method method;
					method = paramsObject.getClass().getMethod("get" + temp, new Class[0]);
					Object obj = method.invoke(paramsObject, new Object[0]);
					if (obj != null) {
						searchParams.put("EQ_"+fieldName, obj);
					}
				} else if (fields[i].getGenericType().equals(Boolean.class)) {
					Method method;
					method = paramsObject.getClass().getMethod("get" + temp, new Class[0]);
					Object obj = method.invoke(paramsObject, new Object[0]);
					if (obj != null) {
						searchParams.put("EQ_"+fieldName, obj);
					}
				} else if (fields[i].getGenericType().equals(Byte.class)) {
					Method method;
					method = paramsObject.getClass().getMethod("get" + temp, new Class[0]);
					Object obj = method.invoke(paramsObject, new Object[0]);
					if (obj != null) {
						searchParams.put("EQ_"+fieldName, obj);
					}
				} else if (fields[i].getGenericType().equals(Short.class)) {
					Method method;
					method = paramsObject.getClass().getMethod("get" + temp, new Class[0]);
					Object obj = method.invoke(paramsObject, new Object[0]);
					if (obj != null) {
						searchParams.put("EQ_"+fieldName, obj);
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return searchParams;
	}

}
