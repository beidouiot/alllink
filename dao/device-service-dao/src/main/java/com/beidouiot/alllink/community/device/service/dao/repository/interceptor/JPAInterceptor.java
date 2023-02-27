package com.beidouiot.alllink.community.device.service.dao.repository.interceptor;

import java.util.Map;

import org.hibernate.EmptyInterceptor;

public class JPAInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6967377735530001839L;

	public static ThreadLocal<Map<String, String>> dynamicTable = new ThreadLocal<>();

	@Override
	public String onPrepareStatement(String sql) {
		
		Map<String, String> map = dynamicTable.get();
		if (null == map) {
			return sql;
		}

		String oldName = map.get("oldName");
		String newName = map.get("newName");
		if (null == oldName || oldName.trim().equals("") || null == newName || newName.trim().equals("")) {
			return sql;
		}

		sql = sql.replaceAll("into " + oldName, "into " + newName).replaceAll("from " + oldName, "from " + newName)
				.replaceAll("update " + oldName, "update " + newName);

		return sql;
	}

}
