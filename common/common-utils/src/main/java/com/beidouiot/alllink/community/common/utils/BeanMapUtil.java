package com.beidouiot.alllink.community.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class BeanMapUtil {
	/**
	 * 对象转Map
	 * 
	 * @param object
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public static Map<String, Object> beanToMap(Object object) throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, InvocationTargetException {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = object.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			if (field.getName().equals("serialVersionUID")) {
				continue;
			}
			field.setAccessible(true);
			if(!field.getType().isPrimitive() ) {
			if (field.getType().getName().contains("TokenAdditionalInfo")) {
				String name = field.getName();
				String temp = name.substring(0, 1).toUpperCase() + name.substring(1);
				Method method = object.getClass().getMethod("get" + temp, new Class[0]);
				Object obj = method.invoke(object, new Object[0]);
				if (obj != null) {
					Map<String, Object> mapType = beanToMap(obj);
					map.put(name, mapType);
					continue;
				}
			}
			}
			map.put(field.getName(), field.get(object) == null ? "" : field.get(object));
		}
		return map;
	}

	/**
	 * map转对象
	 * 
	 * @param map
	 * @param beanClass
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) throws Exception {
		T object = beanClass.newInstance();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}
			field.setAccessible(true);
			if (map.containsKey(field.getName())) {
				field.set(object, map.get(field.getName()));
			}
		}
		return object;
	}
}
