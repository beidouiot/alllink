package com.beidouiot.alllink.community.common.migrate.generators;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beidouiot.alllink.community.common.migrate.schema.Column;

/**
 * Provides basic logic for most Generators.
 *
 */
public class GeneratorHelper {

	private static final Map<Integer, String> types;
	private static final List<Integer> needsLength;
	private static final List<Integer> needsQuotes;
	private static final List<Integer> stringTypes;
	
	static {
		types = new HashMap<Integer, String>();
		types.put(new Integer(Types.BIGINT), "BIGINT");
		types.put(new Integer(Types.BOOLEAN), "BOOL");
		types.put(new Integer(Types.CHAR), "CHAR");
		types.put(new Integer(Types.DATE), "DATE");
		types.put(new Integer(Types.DECIMAL), "DECIMAL");
		types.put(new Integer(Types.DOUBLE), "DOUBLE");
		types.put(new Integer(Types.FLOAT), "FLOAT");
		types.put(new Integer(Types.INTEGER), "INT");
		types.put(new Integer(Types.LONGVARCHAR), "TEXT");
		types.put(new Integer(Types.NUMERIC), "NUMERIC");
		types.put(new Integer(Types.SMALLINT), "SMALLINT");
		types.put(new Integer(Types.TIME), "TIME");
		types.put(new Integer(Types.TIMESTAMP), "TIMESTAMP");
		types.put(new Integer(Types.TINYINT), "TINYINT");
		types.put(new Integer(Types.VARCHAR), "VARCHAR");
		
		needsLength = new ArrayList<Integer>();
		needsLength.add(new Integer(Types.CHAR));
		needsLength.add(new Integer(Types.VARCHAR));
		
		needsQuotes = new ArrayList<Integer>();
		needsQuotes.add(new Integer(Types.CHAR));
		needsQuotes.add(new Integer(Types.LONGVARCHAR));
		needsQuotes.add(new Integer(Types.VARCHAR));
		
		stringTypes = new ArrayList<Integer>();
		stringTypes.add(new Integer(Types.CHAR));
		stringTypes.add(new Integer(Types.LONGVARCHAR));
		stringTypes.add(new Integer(Types.VARCHAR));
	}
	
	public static String getSqlName(int type) {
		return types.get(new Integer(type));
	}
	
	public static boolean needsLength(int type) {
		return needsLength.contains(new Integer(type));
	}
	
	public static boolean needsQuotes(int type) {
		return needsQuotes.contains(new Integer(type));
	}
	
	public static boolean isStringType(int type) {
		return stringTypes.contains(new Integer(type));
	}
	
	public static Column[] getPrimaryKeyColumns(Column[] columns) {
		
		List<Column> primaryKeyColumns = new ArrayList<Column>();
		
		for (int x = 0 ; x < columns.length ; x++) {
			
			try {
				Column column = columns[x];
				
				if (column != null && column.isPrimaryKey()) {
					primaryKeyColumns.add(column);
				}
				
			} catch (ClassCastException ignored) {
			}

		}
		
		return primaryKeyColumns.toArray(new Column[primaryKeyColumns.size()]);
	}
	
	public static int countPrimaryKeyColumns(Column[] columns) {
		return getPrimaryKeyColumns(columns).length;
	}
	
	public static int countAutoIncrementColumns(Column[] columns) {
		int retVal = 0;
		
		for (int x = 0 ; x < columns.length ; x++) {
			
			try {
				Column column = columns[x];
				
				if (column != null && column.isAutoincrement()) {
					retVal++;
				}
				
			} catch (ClassCastException ignored) {
			}
		}
		
		return retVal;
	}
	
	public static String makeStringList(String[] strings) {
		
		StringBuffer buffer = new StringBuffer();
		
		String comma = "";
		for (int x = 0 ; x < strings.length ; x++) {
			buffer.append(comma)
				.append(strings[x]);
			
			comma = ", ";
		}
		
		return buffer.toString();
	}
	
}
