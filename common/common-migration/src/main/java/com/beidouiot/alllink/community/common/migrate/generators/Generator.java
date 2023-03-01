package com.beidouiot.alllink.community.common.migrate.generators;

import com.beidouiot.alllink.community.common.migrate.schema.Column;
import com.beidouiot.alllink.community.common.migrate.schema.ForeignKey;
import com.beidouiot.alllink.community.common.migrate.schema.Index;
import com.beidouiot.alllink.community.common.migrate.schema.Table;

/**
 * Responsible for creating SQL DDL statements for a specific database.
 *
 */
public interface Generator {

	/**
	 * Determines whether a table named <code>tableName</code> exists
	 * 
	 * @param tableName String name of table
	 * @return true if table exists, otherwise false
	 */
	public boolean tableExists(String tableName);
	
	public boolean columnExists(String columnName, String tableName);
	
	public boolean indexExists(String indexName, String tableName);
	
	public boolean exists(Index index);
	
	public boolean foreignKeyExists(String foreignKeyName, String childTableName);
	
	public boolean exists(ForeignKey foreignKey);
	
	public String createTableStatement(Table table);
	
	public String createTableStatement(Table table, String options);

	public String dropTableStatement(String tableName);
	
	public String addColumnStatement(Column column, String tableName, String afterColumn);
	
	public String addColumnStatement(Column column, String tableName, int position);
	
	public String dropColumnStatement(String columnName, String tableName);
	
	public String addIndex(Index index);
	
	public String dropIndex(Index index);
	
	public String dropIndex(String indexName, String tableName);
	
	public String addForeignKey(ForeignKey foreignKey);
	
	public String dropForeignKey(ForeignKey foreignKey);
	
	public String dropForeignKey(String foreignKeyName, String childTable);
	
	public String renameColumn(String newColumnName, String oldColumnName, String tableName);
	
	public String wrapName(String name);
}
