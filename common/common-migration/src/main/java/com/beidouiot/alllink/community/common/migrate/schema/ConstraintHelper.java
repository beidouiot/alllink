package com.beidouiot.alllink.community.common.migrate.schema;

public class ConstraintHelper {

	protected static String nameFromTable(String tableName, int length) {
		if (tableName.length() >= length) {
			return tableName.substring(0, length);
		}
		
		return tableName;
	}
	
	protected static boolean hasValidValue(String[] columnNames) {
		boolean hasValue = false;
		
		for (int x = 0 ; x < columnNames.length ; x++) {
			if (columnNames[x] != null && columnNames[x].trim().length() > 0) {
				hasValue = true;
				break;
			}
		}
		
		return hasValue;
	}
	
	protected static String nameFromColumns(String[] columnNames) {
		
		StringBuffer buffer = new StringBuffer();
		
		if (columnNames.length == 1) {
			buffer.append(getCharacters(columnNames[0], 8));
		} else {
			//Multiple Columns
			buffer.append(getCharacters(columnNames[0], 4));
			buffer.append("_");
			buffer.append(getCharacters(columnNames[columnNames.length - 1], 4));
		}
		
		return buffer.toString();
	}
	
	private static String getCharacters(String string, int length) {
		if (string == null) {
			return "null";
		}
		
		String str = string;
		
		if (str.length() > length) {
			str = str.substring(0, length);
		}
		
		return str;
	}
}
