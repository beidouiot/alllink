package com.beidouiot.alllink.community.common.migrate.util;

public class JdbcUrlUtils {

	/**
	 * 根据jdbc url 解析 出db name
	 * @author Loren
	 * @Datetime 2021年5月11日 下午4:09:14
	 * @param url
	 * @return
	 */
	public static String getDbName(String url) {
		return getMySQLDbName(url);
	}
	
	private static String getMySQLDbName(String url) {
		String[] urlArr = url.split(":");
		String[] portAndDbs = urlArr[3].split("/");
		String[] databaseSplit = portAndDbs[1].split("\\?");
		return databaseSplit[0];
	}
	
}
