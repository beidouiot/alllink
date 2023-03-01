package com.beidouiot.alllink.community.common.migrate.schema;

import com.beidouiot.alllink.community.common.migrate.misc.Validator;

public class Index {

	private String name;
	private String tableName;
	private String[] columnNames;
	private boolean isUnique;
	private boolean isPrimaryKey;
	
	public Index(String tableName, String[] columnNames) {
		this(null, tableName, columnNames, false, false);
	}
	
	public Index(String name, String tableName, String[] columnNames, boolean isUnique, boolean isPrimaryKey) {
		this.name = name;
		this.tableName = tableName;
		this.columnNames = columnNames;
		this.isUnique = isUnique;
		this.isPrimaryKey = isPrimaryKey;
		
		init();		
	}
	
	public String getName() {
		return name;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}
	
	public boolean isUnique() {
		return isUnique;
	}
	
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	
	private void init() {
		Validator.notNull(tableName, "Table is required for an Index");
		Validator.notNull(columnNames, "At least one column names are required for an index");
		Validator.isTrue(columnNames.length > 0, "At least one column names are required for an index");
		Validator.isTrue(ConstraintHelper.hasValidValue(columnNames), "At least one column names are required for an index");
		
		if (name == null) {
			name = generateIndexName();
		}
	}

	private String generateIndexName() {
		StringBuffer name = new StringBuffer();
		
		name.append("idx_")
			.append(ConstraintHelper.nameFromTable(tableName, 8))
			.append("_");
	
		name.append(ConstraintHelper.nameFromColumns(columnNames));
		
		return name.toString();
	}

}
